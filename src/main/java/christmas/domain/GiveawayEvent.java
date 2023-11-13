package christmas.domain;

import christmas.constants.Amount;
import christmas.constants.Day;
import christmas.constants.Giveaway;

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

    @Override
    public void addToValidEvents(List<Event> validEvents) {
        validEvents.add(this);
    }


    @Override
    public double calculateDiscountAmount(Order order) {
        return 0;   // 할인 이벤트가 아니므로 0을 반환
    }

    public void giveaway(Order order) {
        order.setGiveaway(Giveaway.CHAMPAGNE);
    }

    @Override
    public void doEvent(Order order) {
        if (!isValidEvent(order.getReservationDate(), order.getExpectedPaymentAmount())) {
            return;
        }
        addToValidEvents(order.getValidEvents());
        giveaway(order);
    }
}
