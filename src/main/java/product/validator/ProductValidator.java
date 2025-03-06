package product.validator;

import product.Product;
import util.SetMenu;

import java.util.List;

import static util.Separator.*;

public class ProductValidator {

    public static void validateConsumerInput(String orderMenu, String menuName, int quantity, List<Product> products) {
        validateStartWith(orderMenu);
        validateInput(orderMenu);
        isExistMenu(menuName, products);
        isQuantityExceed(menuName, quantity, products);
        isCanOrderSetMenu(menuName, quantity, products);
    }

    private static void validateStartWith(String orderMenu) {
        if (!orderMenu.startsWith(OPEN_SQUARE_BRACKETS.getSign()) ||
                !orderMenu.endsWith(CLOSE_SQUARE_BRACKETS.getSign())) {
            throw new IllegalArgumentException(ProductErrorMessage.NOT_GOOD_INPUT.getMessage());
        }
    }

    private static void validateInput(String orderMenu) {
        String menu = orderMenu.replace(OPEN_SQUARE_BRACKETS.getSign(), BLANK.getSign())
                .replace(CLOSE_SQUARE_BRACKETS.getSign(), BLANK.getSign());
        String[] menuNameAndQuantity = menu.split(HYPHEN.getSign());

        if (menuNameAndQuantity.length < 2) {
            throw new IllegalArgumentException(ProductErrorMessage.NOT_GOOD_INPUT.getMessage());
        }

        try {
            Integer.parseInt(menuNameAndQuantity[1]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ProductErrorMessage.NOT_GOOD_INPUT.getMessage());
        }
    }

    private static void isExistMenu(String menuName, List<Product> products) {
        boolean isExistMenu = products.stream()
                .anyMatch(product -> product.getName().equals(menuName));
        if (!isExistMenu) {
            throw new IllegalArgumentException(ProductErrorMessage.NOT_EXIST_PRODUCT.getMessage());
        }
    }

    //초과하는지 검증
    private static void isQuantityExceed(String menuName, int quantity, List<Product> products) {
        Product product = products.stream()
                .filter(findProduct -> findProduct.getName().equals(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ProductErrorMessage.NOT_EXIST_PRODUCT.getMessage()));
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException(ProductErrorMessage.OUT_OF_PRODUCT.getMessage());
        }
    }

    private static void isCanOrderSetMenu(String menuName, int quantity, List<Product> products) {
        if(menuName.contains(SetMenu.SET.getMenuName())){
            int index = menuName.indexOf(SetMenu.SET.getMenuName());
            String singleMenu = menuName.substring(0,index);

            isQuantityExceed(singleMenu,quantity,products);
            isQuantityExceed(SetMenu.FRENCH_FRIES.getMenuName(), quantity,products);
            isQuantityExceed(SetMenu.COLA.getMenuName(), quantity,products);
        }
    }

}