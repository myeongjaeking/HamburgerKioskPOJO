package util;

public enum SetMenu {

    SET("세트"),
    FRENCH_FRIES("감자튀김"),
    COLA("콜라");

    private final String menuName;

    SetMenu(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName() {
        return menuName;
    }

}