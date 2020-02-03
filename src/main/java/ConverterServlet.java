import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet(name = "ConverterServlet")
public class ConverterServlet extends HttpServlet {

    @EJB
    Converter converter;
    @EJB
    Shop shop;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        String amount = request.getParameter("amount");
        if (amount != null && amount.length() > 0) {
            BigDecimal d = new BigDecimal(amount);
            BigDecimal yenAmount = converter.dollarToYen(d);
            BigDecimal euroAmount = converter.yenToEuro(yenAmount);
            out.println("<!DOCTYPE html> <html>");
            out.println("<head>   <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
            out.println("<title>Currency</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Dollars sum = " + d + "</p>");
            out.println("<p>Yean sum = " + yenAmount + "</p>");
            out.println("<p>Euro sum = " + euroAmount + "</p>");
            out.println("</body>");
            out.println("</html>");

        }
    }

}
