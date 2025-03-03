package consumer.validator;

public enum ConsumerErrorMessage {

    NOT_EXIST_CONSUMER("[Error] 존재하지 않는 회원입니다."),
    NOT_FIT_FORMAT_CREATE_CONSUMER("[Error] 회원 생성 형식에 맞지 않습니다."),
    INVALID_NEGATIVE_NUMBER("[Error] 음수는 사용할 수 없습니다."),
    ALREADY_CONNECT_CONSUMER("[Error] 이미 접속한 회원입니다.");

    private final String message;

    ConsumerErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}