package product;

public class Product {

    private String name;
    private int price;
    private int quantity;
    private String description;
    private String category;

    public Product(String name, int price, int quantity, String description, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
    }

    @Override
    public String toString() {
        return "-" + name + ", " + price + "원, " + quantity + "개, " + description;
    }

}