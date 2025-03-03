package consumer.process;

import consumer.Consumer;
import consumer.validator.ConsumerValidator;
import io.Input;
import root.RootDto;
import root.RootProcess;
import util.Separator;

public class CreateConsumer implements RootProcess {

    @Override
    public void start(RootDto rootDto) {
        System.out.println("회원의 정보를 입력해주세요 ex) 1, 30000");
        String input = Input.nextLine();

        try {
            String[] nameAndMoney = input.split(Separator.REST.getSign());

            ConsumerValidator.createValidation(nameAndMoney);

            String name = nameAndMoney[0].trim();
            int money = Integer.parseInt(nameAndMoney[1].trim());

            Consumer consumer = rootDto.consumer();
            consumer.create(name, money);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}