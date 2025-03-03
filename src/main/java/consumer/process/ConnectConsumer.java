package consumer.process;

import consumer.Consumer;
import consumer.validator.ConsumerValidator;
import io.Input;
import order.Order;
import root.RootDto;
import root.RootProcess;

import java.io.FileNotFoundException;

public class ConnectConsumer implements RootProcess {

    @Override
    public void start(RootDto rootDto) {
        System.out.println("접속할 회원 성명을 입력하세요");
        String input = Input.nextLine();

        try {
            Consumer consumer = rootDto.consumer();

            ConsumerValidator.connectValidation(input, rootDto);

            consumer.connect();
            new Order(rootDto);
        } catch (IllegalArgumentException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}