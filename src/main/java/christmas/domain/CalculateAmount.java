package christmas.domain;

import christmas.constants.Menu;

public class CalculateAmount {
    ChristmasDdayEvent christmasDdayEvent = new ChristmasDdayEvent();
    WeekdaysEvent weekdaysEvent = new WeekdaysEvent();
    WeekendEvent weekendEvent = new WeekendEvent();
    SpecialEvent specialEvent = new SpecialEvent();
    GiveawayEvent giveawayEvent = new GiveawayEvent();


    // 할인 전 총주문 금액을 계산한다.
    public double calculateTotalAmountBeforeDiscount(Order order) {
        double resultAmount = 0;
        for (int amount : order.getOrderedMenus().values()) {
            resultAmount += amount;
        }
        return resultAmount;
    }
}
