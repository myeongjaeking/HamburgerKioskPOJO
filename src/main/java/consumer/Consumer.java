package consumer;

public class Consumer {

    private int id;
    private int money;
    private boolean isConnect;

    public Consumer() {
    }

    public void create(int id, int money) {
        this.id = id;
        this.money = money;
        this.isConnect = false;
    }

    public void buy(int money) {
        this.money -= money;
    }

    public void connect() {
        this.isConnect = true;
    }

    public int getId() {
        return id;
    }

    public int getMoney() {
        return money;
    }

    public boolean isConnect() {
        return isConnect;
    }

}