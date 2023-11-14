package christmas.util;

import christmas.constants.Menu;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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

    // 입력된 메뉴의 유효성을 판단하는 메서드
    public boolean checkMenuValidity(String input) {
        // 1. 메뉴 형식이 올바른지 확인
        if (!checkValidFormat(input))
            return false;
        // 2. 중복 메뉴를 입력했는지 확인
        if (!checkDuplicateMenu(input))
            return false;
        // 입력을 바탕으로 메뉴이름과 수량을 매핑
        Map<String, Integer> menu = parser.splitInput(input);
        // 3. 메뉴판에 있는 메뉴인지 확인
        if (!checkValidMenus(menu))
            return false;
        // 4. 음료를 제외한 음식을 1개 이상 주문했는지 확인
        if (!hasFoodOtherThanBeverage(menu))
            return false;
        // 5. 메뉴를 한번에 20개를 초과해서 주문했는지 확인
        return checkExceededMaxOrderQuantity(menu);
    }

    // 메뉴 형식이 올바른지 확인하는 메서드: 형식이 다를 시 false 반환
    public boolean checkValidFormat(String input) {
        // 올바른 형식을 지정
        Pattern pattern = Pattern.compile("^(([가-힣]+-(?:[1-9]|1[0-9]|20)),)*[가-힣]+-(?:[1-9]|1[0-9]|20)$");
        return pattern.matcher(input).matches();
    }

    // 중복 메뉴를 입력했는지 확인: 중복 시 false 반환
    public boolean checkDuplicateMenu(String inputs) {
        Set<String> menus = new HashSet<>();
        for (String menu : parser.splitByComma(inputs)) {
            String menuName = menu.split("-")[0];
            if (!menus.add(menuName))
                return false;
        }
        return true;
    }

    // 메뉴판에 있는 메뉴들인지 확인하는 메서드: 메뉴판에 없을 시 false 반환
    public boolean checkValidMenus(Map<String, Integer> menus) {
        for (String menuName : menus.keySet()) {
            if (!checkValidMenu(menuName))
                return false;
        }
        return true;
    }

    // 메뉴판에 있는 메뉴인지 확인하는 메서드: 메뉴판에 없을 시 false 반환
    public boolean checkValidMenu(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(name))
                return true;
        }
        return false;
    }

    // 음료를 제외한 음식을 1개 이상 주문했는지 확인: 음료밖에 없을 시 false 반환
    public boolean hasFoodOtherThanBeverage(Map<String, Integer> menus) {
        for (String menuName : menus.keySet()) {
            if (!menuName.equals(Menu.ZERO_COLA.getName()) && !menuName.equals(Menu.RED_WINE.getName()) && !menuName.equals(Menu.CHAMPAGNE.getName()))
                return true;
        }
        return false;
    }

    // 메뉴를 한번에 20개를 초과해서 주문했는지 확인: 초과 시 false 반환
    public boolean checkExceededMaxOrderQuantity(Map<String, Integer> menus) {
        return menus.values().stream().mapToInt(Integer::intValue).sum() <= 20;
    }
}
