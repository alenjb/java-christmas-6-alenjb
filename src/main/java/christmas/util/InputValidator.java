package christmas.util;

import java.util.regex.Pattern;

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

    // 메뉴 형식이 올바른지 확인하는 메서드: 형식이 다를 시 false 반환
    public boolean checkValidFormat(String input) {
        // 올바른 형식을 지정
        Pattern pattern = Pattern.compile("^(([가-힣]+-(?:[1-9]|1[0-9]|20)),)*[가-힣]+-(?:[1-9]|1[0-9]|20)$");
        return pattern.matcher(input).matches();
    }

}
