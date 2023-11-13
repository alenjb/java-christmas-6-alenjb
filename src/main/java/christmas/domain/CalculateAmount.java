package christmas.domain;

import christmas.constants.Menu;

public class CalculateAmount {
    ChristmasDdayEvent christmasDdayEvent = new ChristmasDdayEvent();
    WeekdaysEvent weekdaysEvent = new WeekdaysEvent();
    WeekendEvent weekendEvent = new WeekendEvent();
    SpecialEvent specialEvent = new SpecialEvent();
    GiveawayEvent giveawayEvent = new GiveawayEvent();


    // 할인 전 총주문 금액을 계산하는 메서드
    public double getTotalAmountBeforeDiscount(Order order) {
        double resultAmount = 0;
        for (int amount : order.getOrderedMenus().values()) {
            resultAmount += amount;
        }
        return resultAmount;
    }

    // 총혜택 금액을 계산하는 메서드
    public double getExpectedPaymentAmount(Order order) {
        double resultAmount = 0;
        for (Event event : order.getValidEvents()) {
            resultAmount += event.calculateDiscountAmount(order);
        }
        return resultAmount;
    }
}
