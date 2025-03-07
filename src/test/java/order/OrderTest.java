package order;

import consumer.Consumer;
import file.LoadFile;
import io.Input;
import manager.Manager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import root.ManagerConsumerDto;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

class OrderTest {

    private final InputStream systemIn = System.in;
    private Consumer consumer;
    private Manager manager;
    private ManagerConsumerDto managerConsumerDto;
    private LoadFile loadFile;
    private Order order;

    private void prepareInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @BeforeEach
    void setUp() throws FileNotFoundException {
        consumer = new Consumer();
        consumer.create(1, 10000);

        manager = new Manager();
        manager.create("관리자1", 10000);

        loadFile = new LoadFile();

        managerConsumerDto = new ManagerConsumerDto(manager, consumer);
        order = Order.getInstance(managerConsumerDto, loadFile);
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        Input.close();
    }


    @Test
    @DisplayName("정상적인 입력이면 주문이 성공한다.")
    void testSelectMenu() {
        String input = "[치킨버거-2],[불고기버거세트-1]\n";
        prepareInput(input);

        order.start();
    }

}