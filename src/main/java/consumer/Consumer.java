package consumer;

public class Consumer {

    private String name;
    private int money;
    private boolean isConnect;

    public Consumer() {
    }

    public void create(String name, int money) {
        this.name = name;
        this.money = money;
        this.isConnect = false;
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