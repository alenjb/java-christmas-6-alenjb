package christmas.view;

import christmas.constants.Giveaway;
import christmas.constants.Menu;
import christmas.constants.Message;
import christmas.domain.*;

public class OutputView {
    final CalculateAmount CALCULATOR = new CalculateAmount();

    public void greet() {
        System.out.println(Message.GREETING_MSG.getMessage());
    }

    // 주문 메뉴를 출력하는 메서드
    public void printOrderedMenus(Order order) {
        System.out.println(Message.MENU_MSG.getMessage());
        for (Menu menu : order.getOrderedMenus().keySet()) {
            if (order.getOrderedMenus().get(menu) != 0)
                System.out.println(menu.getName() + " " + order.getOrderedMenus().get(menu) + "개");
        }
        System.out.println();
    }

    // 할인 전 총주문 금액을 출력하는 메서드
    public void printTotalAmountBeforeDiscount(Order order) {
        System.out.println(Message.TOTAL_AMOUNT_BEFORE_DISCOUNT_MSG.getMessage());
        System.out.println(String.format("%,d", CALCULATOR.getTotalAmountBeforeDiscount(order)) + "원");
        System.out.println();
    }

    // 증정 메뉴를 출력하는 메서드
    public void printGiveaway(Order order) {
        System.out.println(Message.GIVEAWAY_MSG.getMessage());
        if (order.getGiveaway().equals(Giveaway.CHAMPAGNE)) {
            System.out.println(Giveaway.CHAMPAGNE.getName() + " 1개");
            return;
        }
        System.out.println(Giveaway.NONE.getName());
        System.out.println();
    }

    // 혜택 내역을 출력하는 메서드
    public void printBenefits(Order order) {
        System.out.println(Message.BENEFITS_MSG.getMessage());
        for (Event event : order.getValidEvents()) {
            if (event instanceof ChristmasDdayEvent)
                System.out.println(Message.CHRISTMAS_DDAY_EVENT_MSG.getMessage() + String.format(" -%,d", event.calculateDiscountAmount(order)) + "원");
            if (event instanceof WeekdaysEvent)
                System.out.println(Message.WEEKDAYS_EVENT_MSG.getMessage() + String.format(" -%,d", event.calculateDiscountAmount(order)) + "원");
            if (event instanceof WeekendEvent)
                System.out.println(Message.WEEKEND_EVENT_MSG.getMessage() + String.format(" -%,d", event.calculateDiscountAmount(order)) + "원");
            if (event instanceof SpecialEvent)
                System.out.println(Message.SPECIAL_EVENT_MSG.getMessage() + String.format(" -%,d", event.calculateDiscountAmount(order)) + "원");
            if (event instanceof GiveawayEvent)
                System.out.println(Message.GIVEAWAY_EVENT_MSG.getMessage() + String.format(" -%,d", ((GiveawayEvent) event).calculateGiveawayAmount(order)) + "원");
        }
        if (order.getValidEvents().isEmpty())
            System.out.println("없음");
        System.out.println();
    }

    // 총혜택 금액을 출력하는 메서드
    public void printTotalBenefits(Order order) {
        System.out.println(Message.TOTAL_BENEFITS_AMOUNT_MSG.getMessage());
        if (CALCULATOR.getTotalBenefits(order) == 0) {
            System.out.println(String.format("%,d", CALCULATOR.getTotalBenefits(order)) + "원");
            System.out.println();
            return;
        }
        System.out.println(String.format("-%,d", CALCULATOR.getTotalBenefits(order)) + "원");
        System.out.println();
    }

    // 할인 후 예상 결제 금액을 출력하는 메서드
    public void printExpectedTotalPaymentAmount(Order order) {
        System.out.println(Message.EXPECTED_TOTAL_PAYMENT_AMOUNT_MSG.getMessage());
        System.out.println(String.format("%,d", CALCULATOR.getExpectedTotalPaymentAmount(order)) + "원");
        System.out.println();
    }

    // 12월 이벤트 배지를 출력하는 메서드
    public void printBadge(Order order) {
        System.out.println(Message.BADGE_MSG.getMessage());
        System.out.println(order.getBadge().getName());
    }

    // 혜택 미리보기 안내 메시지를 출력하는 메서드
    public void printPreviewMsg(Order order) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", order.getReservationDate());
        System.out.println();
    }
}
