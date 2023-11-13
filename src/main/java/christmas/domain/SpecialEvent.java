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
    public boolean isValidEventAmount(double totalAmount) {
        return totalAmount >= Amount.MINIMUM_AMOUNT_FOR_EVENT.amount;
    }

    public double calculateDiscountAmount(Order order) {
        return Amount.SPECIAL_DISCOUNT_AMOUNT.amount;
    }
}
