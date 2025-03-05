package order;

import consumer.Consumer;
import io.Input;
import manager.Manager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import root.RootDto;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

class OrderTest {

    private final InputStream systemIn = System.in;
    private Consumer consumer;
    private Manager manager;
    private RootDto rootDto;
    private Order order;

    private void prepareInput(String input){
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @BeforeEach
    void setUp() throws FileNotFoundException {
        consumer = new Consumer();
        consumer.create(1, 10000);

        manager = new Manager();
        manager.create("관리자1", 10000);

        rootDto = new RootDto(manager, consumer);
        order = Order.getInstance(rootDto);
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