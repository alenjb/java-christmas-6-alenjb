package christmas.domain;

import christmas.constants.Amount;
import christmas.constants.Day;
import christmas.constants.Giveaway;

import java.util.List;

public class GiveawayEvent implements Event {
    @Override
    public boolean isValidEvent(int reservationDate, int totalAmount) {
        return isValidEventPeriod(reservationDate) && isValidEventAmount(totalAmount);
    }

    @Override
    public boolean isValidEventPeriod(int reservationDate) {
        return Day.EVENT_START_DAY.getDay() <= reservationDate && Day.EVENT_END_DAY.getDay() >= reservationDate;

    }

    @Override
    public boolean isValidEventAmount(int totalAmount) {
        return totalAmount >= Amount.MINIMUM_AMOUNT_FOR_GIVEAWAY_EVENT.amount;
    }

    @Override
    public void addToValidEvents(Order order) {
        order.addValidEvents(List.of(this));
    }


    @Override
    public int calculateDiscountAmount(Order order) {
        return 0;   // 할인 이벤트가 아니므로 0을 반환
    }

    // 증정 혜택 금액 계산 메서드
    public int calculateGiveawayAmount(Order order) {
        return Amount.GIVEAWAY_AMOUNT.amount;   // 증정 이벤트 혜택 금액을 반환
    }

    public void giveaway(Order order) {
        order.setGiveaway(Giveaway.CHAMPAGNE);
    }

    @Override
    public void doEvent(Order order) {
        if (!isValidEvent(order.getReservationDate(), order.getExpectedPaymentAmount())) {
            return;
        }
        addToValidEvents(order);
        giveaway(order);
    }
}
