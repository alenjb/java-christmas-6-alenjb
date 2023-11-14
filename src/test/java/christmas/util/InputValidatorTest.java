package christmas.util;

import christmas.constants.Menu;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {
    final InputValidator inputValidator = new InputValidator();

    @Test
    void checkDateValidity() {
    }

    @Test
    void checkMenuValidity() {
    }

    @Test
    void checkValidFormat() {
        assertTrue(inputValidator.checkValidFormat("해산물파스타-2,레드와인-1,초코케이크-1"));
        assertTrue(inputValidator.checkValidFormat("해산물파스타-5"));
        assertFalse(inputValidator.checkValidFormat("해산물파스타-2,레드와인-1,초코케이크-1,"));
        assertFalse(inputValidator.checkValidFormat("해산물파스타-2,레드와인-1,초코케이크-1,레드-"));
        assertFalse(inputValidator.checkValidFormat("해산물파스타-2,레드와인-1,초코케이크-1,레드"));
        assertFalse(inputValidator.checkValidFormat("해산물파스타-2,레드와인-1,초코케이크-1 레드-"));
        assertFalse(inputValidator.checkValidFormat("해산물파스타-2,레드와인-1,초코케이크-1레드-"));
        assertFalse(inputValidator.checkValidFormat("해산물파스타-2,"));
        assertFalse(inputValidator.checkValidFormat("해산물파스타2"));

    }

    @Test
    void checkDuplicateMenu() {
    }

    @Test
    void checkValidMenus() {
    }

    @Test
    void checkValidMenu() {
        assertTrue(inputValidator.checkValidMenu(Menu.RED_WINE.getName()));
        assertFalse(inputValidator.checkValidMenu("해산물 파스타"));
    }

    @Test
    void hasFoodOtherThanBeverage() {
    }

    @Test
    void checkExceededMaxOrderQuantity() {
    }
}