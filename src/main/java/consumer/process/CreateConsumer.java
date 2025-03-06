package consumer.process;

import consumer.Consumer;
import consumer.validator.ConsumerValidator;
import io.Input;
import root.ManagerConsumerDto;
import root.OptionProcess;
import util.Separator;

public class CreateConsumer implements OptionProcess {

    @Override
    public void start(ManagerConsumerDto managerConsumerDto) {
        System.out.println("회원의 정보를 입력해주세요 ex) 1, 30000");
        String input = Input.nextLine();

        try {
            String[] idAndMoney = input.split(Separator.REST.getSign());

            ConsumerValidator.createValidation(idAndMoney);

            int id = Integer.parseInt(idAndMoney[0].trim());
            int money = Integer.parseInt(idAndMoney[1].trim());

            Consumer consumer = managerConsumerDto.consumer();
            consumer.create(id, money);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}