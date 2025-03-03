package manager.validator;

import manager.Manager;

public class ManagerValidator {

    public static void createValidation(String[] nameAndMoney) {
        validateLength(nameAndMoney);
        validateNumberFormat(nameAndMoney);
        validateIsNegative(nameAndMoney);
    }

    public static void connectValidation(String name, Manager manager) {
        validName(name, manager);
        validConnection(manager);
    }

    private static void validName(String name, Manager manager) {
        if (!name.equals(manager.getName())) {
            throw new IllegalArgumentException(ManagerErrorMessage.NOT_EXIST_MANAGER.getMessage());
        }
    }

    private static void validConnection(Manager manager) {
        if (manager.isConnect()) {
            throw new IllegalArgumentException(ManagerErrorMessage.ALREADY_CONNECT_MANAGER.getMessage());
        }
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
        if (Integer.parseInt(nameAndMoney[1].trim()) < 0) {
            throw new NumberFormatException(ManagerErrorMessage.INVALID_NEGATIVE_NUMBER.getMessage());
        }
    }

}