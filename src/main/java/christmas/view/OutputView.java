package christmas.view;

import christmas.constants.Giveaway;
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

    // 할인 전 총주문 금액을 출력한다.
    public void printTotalAmountBeforeDiscount(Order order) {
        System.out.println(Message.TOTAL_AMOUNT_BEFORE_DISCOUNT_MSG);
        System.out.println(String.format("%,d", CALCULATOR.getTotalAmountBeforeDiscount(order)) + "원");
    }

    // 증정 메뉴를 출력한다.
    public void printGiveaway(Order order) {
        System.out.println(Message.GIVEAWAY_MSG);
        if (order.getGiveaway().equals(Giveaway.CHAMPAGNE)) {
            System.out.println(Giveaway.CHAMPAGNE.name() + " 1개");
            return;
        }
        System.out.println(Giveaway.NONE.name());
    }
}
