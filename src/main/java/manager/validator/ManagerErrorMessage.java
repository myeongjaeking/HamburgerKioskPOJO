package manager.validator;

public enum ManagerErrorMessage {

    NOT_EXIST_MANAGER("[Error] 존재하지 않는 관리자입니다."),
    NOT_FIT_FORMAT_CREATE_MANAGER("[Error] 관리자 생성 형식에 맞지 않습니다."),
    INVALID_NEGATIVE_NUMBER("[Error] 음수는 사용할 수 없습니다.");

    private final String message;

    ManagerErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}