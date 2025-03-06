package util;

public enum Separator {

    REST(","),
    OPEN_SQUARE_BRACKETS("["),
    CLOSE_SQUARE_BRACKETS("]"),
    BLANK("");

    private final String sign;

    Separator(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

}