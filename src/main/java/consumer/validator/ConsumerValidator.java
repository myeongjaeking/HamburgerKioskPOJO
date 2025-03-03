package consumer.validator;

import consumer.Consumer;
import manager.Manager;
import root.RootDto;


public class ConsumerValidator {

    public static void createValidation(String[] nameAndMoney) {
        validateLength(nameAndMoney);
        validateNumberFormat(nameAndMoney);
        validateIsNegative(nameAndMoney);
    }

    public static void connectValidation(String name, RootDto rootDto) {
        validName(name, rootDto.consumer());
        validConnection(rootDto.consumer());
        isConnectedManager(rootDto.manager());
    }

    private static void isConnectedManager(Manager manager) {
        if (!manager.isConnect()) {
            throw new IllegalArgumentException(ConsumerErrorMessage.NOT_CONNECTED_MANAGER.getMessage());
        }
    }

    private static void validName(String name, Consumer consumer) {
        if (!name.equals(consumer.getName())) {
            throw new IllegalArgumentException(ConsumerErrorMessage.NOT_EXIST_CONSUMER.getMessage());
        }
    }

    private static void validConnection(Consumer consumer) {
        if (consumer.isConnect()) {
            throw new IllegalArgumentException(ConsumerErrorMessage.ALREADY_CONNECT_CONSUMER.getMessage());
        }
    }

    private static void validateLength(String[] nameAndMoney) {
        if (nameAndMoney.length != 2) {
            throw new IllegalArgumentException(ConsumerErrorMessage.NOT_FIT_FORMAT_CREATE_CONSUMER.getMessage());
        }
    }

    private static void validateNumberFormat(String[] nameAndMoney) {
        try {
            Integer.parseInt(nameAndMoney[1].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ConsumerErrorMessage.NOT_FIT_FORMAT_CREATE_CONSUMER.getMessage());
        }
    }

    private static void validateIsNegative(String[] nameAndMoney) {
        if (Integer.parseInt(nameAndMoney[1].trim()) < 0) {
            throw new NumberFormatException(ConsumerErrorMessage.INVALID_NEGATIVE_NUMBER.getMessage());
        }
    }

}