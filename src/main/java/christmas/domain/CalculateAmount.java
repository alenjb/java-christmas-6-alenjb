package christmas.domain;

import christmas.constants.Menu;

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
        for(Menu menu: order.getOrderedMenus().keySet())
            resultAmount += menu.getPrice() * order.getOrderedMenus().get(menu);
        return resultAmount;
    }

    // 총할인 금액을 계산하는 메서드(증정품 가격 포함X)
    public int getTotalDiscountAmount(Order order) {
        int resultAmount = 0;
        for (Event event : order.getValidEvents()) {
            resultAmount += event.calculateDiscountAmount(order);
        }
        return resultAmount;
    }

    // 총혜택 금액을 계산하는 메서드(증정품 가격 포함O)
    public int getTotalBenefits(Order order) {
        int resultAmount = 0;
        for (Event event : order.getValidEvents()) {
            resultAmount += event.calculateDiscountAmount(order);
            // 증정 이벤트가 포함된 경우 증정품의 가격도 포함
            if(event instanceof GiveawayEvent)
                resultAmount += ((GiveawayEvent) event).calculateGiveawayAmount(order);
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
        return getTotalAmountBeforeDiscount(order) - getTotalDiscountAmount(order);
    }
}
