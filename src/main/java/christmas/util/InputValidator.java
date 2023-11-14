package christmas.util;

public class InputValidator {
    static Parser parser = new Parser();

    private static boolean checkValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.\n");
            return false;
        }
    }

    public boolean checkDateValidity(String input) {
        int inputNumber;
        // 정수 숫자인지 확인
        if (checkValidInteger(input)) {
            inputNumber = Integer.parseInt(input);
            // 1~31내의 숫자인지 확인
            return inputNumber >= 1 && inputNumber <= 31;
        }
        return false;
    }
}
