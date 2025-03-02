package manager.validator;

public class ManagerValidator {

    public static void createValidation(String[] nameAndMoney) {
        validateLength(nameAndMoney);
        validateNumberFormat(nameAndMoney);
        validateIsNegative(nameAndMoney);
    }

    private static void validateLength(String[] nameAndMoney) {
        if (nameAndMoney.length != 2) {
            throw new IllegalArgumentException(ManagerErrorMessage.NOT_FIT_FORMAT_CREATE_MANAGER.getMessage());
        }
    }

    private static void validateNumberFormat(String[] nameAndMoney) {
        try {
            Integer.parseInt(nameAndMoney[1].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ManagerErrorMessage.NOT_FIT_FORMAT_CREATE_MANAGER.getMessage());
        }
    }

    private static void validateIsNegative(String[] nameAndMoney) {
        if(Integer.parseInt(nameAndMoney[1].trim())<0){
            throw new NumberFormatException(ManagerErrorMessage.INVALID_NEGATIVE_NUMBER.getMessage());
        }
    }

}