package consumer.process;

import consumer.Consumer;
import consumer.validator.ConsumerValidator;
import io.Input;
import root.ManagerConsumerDto;
import root.OptionProcess;

public class ConnectConsumer implements OptionProcess {

    @Override
    public void start(ManagerConsumerDto managerConsumerDto) {
        System.out.println("접속할 회원 성명을 입력하세요");
        String input = Input.nextLine();

        try {
            Consumer consumer = managerConsumerDto.consumer();

            ConsumerValidator.connectValidation(input, managerConsumerDto);

            consumer.connect();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}