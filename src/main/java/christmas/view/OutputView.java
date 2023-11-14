package christmas.view;

import christmas.constants.Giveaway;
import christmas.constants.Menu;
import christmas.constants.Message;
import christmas.domain.*;

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

    // 할인 전 총주문 금액을 출력하는 메서드
    public void printTotalAmountBeforeDiscount(Order order) {
        System.out.println(Message.TOTAL_AMOUNT_BEFORE_DISCOUNT_MSG);
        System.out.println(String.format("%,d", CALCULATOR.getTotalAmountBeforeDiscount(order)) + "원");
    }

    // 증정 메뉴를 출력하는 메서드
    public void printGiveaway(Order order) {
        System.out.println(Message.GIVEAWAY_MSG);
        if (order.getGiveaway().equals(Giveaway.CHAMPAGNE)) {
            System.out.println(Giveaway.CHAMPAGNE.name() + " 1개");
            return;
        }
        System.out.println(Giveaway.NONE.name());
    }

    // 혜택 내역을 출력하는 메서드
    public void printBenefits(Order order) {
        for (Event event : order.getValidEvents()) {
            if (event instanceof ChristmasDdayEvent)
                System.out.println(Message.CHRISTMAS_DDAY_EVENT_MSG + String.format(" -%,d", event.calculateDiscountAmount(order)) + "원");
            if (event instanceof WeekdaysEvent)
                System.out.println(Message.WEEKDAYS_EVENT_MSG + String.format(" -%,d", event.calculateDiscountAmount(order)) + "원");
            if (event instanceof WeekendEvent)
                System.out.println(Message.WEEKEND_EVENT_MSG + String.format(" -%,d", event.calculateDiscountAmount(order)) + "원");
            if (event instanceof SpecialEvent)
                System.out.println(Message.SPECIAL_EVENT_MSG + String.format(" -%,d", event.calculateDiscountAmount(order)) + "원");
            if (event instanceof GiveawayEvent)
                System.out.println(Message.GIVEAWAY_MSG + String.format(" -%,d", ((GiveawayEvent) event).calculateGiveawayAmount(order)) + "원");
        }
    }

    // 총혜택 금액을 출력하는 메서드
    public void printTotalBenefits(Order order) {
        System.out.println(Message.TOTAL_BENEFITS_AMOUNT_MSG);
        System.out.println(String.format("-%,d", CALCULATOR.getTotalBenefits(order)) + "원");
    }

    // 할인 후 예상 결제 금액을 출력하는 메서드
    public void printExpectedTotalPaymentAmount(Order order) {
        System.out.println(Message.EXPECTED_TOTAL_PAYMENT_AMOUNT_MSG);
        System.out.println(String.format("%,d", CALCULATOR.getExpectedTotalPaymentAmount(order)) + "원");
    }
}
