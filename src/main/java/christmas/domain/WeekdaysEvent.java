package christmas.domain;

import christmas.constants.Amount;
import christmas.constants.Day;

public class WeekdaysEvent extends Discount implements Event {

    @Override
    public boolean isValidEventPeriod(int day) {
        return Day.WEEKDAYS.contains(day);
    }

    @Override
    public boolean isValidEventAmount(double totalAmount) {
        return totalAmount >= Amount.MINIMUM_AMOUNT_FOR_EVENT.amount;
    }
}
