package manager.process;

import io.Input;
import manager.Manager;
import manager.validator.ManagerValidator;
import root.ManagerConsumerDto;
import root.OptionProcess;

public class ConnectManager implements OptionProcess {

    @Override
    public void start(ManagerConsumerDto managerConsumerDto) {
        System.out.println("접속할 관리자 성명을 입력하세요");
        String input = Input.nextLine();

        try {
            Manager manager = managerConsumerDto.manager();

            ManagerValidator.connectValidation(input, manager);

            manager.connect();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}