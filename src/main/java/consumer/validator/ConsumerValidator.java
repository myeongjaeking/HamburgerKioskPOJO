package consumer.validator;

import consumer.Consumer;
import manager.Manager;
import root.RootDto;


public class ConsumerValidator {

    public static void createValidation(String[] idAndMoney) {
        validateLength(idAndMoney);
        validateCreateFormat(idAndMoney);
        validateIsNegative(idAndMoney);
    }

    public static void connectValidation(String id, RootDto rootDto) {
        validateConnectFormat(id);
        validId(id, rootDto.consumer());
        validConnection(rootDto.consumer());
        isConnectedManager(rootDto.manager());
    }

    private static void isConnectedManager(Manager manager) {
        if (!manager.isConnect()) {
            throw new IllegalArgumentException(ConsumerErrorMessage.NOT_CONNECTED_MANAGER.getMessage());
        }
    }

    private static void validId(String id, Consumer consumer) {
        if (Integer.parseInt(id) != consumer.getId()) {
            throw new IllegalArgumentException(ConsumerErrorMessage.NOT_EXIST_CONSUMER.getMessage());
        }
    }

    private static void validConnection(Consumer consumer) {
        if (consumer.isConnect()) {
            throw new IllegalArgumentException(ConsumerErrorMessage.ALREADY_CONNECT_CONSUMER.getMessage());
        }
    }

    private static void validateLength(String[] idAndMoney) {
        if (idAndMoney.length != 2) {
            throw new IllegalArgumentException(ConsumerErrorMessage.NOT_FIT_FORMAT_CREATE_CONSUMER.getMessage());
        }
    }

    private static void validateConnectFormat(String id){
        try {
            Integer.parseInt(id);
        }catch (NumberFormatException e){
            throw new NumberFormatException(ConsumerErrorMessage.NOT_EXIST_CONSUMER.getMessage());
        }
    }

    private static void validateCreateFormat(String[] idAndMoney) {
        try {
            Integer.parseInt(idAndMoney[0].trim());
            Integer.parseInt(idAndMoney[1].trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ConsumerErrorMessage.NOT_FIT_FORMAT_CREATE_CONSUMER.getMessage());
        }
    }

    private static void validateIsNegative(String[] nameAndMoney) {
        if (Integer.parseInt(nameAndMoney[0].trim()) < 0) {
            throw new NumberFormatException(ConsumerErrorMessage.INVALID_NEGATIVE_NUMBER.getMessage());
        }
        if (Integer.parseInt(nameAndMoney[1].trim()) < 0) {
            throw new NumberFormatException(ConsumerErrorMessage.INVALID_NEGATIVE_NUMBER.getMessage());
        }
    }

}