package christmas.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    final Parser parser = new Parser();

    @Test
    @DisplayName("메뉴들을 쉼표로 정확히 나누는지 테스트")
    void splitByComma() {
        String input = "해산물파스타-2,레드와인-1,초코케이크-1";
        List<String> expectedResult = List.of("해산물파스타-2", "레드와인-1", "초코케이크-1");
        assertEquals(expectedResult, parser.splitByComma(input));
    }
}