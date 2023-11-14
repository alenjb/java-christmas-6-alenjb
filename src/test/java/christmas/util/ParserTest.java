package christmas.util;

import christmas.constants.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    final Parser parser = new Parser();

    @Test
    @DisplayName("메뉴들을 정확히 나누는지 테스트")
    void splitInput() {
        String input = "해산물파스타-2,레드와인-1,초코케이크-1";
        Map<String, Integer> expectedResult = new HashMap<>();
        expectedResult.put("해산물파스타", 2);
        expectedResult.put("레드와인", 1);
        expectedResult.put("초코케이크", 1);
        assertEquals(expectedResult, parser.splitInput(input));
    }

    @Test
    @DisplayName("메뉴들을 쉼표로 정확히 나누는지 테스트")
    void splitByComma() {
        String input = "해산물파스타-2,레드와인-1,초코케이크-1";
        List<String> expectedResult = List.of("해산물파스타-2", "레드와인-1", "초코케이크-1");
        assertEquals(expectedResult, parser.splitByComma(input));
    }

    @Test
    @DisplayName("메뉴이름과 수량을 -로 정확히 나누는지 테스트")
    void splitByHyphen() {
        String input = "해산물파스타-2";
        Map<String, Integer> expectedResult = new HashMap<>();
        expectedResult.put("해산물파스타", 2);
        assertEquals(expectedResult, parser.splitInput(input));
    }

    @Test
    @DisplayName("메뉴들을 메뉴객체를 활용한 맵으로 정확히 바꾸는지 테스트")
    void convertToMenuFormat() {
        Map<String, Integer> inputs = new HashMap<>();
        inputs.put("해산물파스타", 2);
        inputs.put("레드와인", 1);
        inputs.put("초코케이크", 1);
        Map<Menu, Integer> expectedResult = new HashMap<>();
        expectedResult.put(Menu.SEAFOOD_PASTA, 2);
        expectedResult.put(Menu.RED_WINE, 1);
        expectedResult.put(Menu.CHOCOLATE_CAKE, 1);
        assertEquals(expectedResult, parser.convertToMenuFormat(inputs));
    }
}