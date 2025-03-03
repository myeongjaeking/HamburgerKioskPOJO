package consumer.process;

import consumer.Consumer;
import consumer.validator.ConsumerErrorMessage;
import consumer.validator.ConsumerValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Separator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CreateConsumerTest {

    Consumer consumer = new Consumer();

    private void prepare(String input) {
        String[] nameAndMoney = input.split(Separator.REST.getSign());
        ConsumerValidator.createValidation(nameAndMoney);
        int id = Integer.parseInt(nameAndMoney[0].trim());
        int money = Integer.parseInt(nameAndMoney[1].trim());
        consumer.create(id, money);
    }


    @Test
    @DisplayName("정상적인 입력이면 회원 생성 성공한다.")
    void testSuccessCreateConsumer() {
        String input = "1,1";

        prepare(input);

        Assertions.assertEquals(consumer.getId(), 1);
        Assertions.assertEquals(consumer.getMoney(), 1);
    }

    @Test
    @DisplayName("공백이 있어도 회원 생성 성공한다.")
    void testBlankInputCreateConsumer() {
        String input = " 1,    40000";

        prepare(input);

        Assertions.assertEquals(consumer.getId(), 1);
        Assertions.assertEquals(consumer.getMoney(), 40000);
    }

    @Test
    @DisplayName("돈이 음수일 때 회원 생성 시 에러를 발생시킨다.")
    void testValidateNegative() {
        String input = " 1, -1";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(NumberFormatException.class)
                .hasMessage(ConsumerErrorMessage.INVALID_NEGATIVE_NUMBER.getMessage());
    }

    @Test
    @DisplayName("입력 문자열에 구분자가 2개 이상이면 에러를 발생시킨다.")
    void testValidateInputLength() {
        String input = " 1,1,3";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ConsumerErrorMessage.NOT_FIT_FORMAT_CREATE_CONSUMER.getMessage());
    }

    @Test
    @DisplayName("입력 문자열에서 ,기준 뒷 문자열이 숫자로 변환 불가능하면 에러를 발생시킨다.")
    void testValidateNumberFormat() {
        String input = " 1,  a";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ConsumerErrorMessage.NOT_FIT_FORMAT_CREATE_CONSUMER.getMessage());
    }

    @Test
    @DisplayName("입력 문자열에서 , 고유번호 문자열이 숫자로 변환 불가능하면 에러를 발생시킨다.")
    void testValidateIdFormat() {
        String input = " a,  100000";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ConsumerErrorMessage.NOT_FIT_FORMAT_CREATE_CONSUMER.getMessage());
    }

    @Test
    @DisplayName("입력 문자열에서 ,기준 뒷 문자열이 소숫점이 존재하면 에러를 발생시킨다.")
    void testHaveDecimalPoint() {
        String input = " 1,  10.0";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ConsumerErrorMessage.NOT_FIT_FORMAT_CREATE_CONSUMER.getMessage());
    }

    @Test
    @DisplayName("입력 문자열에서 , 이름만 가질 경우 에러를 발생시킨다.")
    void testHaveOnlyName() {
        String input = " 1";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ConsumerErrorMessage.NOT_FIT_FORMAT_CREATE_CONSUMER.getMessage());
    }

    @Test
    @DisplayName("입력 문자열에서 , 돈만 가질 경우 에러를 발생시킨다.")
    void testHaveOnlyMoney() {
        String input = " 3";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ConsumerErrorMessage.NOT_FIT_FORMAT_CREATE_CONSUMER.getMessage());
    }

}