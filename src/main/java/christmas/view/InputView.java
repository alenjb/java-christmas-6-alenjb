package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.Menu;
import christmas.constants.Message;
import christmas.util.InputValidator;
import christmas.util.Parser;

import java.util.Map;

public class InputView {
    final InputValidator VALIDATOR = new InputValidator();
    final Parser PARSER = new Parser();

    public int readDate() {
        System.out.println(Message.GET_DATE_MSG.getMessage());
        while (true) {
            try {
                Integer input = getIntegerFromInput();
                if (input != null) return input;
            } catch (IllegalArgumentException ignored) {
            }
        }
    }

    private Integer getIntegerFromInput() {
        String input = Console.readLine();
        if (VALIDATOR.checkDateValidity(input))
            return Integer.parseInt(input);
        // 에러 메시지를 출력
        System.out.println(Message.INVALID_DATE_ERROR_MSG.getMessage());
        return null;
    }

    public Map<Menu, Integer> readMenus() {
        System.out.println(Message.GET_MENU_MSG.getMessage());
        while (true) {
            try {
                String input = Console.readLine();
                // 입력값이 유효하면 메뉴를 파싱해서 반환
                if (VALIDATOR.checkMenuValidity(input)) return PARSER.convertToMenuFormat(PARSER.splitInput(input));
                // 에러 메시지를 출력
                System.out.println(Message.INVALID_MENU_ERROR_MSG.getMessage());
            } catch (IllegalArgumentException ignored) {
            }
        }
    }
}