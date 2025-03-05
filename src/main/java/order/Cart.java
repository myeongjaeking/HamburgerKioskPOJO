package order;

public class Cart {

    private final String name;
    private final int quantity;
    private int price;

    public Cart(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void productPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

}