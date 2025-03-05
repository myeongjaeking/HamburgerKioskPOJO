package consumer.process;

import consumer.Consumer;
import consumer.validator.ConsumerValidator;
import io.Input;
import root.RootDto;
import root.RootProcess;

public class ConnectConsumer implements RootProcess {

    @Override
    public void start(RootDto rootDto) {
        System.out.println("접속할 회원 성명을 입력하세요");
        String input = Input.nextLine();

        try {
            Consumer consumer = rootDto.consumer();

            ConsumerValidator.connectValidation(input, rootDto);

            consumer.connect();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}