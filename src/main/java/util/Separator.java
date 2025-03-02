package util;

public enum Separator {

    REST(",");

    private final String sign;

    Separator(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

}