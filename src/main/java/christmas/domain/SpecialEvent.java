package christmas.domain;

import christmas.constants.Amount;
import christmas.constants.Day;

public class SpecialEvent extends Discount implements Event {

    @Override
    public boolean isValidEventPeriod(int day) {
        return Day.STAR_DAYS.contains(day);
    }

    @Override
    public boolean isValidEventAmount(int totalAmount) {
        return totalAmount >= Amount.MINIMUM_AMOUNT_FOR_EVENT.amount;
    }

    public int calculateDiscountAmount(Order order) {
        discountAmount = Amount.SPECIAL_DISCOUNT_AMOUNT.amount;
        return discountAmount;
    }

    @Override
    void discount(Order order) {
        order.setExpectedPaymentAmount(order.getExpectedPaymentAmount() - calculateDiscountAmount(order));
    }
}
