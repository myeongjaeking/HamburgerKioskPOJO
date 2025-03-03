package manager.process;

import io.Input;
import manager.Manager;
import manager.validator.ManagerValidator;
import root.RootDto;
import root.RootProcess;
import util.Separator;

public class CreateManager implements RootProcess {

    @Override
    public void start(RootDto rootDto) {
        System.out.println("관리자의 정보를 입력해 주세요 ex) 관리자1, 10000");
        String input = Input.nextLine();

        try {
            String[] nameAndMoney = input.split(Separator.REST.getSign());

            ManagerValidator.createValidation(nameAndMoney);

            String name = nameAndMoney[0].trim();
            int money = Integer.parseInt(nameAndMoney[1].trim());

            Manager manager = rootDto.manager();
            manager.create(name, money);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}