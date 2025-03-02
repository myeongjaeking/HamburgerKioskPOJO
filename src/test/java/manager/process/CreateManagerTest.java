package manager.process;

import manager.Manager;
import manager.validator.ManagerErrorMessage;
import manager.validator.ManagerValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Separator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CreateManagerTest {

    Manager manager = new Manager();

    private void prepare(String input) {
        String[] nameAndMoney = input.split(Separator.REST.getSign());
        ManagerValidator.createValidation(nameAndMoney);
        String name = nameAndMoney[0].trim();
        int money = Integer.parseInt(nameAndMoney[1].trim());
        manager.create(name, money);
    }


    @Test
    @DisplayName("정상적인 입력이면 관리자 생성 성공한다.")
    void testSuccessCreateManager() {
        String input = "관리자,1";

        prepare(input);

        Assertions.assertEquals(manager.getName(), "관리자");
        Assertions.assertEquals(manager.getMoney(), 1);
    }

    @Test
    @DisplayName("공백이 있어도 관리자 생성 성공한다.")
    void testBlankInputCreateManager() {
        String input = " 관리자,    1";

        prepare(input);

        Assertions.assertEquals(manager.getName(), "관리자");
        Assertions.assertEquals(manager.getMoney(), 1);
    }

    @Test
    @DisplayName("돈이 음수일 때 관리자 생성 시 에러를 발생시킨다.")
    void testValidateNegative() {
        String input = " 관리자1, -1";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(NumberFormatException.class)
                .hasMessage(ManagerErrorMessage.INVALID_NEGATIVE_NUMBER.getMessage());
    }

    @Test
    @DisplayName("입력 문자열에 구분자가 2개 이상이면 에러를 발생시킨다.")
    void testValidateInputLength() {
        String input = " 관리자1,1,3";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ManagerErrorMessage.NOT_FIT_FORMAT_CREATE_MANAGER.getMessage());
    }

    @Test
    @DisplayName("입력 문자열에서 ,기준 뒷 문자열이 숫자로 변환 불가능하면 에러를 발생시킨다.")
    void testValidateNumberFormat() {
        String input = " 관리자1,  a";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ManagerErrorMessage.NOT_FIT_FORMAT_CREATE_MANAGER.getMessage());
    }

    @Test
    @DisplayName("입력 문자열에서 ,기준 뒷 문자열이 소숫점이 존재하면 에러를 발생시킨다.")
    void testHaveDecimalPoint() {
        String input = " 관리자1,  10.0";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ManagerErrorMessage.NOT_FIT_FORMAT_CREATE_MANAGER.getMessage());
    }

    @Test
    @DisplayName("입력 문자열에서 , 이름만 가질 경우 에러를 발생시킨다.")
    void testHaveOnlyName() {
        String input = " 관리자1";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ManagerErrorMessage.NOT_FIT_FORMAT_CREATE_MANAGER.getMessage());
    }

    @Test
    @DisplayName("입력 문자열에서 , 돈만 가질 경우 에러를 발생시킨다.")
    void testHaveOnlyMoney() {
        String input = " 3";

        assertThatThrownBy(() -> prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ManagerErrorMessage.NOT_FIT_FORMAT_CREATE_MANAGER.getMessage());
    }

}