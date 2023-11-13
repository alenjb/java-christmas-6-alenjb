package christmas.domain;

import christmas.constants.Amount;
import christmas.constants.Day;

public class ChristmasDdayEvent extends Discount implements Event {

    @Override
    public boolean isValidEventPeriod(int reservationDate) {
        return Day.EVENT_START_DAY.getDay() <= reservationDate && Day.EVENT_END_DAY.getDay() >= reservationDate;
    }

    @Override
    public boolean isValidEventAmount(double totalAmount) {
        return totalAmount >= Amount.MINIMUM_AMOUNT_FOR_EVENT.amount;
    }

    public double calculateDiscountAmount(int reservationDate) {
        return Amount.INITIAL_CHRISTMAS_DDAY_DISCOUNT_AMOUNT.amount + Amount.CHRISTMAS_DDAY_DISCOUNT_INCREMENT_AMOUNT_BY_DAY.amount * (reservationDate - Day.CHRISTMAS_D_DAY_EVENT_START_DAY.getDay());
    }

}
