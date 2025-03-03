package file.validator;

public enum FileErrorMessage {

    NOT_FOUND_FILE("[Error] 파일을 찾을 수 없습니다.");

    private final String message;

    FileErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}