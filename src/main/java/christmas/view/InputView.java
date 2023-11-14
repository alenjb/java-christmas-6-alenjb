package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.Message;
import christmas.util.InputValidator;
import christmas.util.Parser;

public class InputView {
    final InputValidator VALIDATOR = new InputValidator();
    final Parser PARSER = new Parser();
    final int INVALID = -1;

    public int readDate() {
        System.out.println(Message.GET_DATE_MSG);
        String input = Console.readLine();
        if (VALIDATOR.checkDateValidity(input))
            return Integer.parseInt(input);
        return INVALID;
    }
}