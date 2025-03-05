package manager;


public class Manager {

    private String name;
    private int money;
    private boolean isConnect;

    public Manager() {
    }

    public void create(String name, int money) {
        this.name = name;
        this.money = money;
        this.isConnect = false;
    }

    public void sell(int money) {
        this.money += money;
    }

    public void connect() {
        this.isConnect = true;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public boolean isConnect() {
        return isConnect;
    }

}