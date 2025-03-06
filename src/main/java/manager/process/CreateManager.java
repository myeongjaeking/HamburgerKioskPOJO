package manager.process;

import io.Input;
import manager.Manager;
import manager.validator.ManagerValidator;
import root.ManagerConsumerDto;
import root.OptionProcess;
import util.Separator;

public class CreateManager implements OptionProcess {

    @Override
    public void start(ManagerConsumerDto managerConsumerDto) {
        System.out.println("관리자의 정보를 입력해 주세요 ex) 관리자1, 10000");
        String input = Input.nextLine();

        try {
            String[] nameAndMoney = input.split(Separator.REST.getSign());

            ManagerValidator.createValidation(nameAndMoney);

            String name = nameAndMoney[0].trim();
            int money = Integer.parseInt(nameAndMoney[1].trim());

            Manager manager = managerConsumerDto.manager();
            manager.create(name, money);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}