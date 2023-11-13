package christmas.domain;

import java.util.List;

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
    public double getTotalDiscountAmount(Order order) {
        double resultAmount = 0;
        for (Event event : order.getValidEvents()) {
            resultAmount += event.calculateDiscountAmount(order);
        }
        return resultAmount;
    }

    // 적용 이벤트들에 증정 이벤트가 있는지 확인하는 메서드
    public boolean hasGiveawayEvent(List<Event> validEvents) {
        for (Event event : validEvents) {
            if (event instanceof GiveawayEvent) {
                return true;
            }
        }
        return false;
    }
}
