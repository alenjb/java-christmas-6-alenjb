package christmas.domain;

import christmas.constants.Amount;
import christmas.constants.Day;

import java.util.List;

public class GiveawayEvent implements Event {
    @Override
    public boolean isValidEvent(int reservationDate, double totalAmount) {
        return isValidEventPeriod(reservationDate) && isValidEventAmount(totalAmount);
    }

    @Override
    public boolean isValidEventPeriod(int reservationDate) {
        return Day.EVENT_START_DAY.getDay() <= reservationDate && Day.EVENT_END_DAY.getDay() >= reservationDate;

    }

    @Override
    public boolean isValidEventAmount(double totalAmount) {
        return totalAmount >= Amount.MINIMUM_AMOUNT_FOR_GIVEAWAY_EVENT.amount;
    }

}
