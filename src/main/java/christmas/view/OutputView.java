package christmas.view;

import christmas.constants.Menu;
import christmas.constants.Message;
import christmas.domain.CalculateAmount;
import christmas.domain.Order;

public class OutputView {
    final CalculateAmount CALCULATOR = new CalculateAmount();

    public void greet() {
        System.out.println(Message.GREETING_MSG);
    }

    // 주문 메뉴를 출력하는 메서드
    public void printOrderedMenus(Order order) {
        System.out.println(Message.MENU_MSG);
        for (Menu menu : order.getOrderedMenus().keySet()) {
            System.out.println(menu.getName() + " " + order.getOrderedMenus().get(menu) + "개");
        }
    }
}
