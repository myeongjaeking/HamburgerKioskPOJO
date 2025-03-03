package consumer.process;

import consumer.Consumer;
import consumer.validator.ConsumerErrorMessage;
import consumer.validator.ConsumerValidator;
import manager.Manager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import root.RootDto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ConnectConsumerTest {

    private Consumer consumer;
    private Manager manager;
    private RootDto rootDto;

    @BeforeEach
    void setUp() {
        consumer = new Consumer();
        consumer.create("회원", 10000);

        manager = new Manager();
        manager.create("관리자1", 10000);

        rootDto = new RootDto(manager, consumer);
    }

    private void prepare(String input) {
        ConsumerValidator.connectValidation(input, rootDto);
        consumer.connect();
    }

    @Test
    @DisplayName("정상적인 입력이면 회원 접속에 성공한다.")
    void testSuccessConnectConsumer() {
        manager.connect();
        String input = "회원";

        prepare(input);

        Assertions.assertTrue(consumer.isConnect());
    }

    @Test
    @DisplayName("존재하지 않는 회원 이름으로 접속 시도하면 에러를 발생시킨다.")
    void testConnectNotExistentConsumer() {
        manager.connect();
        String input = "회원2";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ConsumerErrorMessage.NOT_EXIST_CONSUMER.getMessage());
    }

    @Test
    @DisplayName("이미 접속한 상태에서 다시 접속 시도하면 에러를 발생시킨다.")
    void testConnectAlreadyConnectedConsumer() {
        consumer.connect();
        String input = "회원";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ConsumerErrorMessage.ALREADY_CONNECT_CONSUMER.getMessage());
    }

    @Test
    @DisplayName("입력값에 공백이 존재하면 에러를 발생시킨다.")
    void testContainBlankInput() {
        String input = "  회원";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ConsumerErrorMessage.NOT_EXIST_CONSUMER.getMessage());
    }

    @Test
    @DisplayName("관리자가 접속 안 한 상태에서 회원 접속 시 에러를 발생시킨다.")
    void testIsConnectManager() {

        String input = "회원";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ConsumerErrorMessage.NOT_CONNECTED_MANAGER.getMessage());
    }

}