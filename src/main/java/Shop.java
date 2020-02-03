import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.Iterator;

@Singleton
public class Shop {
    ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(Product product) {
        Iterator<Product> itr = products.iterator();
        while (itr.hasNext()) {
            Product prod_element = itr.next();
            if (product.getModel().equals(prod_element.getModel())) {
                products.remove(prod_element);
            }
        }
    }
    public void deleteProductByIndex(int index) {
     products.remove(index);
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProductsInRange(int from, int to) {
        ArrayList<Product> result = new ArrayList<>();
        Iterator<Product> itr = products.iterator();

        while (itr.hasNext()) {
            Product prod_element = itr.next();
            if (prod_element.getPrice() >= from && prod_element.getPrice() <= to) {
                result.add(prod_element);
            }
        }
        return result;
    }
}
