package order;

import file.LoadFile;
import io.Input;
import product.Product;
import product.validator.ProductErrorMessage;
import product.validator.ProductValidator;
import root.RootDto;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static Order ORDER;
    private final LoadFile loadFile = new LoadFile();
    private final RootDto rootDto;
    private List<Product> products;
    private final List<Cart> carts = new ArrayList<>();
    private int count = 0;
    private int amount = 0;

    private Order(RootDto rootDto) {
        this.rootDto = rootDto;
    }

    public static Order getInstance(RootDto rootDto) {
        if (ORDER == null) {
            ORDER = new Order(rootDto);
        }

        return ORDER;
    }

    private void intro() {
        System.out.println("\n=================================");
        System.out.println("안녕하세요. " + rootDto.consumer().getId() + "님 햄버거 가게 입니다.");
        System.out.println("현재 접속된 관리자는 " + rootDto.manager().getName() + "입니다.\n");
    }

    private void outro() {
        System.out.println("\n구매하실 상품명과 수량을 입력해 주세요. (예: [치킨버거-3],[불고기버거세트-2])");
    }

    private void showMenu() {
        if (products == null) {
            loadFile.readFile();
            products = loadFile.getProducts();
        }
        intro();

        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    public void start() {
        showMenu();
        while (true) {
            outro();
            carts.clear();
            count = 0;
            amount = 0;
            try {
                String input = Input.nextLine();
                selectMenu(input);
                orderCalculation();
                receipt();
                if (!orderAdditional()) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    private boolean orderAdditional() {
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        String input = Input.nextLine();
        if (input.equals("Y")) {
            start();
        }
        return false;
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

    private void orderCalculation() {
        for (Cart cart : carts) {
            Product product = products.stream().filter(findProduct -> findProduct.getName().equals(cart.getName())).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(ProductErrorMessage.NOT_EXIST_PRODUCT.getMessage()));

            ProductValidator.validateQuantity(product, cart.getQuantity());
            product.updateQuantity(cart.getQuantity());
            cart.productPrice(product.getPrice() * cart.getQuantity());
            amount += product.getPrice() * cart.getQuantity();
            count += cart.getQuantity();
        }

        ProductValidator.validateConsumerMoney(rootDto.consumer(), amount);
        rootDto.manager().sell(amount);
        rootDto.consumer().buy(amount);
    }

    private void selectMenu(String input) {
        String[] separatedOrderMenu = input.split("\\],\\[");
        for (String orderMenu : separatedOrderMenu) {
            ProductValidator.validateConsumerInput(orderMenu);

            String menu = orderMenu.replace("[", "").replace("]", "");

            String[] menuNameAndQuantity = menu.split("\\-");

            String menuName = menuNameAndQuantity[0];
            int quantity = Integer.parseInt(menuNameAndQuantity[1]);

            carts.add(new Cart(menuName, quantity));
        }
    }

}