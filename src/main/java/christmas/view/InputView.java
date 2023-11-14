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
    final int INVALID = -1;

    public int readDate() {
        System.out.println(Message.GET_DATE_MSG.getMessage());
        String input = Console.readLine();
        if (VALIDATOR.checkDateValidity(input))
            return Integer.parseInt(input);
        return INVALID;
    }

    public Map<Menu, Integer> readMenus() {
        System.out.println(Message.GET_MENU_MSG.getMessage());
        String input = Console.readLine();
        // 입력값이 유효하면
        if (VALIDATOR.checkMenuValidity(input))
            // 메뉴를 파싱해서 반환
            return PARSER.convertToMenuFormat(PARSER.splitInput(input));
        return null;
    }
}