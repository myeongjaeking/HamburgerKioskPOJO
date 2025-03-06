package order.validator;

public enum OrderErrorMessage {

    NOT_GOOD_INPUT("입력 형식에 맞지 않습니다.");

    private final String message;

    OrderErrorMessage(String message){
        this.message =message;
    }

    public String getMessage() {
        return message;
    }

}