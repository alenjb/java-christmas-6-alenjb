package christmas.util;

import christmas.constants.Menu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    // 주문 내용을 각 메뉴이름과 수량으로 분리하여 매핑하는 메서드
    public Map<String, Integer> splitInput(String inputs) {
        Map<String, Integer> menus = new HashMap<>();
        for (String menu : splitByComma(inputs)) {
            menus.putAll(splitByHyphen(menu));
        }
        return menus;
    }

    // 주문 내용을 각 메뉴이름과 수량으로 분리하여 매핑하는 메서드
    public Map<Menu, Integer> convertToMenuFormat(Map<String, Integer> inputs) {
        Map<Menu, Integer> menus = new HashMap<>();

        for (Map.Entry<String, Integer> entry : inputs.entrySet()) {
            Menu menu = Menu.getMenuByName(entry.getKey());
            // 일치하는 메뉴 이름이 Menu 열거형에 있으면
            if (menu != null) {
                menus.put(menu, entry.getValue());
            }
        }

        return menus;
    }

    // 쉼표를 통해 주문 내용을 구분하는 메서드
    public List<String> splitByComma(String inputs) {
        // 쉼표로 문자열을 나누고 리스트로 변환
        String[] valuesArray = inputs.split(",");
        return Arrays.asList(valuesArray);
    }

    // 주문 내용을 주문메뉴와 수량을 구분하는 메서드
    public Map<String, Integer> splitByHyphen(String inputs) {
        Map<String, Integer> resultMap = new HashMap<>();
        // "-"로 문자열을 나누기
        String[] parts = inputs.split("-");
        String key = parts[0].trim();
        try {
            int value = Integer.parseInt(parts[1].trim());
            resultMap.put(key, value);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return resultMap;
    }
}
