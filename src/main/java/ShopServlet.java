import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ShopServlet")
public class ShopServlet extends HttpServlet {//может сделать операцию над шоп, а потом сделать редирект на хтмл

    @EJB
    Shop shop;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String button = req.getParameter("button");
        if (button.equals("add_btn")) {
            Product product = new Product();
            product.setCategory(req.getParameter("category"));
            product.setModel(req.getParameter("model"));
            product.setPrice(Double.parseDouble(req.getParameter("price")));
            if (req.getParameter("available").equals("Available")) {
                product.setAvailable(true);
            } else {
                product.setAvailable(false);
            }
            shop.addProduct(product);

        }
        if (button.matches("del_btn(.*)")) {
            String buff[] = button.split("-");
            shop.deleteProductByIndex(Integer.parseInt(buff[1]));

        }

        ArrayList<Product> products = shop.getProducts();
        if (button.equals("range_btn")) {
            products = shop.getProductsInRange(Integer.parseInt(req.getParameter("from")), Integer.parseInt(req.getParameter("to")));
        }

        printPage(resp, products);


    }

    private void printPage(HttpServletResponse resp, ArrayList<Product> products) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "<link rel=\"stylesheet\" href=\"style.css\">" +
                "    <title>Shop</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"ShopServlet\" method=\"POST\">\n" +
                "  <h1>Shop</h1><br/>"+
                "    Category: <input name=\"category\"/>\n" +
                "    Model: <input name=\"model\"/>\n" +
                "    Price: <input name=\"price\"/>\n" +
                "    Status: <select name=\"available\">\n" +
                "    <option>Available</option>\n" +
                "    <option>Not available</option>\n" +
                "    <br><br>\n" +
                "</select>\n" +
                "    <button type=\"submit\" name=\"button\" value=\"add_btn\">Add</button>\n" +
                "    <br/>\n" +
                "        <br/>\n" +

                "\n" + "   <br/>\n" +
                "    Price range from: <input name=\"from\"/> to:<input name=\"to\"/>" +
                "<button type=\"submit\" name=\"button\" value=\"range_btn\">find</button> <br />" +
                "      Products: <br> <div >");
        if (!products.isEmpty()) {
            for (int i = 0; i < products.size(); i++) {
                printWriter.println("<b>" + products.get(i).getCategory() + "</b>  " + products.get(i).getModel()
                        + ", " + products.get(i).getPrice() + "$ " + ", " + products.get(i).isAvailable()
                        + " <button class=\"delete_btn\" type=\"submit\" name=\"button\" value=\"del_btn-" + i + "\">delete</button>" + "<br/>");
            }
        }
        printWriter.println("</div>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");


    }
}
