package payment.validator;

import consumer.Consumer;

public class PaymentVaildator {

    public static void validateConsumerMoney(Consumer consumer, int amount) {
        if (consumer.getMoney() < amount) {
            throw new IllegalArgumentException(PaymentErrorMessage.INSUFFICIENT_BALANCE.getMessage());
        }
    }

}