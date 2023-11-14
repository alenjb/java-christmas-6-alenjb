package christmas.domain;

import java.util.List;

public class CalculateAmount {
    ChristmasDdayEvent christmasDdayEvent = new ChristmasDdayEvent();
    WeekdaysEvent weekdaysEvent = new WeekdaysEvent();
    WeekendEvent weekendEvent = new WeekendEvent();
    SpecialEvent specialEvent = new SpecialEvent();
    GiveawayEvent giveawayEvent = new GiveawayEvent();

    // 할인 전 총주문 금액을 계산하는 메서드
    public int getTotalAmountBeforeDiscount(Order order) {
        int resultAmount = 0;
        for (int amount : order.getOrderedMenus().values()) {
            resultAmount += amount;
        }
        return resultAmount;
    }

    // 총혜택 금액을 계산하는 메서드
    public int getTotalDiscountAmount(Order order) {
        int resultAmount = 0;
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

    // 할인 후 예상 결제 금액을 계산하는 메서드
    public int getExpectedTotalPaymentAmount(Order order) {
        int resultAmount = 0;
        resultAmount = getTotalAmountBeforeDiscount(order) - getTotalDiscountAmount(order);
        // 증정 이벤트가 적용 이벤트들에 포함된 경우
        if (hasGiveawayEvent(order.getValidEvents()))
            resultAmount -= giveawayEvent.calculateDiscountAmount(order);   // 증정 이벤트 혜택은 실제 할인 금액이 아니므로 할인 금액에서 제외함
        return resultAmount;
    }
}
