package payment;

import order.Cart;
import payment.validator.PaymentValidator;
import product.Product;
import root.ManagerConsumerDto;

import java.util.List;

public class Payment {

    private static Payment PAYMENT;
    private final List<Cart> carts;
    private final List<Product> products;
    private final ManagerConsumerDto managerConsumerDto;
    private int amount = 0;
    private int count = 0;

    private Payment(List<Cart> carts, List<Product> products, ManagerConsumerDto managerConsumerDto) {
        this.carts = carts;
        this.products = products;
        this.managerConsumerDto = managerConsumerDto;
    }

    public static Payment getInstance(List<Cart> carts, List<Product> products, ManagerConsumerDto managerConsumerDto) {
        if (PAYMENT == null) {
            PAYMENT = new Payment(carts, products, managerConsumerDto);
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
            Product product = products.stream()
                    .filter(findProduct -> findProduct.getName().equals(cart.getName()))
                    .findFirst()
                    .get();

            product.updateQuantity(cart.getQuantity());
            cart.productPrice(product.getPrice() * cart.getQuantity());

            totalMoney(product.getPrice() * cart.getQuantity(), cart.getQuantity());
        }

        PaymentValidator.validateConsumerMoney(managerConsumerDto.consumer(), amount);
        managerConsumerDto.manager().sell(amount);
        managerConsumerDto.consumer().buy(amount);
    }

    private void totalMoney(int amount, int count) {
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
        System.out.println("판매자 : " + managerConsumerDto.manager().getName() + ", " + managerConsumerDto.manager().getMoney());
        System.out.println("구매자 : " + managerConsumerDto.consumer().getId() + ", " + managerConsumerDto.consumer().getMoney());
    }

}