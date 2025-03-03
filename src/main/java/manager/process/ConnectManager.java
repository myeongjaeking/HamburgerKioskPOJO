package manager.process;

import io.Input;
import manager.Manager;
import manager.validator.ManagerValidator;
import root.RootDto;
import root.RootProcess;

public class ConnectManager implements RootProcess {

    @Override
    public void start(RootDto rootDto) {
        System.out.println("접속할 관리자 성명을 입력하세요");
        String input = Input.nextLine();

        try {
            Manager manager = rootDto.manager();

            ManagerValidator.connectValidation(input, manager);

            manager.connect();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}