package order;

import file.LoadFile;
import io.Input;
import order.validator.OrderValidator;
import payment.Payment;
import product.Product;
import product.validator.ProductValidator;
import root.RootDto;
import util.Separator;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private static Order ORDER;
    private final LoadFile loadFile = new LoadFile();
    private final RootDto rootDto;
    private List<Product> products;
    private final List<Cart> carts = new ArrayList<>();

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
        loadFile.readFile();
        products = loadFile.getProducts();
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

            try {
                String input = Input.nextLine();
                selectMenu(input);

                Payment.getInstance(carts, products, rootDto).start();

                loadFile.writeFile(products);
                products.clear();
                if (!orderAdditional()) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean orderAdditional() {
        try{
            System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
            String input = Input.nextLine();

            OrderValidator.validateAddition(input);

            if (input.equals("Y")) {
                start();
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            orderAdditional();
        }

        return false;
    }

    private void selectMenu(String input) {
        String[] separatedOrderMenu = input.split(Separator.REST.getSign());

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