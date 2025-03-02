package manager;


public class Manager {

    private String name;
    private int money;

    public Manager() {
    }

    public void create(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

}