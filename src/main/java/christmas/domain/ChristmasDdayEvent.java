package christmas.domain;

import christmas.constants.Amount;
import christmas.constants.Day;

public class ChristmasDdayEvent extends Discount implements Event {

    @Override
    public boolean isValidEventPeriod(int reservationDate) {
        return Day.CHRISTMAS_D_DAY_EVENT_START_DAY.getDay() <= reservationDate && Day.CHRISTMAS_D_DAY_EVENT_END_DAY.getDay() >= reservationDate;
    }

    @Override
    public boolean isValidEventAmount(double totalAmount) {
        return totalAmount >= Amount.MINIMUM_AMOUNT_FOR_EVENT.amount;
    }

    @Override
    public double calculateDiscountAmount(Order order) {
        return Amount.INITIAL_CHRISTMAS_DDAY_DISCOUNT_AMOUNT.amount + Amount.CHRISTMAS_DDAY_DISCOUNT_INCREMENT_AMOUNT_BY_DAY.amount * (order.getReservationDate() - Day.CHRISTMAS_D_DAY_EVENT_START_DAY.getDay());
    }

    // 주문 금액에서 할인 금액만큼 할인하는 메서드
    @Override
    void discount(Order order) {
        order.setExpectedPaymentAmount(order.getExpectedPaymentAmount() - calculateDiscountAmount(order));
    }
}
