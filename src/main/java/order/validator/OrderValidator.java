package order.validator;

public class OrderValidator {

    public static void validateAddition(String input){
        if(!(input.equals("Y") || input.equals("N"))){
            throw new IllegalArgumentException(OrderErrorMessage.NOT_GOOD_INPUT.getMessage());
        }
    }

}