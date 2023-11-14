package christmas.util;

import camp.nextstep.edu.missionutils.Randoms;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputValidatorTest {
    final InputValidator inputValidator = new InputValidator();

    @Test
    @DisplayName("날짜 입력값의 유효성을 테스트")
    void checkDateValidity() {
        assertTrue(inputValidator.checkDateValidity(Randoms.pickNumberInRange(1, 31) + ""));
        assertFalse(inputValidator.checkDateValidity("aaaa"));
        assertFalse(inputValidator.checkDateValidity("12aaa"));
        assertFalse(inputValidator.checkDateValidity("2.1"));
        assertFalse(inputValidator.checkDateValidity("35"));
    }
}