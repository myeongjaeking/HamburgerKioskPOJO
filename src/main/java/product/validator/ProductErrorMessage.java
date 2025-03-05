package product.validator;

public enum ProductErrorMessage {

    OUT_OF_PRODUCT("재고가 부족합니다."),
    NOT_EXIST_PRODUCT("없는 상품입니다."),
    INSUFFICIENT_BALANCE("잔액이 부족합니다."),
    NOT_GOOD_INPUT("입력 형식에 맞지 않습니다.");

    private final String message;

    ProductErrorMessage(String message){
        this.message =message;
    }

    public String getMessage() {
        return message;
    }

}