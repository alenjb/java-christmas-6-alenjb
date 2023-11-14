package christmas.util;

import camp.nextstep.edu.missionutils.Randoms;
import christmas.constants.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    @DisplayName("메뉴들 입력값의 유효성을 종합 테스트")
    void checkMenuValidity() {
        // 유효한 경우
        // 1. 여러 메뉴를 주문한 경우
        assertTrue(inputValidator.checkMenuValidity("해산물파스타-2,레드와인-1,초코케이크-1"));
        // 2. 한가지 메뉴만 주문한 경우
        assertTrue(inputValidator.checkMenuValidity("해산물파스타-5"));
        // 유효하지 않은 경우
        // 1. 마지막에 쉼표를 추가한 경우
        assertFalse(inputValidator.checkMenuValidity("해산물파스타-2,레드와인-1,초코케이크-1,"));
        // 2. 마지막에 수량을 누락한 경우
        assertFalse(inputValidator.checkMenuValidity("해산물파스타-2,레드와인-1,초코케이크-1,레드-"));
        // 3. 마지막에 수량과 "-"를 누락한 경우
        assertFalse(inputValidator.checkMenuValidity("해산물파스타-2,레드와인-1,초코케이크-1,레드"));
        // 4. 마지막에 수량을 누락하고 쉼표를 누락한 경우
        assertFalse(inputValidator.checkMenuValidity("해산물파스타-2,레드와인-1,초코케이크-1레드-"));
        // 5. 1개의 메뉴를 주문하면서 쉼표를 추가한 경우
        assertFalse(inputValidator.checkMenuValidity("해산물파스타-2,"));
        // 6. 1개의 메뉴를 주문하면서 수량과 "-"를 누락한 경우
        assertFalse(inputValidator.checkMenuValidity("해산물파스타2"));
    }

    @Test
    @DisplayName("메뉴 형식이 올바른지 테스트")
    void checkValidFormat() {
        // 올바른 형식
        // 1. 여러 메뉴를 주문한 경우
        assertTrue(inputValidator.checkValidFormat("해산물파스타-2,레드와인-1,초코케이크-1"));
        // 2. 한가지 메뉴만 주문한 경우
        assertTrue(inputValidator.checkValidFormat("해산물파스타-5"));
        // 올바르지 않은 형식
        // 1. 마지막에 쉼표를 추가한 경우
        assertFalse(inputValidator.checkValidFormat("해산물파스타-2,레드와인-1,초코케이크-1,"));
        // 2. 마지막에 수량을 누락한 경우
        assertFalse(inputValidator.checkValidFormat("해산물파스타-2,레드와인-1,초코케이크-1,레드-"));
        // 3. 마지막에 수량과 "-"를 누락한 경우
        assertFalse(inputValidator.checkValidFormat("해산물파스타-2,레드와인-1,초코케이크-1,레드"));
        // 4. 마지막에 수량을 누락하고 쉼표를 누락한 경우
        assertFalse(inputValidator.checkValidFormat("해산물파스타-2,레드와인-1,초코케이크-1레드-"));
        // 5. 1개의 메뉴를 주문하면서 쉼표를 추가한 경우
        assertFalse(inputValidator.checkValidFormat("해산물파스타-2,"));
        // 6. 1개의 메뉴를 주문하면서 수량과 "-"를 누락한 경우
        assertFalse(inputValidator.checkValidFormat("해산물파스타2"));
    }

    @Test
    @DisplayName("중복 메뉴를 입력했는지 테스트")
    void checkDuplicateMenu() {
        // 중복 메뉴를 입력한 경우
        assertFalse(inputValidator.checkDuplicateMenu("해산물파스타-2,레드와인-1,해산물파스타-1"));
        // 중복 메뉴를 입력하지 않은 경우
        assertTrue(inputValidator.checkDuplicateMenu("해산물파스타-2,레드와인-1,초코케이크-1"));
    }

    @Test
    @DisplayName("메뉴판에 있는 메뉴들인지 테스트")
    void checkValidMenus() {
        Map<String, Integer> inputs = new HashMap<>();
        inputs.put("해산물파스타", 2);
        inputs.put("레드와인", 1);
        inputs.put("초코케이크", 1);
        // 메뉴판에 있는 메뉴들인 경우
        assertTrue(inputValidator.checkValidMenus(inputs));
        inputs.put("당근케이크", 3);
        // 메뉴판에 없는 메뉴들인 경우
        assertFalse(inputValidator.checkValidMenus(inputs));
    }

    @Test
    @DisplayName("메뉴판에 있는 메뉴인지 확인 테스트")
    void checkValidMenu() {
        // 메뉴판에 있는 메뉴인 경우
        assertTrue(inputValidator.checkValidMenu(Menu.RED_WINE.getName()));
        // 메뉴판에 없는 메뉴인 경우
        assertFalse(inputValidator.checkValidMenu("해산물 파스타"));
    }

    @Test
    @DisplayName("음료를 제외한 음식을 1개 이상 주문했는지 테스트")
    void hasFoodOtherThanBeverage() {
        Map<String, Integer> inputs = new HashMap<>();
        inputs.put("레드와인", 1);
        // 음료를 제외한 음식이 없는 경우
        assertFalse(inputValidator.hasFoodOtherThanBeverage(inputs));
        inputs.put("초코케이크", 1);
        // 음료를 제외한 음식을 1개 이상 주문한 경우
        assertTrue(inputValidator.hasFoodOtherThanBeverage(inputs));
    }

    @Test
    @DisplayName("메뉴를 한번에 20개를 초과해서 주문했는지 테스트")
    void checkExceededMaxOrderQuantity() {
        Map<String, Integer> inputs = new HashMap<>();
        inputs.put("해산물파스타", 16);
        inputs.put("레드와인", 4);
        // 20개를 초과하지 않은 경우
        assertTrue(inputValidator.checkExceededMaxOrderQuantity(inputs));
        inputs.put("초코케이크", 5);
        // 20개를 초과한 경우
        assertFalse(inputValidator.checkExceededMaxOrderQuantity(inputs));

    }
}