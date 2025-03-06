package product.validator;

import product.Product;

import static util.Separator.*;

public class ProductValidator {

    public static void validateConsumerInput(String orderMenu) {
        validateStartWith(orderMenu);
        validateQuantity(orderMenu);
    }

    public static void validateQuantity(Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException(ProductErrorMessage.OUT_OF_PRODUCT.getMessage());
        }
    }

    private static void validateStartWith(String orderMenu) {
        if (!orderMenu.startsWith("[") || !orderMenu.endsWith("]")) {
            throw new IllegalArgumentException(ProductErrorMessage.NOT_GOOD_INPUT.getMessage());
        }
    }

    private static void validateQuantity(String orderMenu) {
        String menu = orderMenu.replace(OPEN_SQUARE_BRACKETS.getSign(), BLANK.getSign())
                .replace(CLOSE_SQUARE_BRACKETS.getSign(), BLANK.getSign());
        String[] menuNameAndQuantity = menu.split("\\-");

        if (menuNameAndQuantity.length < 2) {
            throw new IllegalArgumentException(ProductErrorMessage.NOT_GOOD_INPUT.getMessage());
        }

        try {
            Integer.parseInt(menuNameAndQuantity[1]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ProductErrorMessage.NOT_GOOD_INPUT.getMessage());
        }
    }

}