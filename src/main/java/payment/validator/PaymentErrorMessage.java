package payment.validator;

public enum PaymentErrorMessage {

    INSUFFICIENT_BALANCE("잔액이 부족합니다.");

    private final String message;

    PaymentErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}