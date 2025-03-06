package payment;

import order.Cart;
import payment.validator.PaymentValidator;
import product.Product;
import product.validator.ProductErrorMessage;
import product.validator.ProductValidator;
import root.RootDto;

import java.util.List;

public class Payment {

    private static Payment PAYMENT;
    private final List<Cart> carts;
    private final List<Product> products;
    private final RootDto rootDto;
    int amount = 0;
    int count = 0;

    private Payment(List<Cart> carts, List<Product> products, RootDto rootDto) {
        this.carts = carts;
        this.products = products;
        this.rootDto = rootDto;
    }

    public static Payment getInstance(List<Cart> carts, List<Product> products, RootDto rootDto) {
        if (PAYMENT == null) {
            PAYMENT = new Payment(carts, products, rootDto);
        }

        return PAYMENT;
    }

    public void start() {
        amount = 0;
        count = 0;
        pay();
        receipt();
    }

    private void pay() {
        for (Cart cart : carts) {
            Product product = products.stream().filter(findProduct -> findProduct.getName().equals(cart.getName())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(ProductErrorMessage.NOT_EXIST_PRODUCT.getMessage()));

            ProductValidator.validateQuantity(product, cart.getQuantity());
            product.updateQuantity(cart.getQuantity());
            cart.productPrice(product.getPrice() * cart.getQuantity());

            update(product.getPrice() * cart.getQuantity(), cart.getQuantity());
        }

        PaymentValidator.validateConsumerMoney(rootDto.consumer(), amount);
        rootDto.manager().sell(amount);
        rootDto.consumer().buy(amount);
    }

    private void update(int amount, int count) {
        this.amount += amount;
        this.count += count;
    }

    private void receipt() {
        System.out.println("=====================");
        System.out.println("상품명\t\t" + "수량\t" + " 금액");

        for (Cart cart : carts) {
            System.out.println(cart.getName() + "\t\t" + cart.getQuantity() + "\t" + cart.getPrice());
        }

        System.out.println("=====================");
        System.out.println("총구매액  " + count + "\t" + amount);
        System.out.println("=====================");
        System.out.println("판매자 : " + rootDto.manager().getName() + ", " + rootDto.manager().getMoney());
        System.out.println("구매자 : " + rootDto.consumer().getId() + ", " + rootDto.consumer().getMoney());
    }

}