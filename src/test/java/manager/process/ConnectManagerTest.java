package manager.process;

import manager.Manager;
import manager.validator.ManagerErrorMessage;
import manager.validator.ManagerValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ConnectManagerTest {

    private Manager manager;

    @BeforeEach
    void setUp() {
        manager = new Manager();
        manager.create("관리자1", 10000);
    }

    private void prepare(String input) {
        ManagerValidator.connectValidation(input, manager);
        manager.connect();
    }

    @Test
    @DisplayName("정상적인 입력이면 관리자 접속에 성공한다.")
    void testSuccessConnectManager() {
        String input = "관리자1";

        prepare(input);

        Assertions.assertTrue(manager.isConnect());
    }

    @Test
    @DisplayName("존재하지 않는 관리자 이름으로 접속 시도하면 에러를 발생시킨다.")
    void testConnectNotExistentManager() {
        String input = "관리자2";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ManagerErrorMessage.NOT_EXIST_MANAGER.getMessage());
    }

    @Test
    @DisplayName("이미 접속한 상태에서 다시 접속 시도하면 에러를 발생시킨다.")
    void testConnectAlreadyConnectedManager() {
        manager.connect();
        String input = "관리자1";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ManagerErrorMessage.ALREADY_CONNECT_MANAGER.getMessage());
    }

    @Test
    @DisplayName("입력값에 공백이 존재하면 에러를 발생시킨다.")
    void testContainBlankInput() {
        String input = "  관리자1";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ManagerErrorMessage.NOT_EXIST_MANAGER.getMessage());
    }

}