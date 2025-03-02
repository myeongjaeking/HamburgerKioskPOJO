package kiosk.validator;

public enum KioskErrorMessage {

    NOT_FIT_FORMAT_KIOSK("[ERROR] 메뉴 선택지에 없는 번호입니다.");

    private final String message;

    KioskErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}