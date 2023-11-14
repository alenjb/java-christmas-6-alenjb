package christmas.domain;

import christmas.constants.Amount;
import christmas.constants.Day;
import christmas.constants.Menu;
import christmas.constants.MenuCategory;

public class SpecialEvent extends Discount implements Event {

    @Override
    public boolean isValidEventPeriod(int day) {
        return Day.EVENT_START_DAY.contains(day);
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
