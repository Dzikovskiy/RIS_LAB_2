public class Product {
    private String category;
    private String model;
    private double price;
    private boolean available;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String isAvailable() {
        return available ? "Available" : "Not available";
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
