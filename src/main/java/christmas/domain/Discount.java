package christmas.domain;

import java.util.List;

public abstract class Discount implements Event {
    int discountAmount;

    @Override
    public boolean isValidEvent(int day, int totalAmount) {
        return isValidEventPeriod(day) && isValidEventAmount(totalAmount);
    }

    @Override
    public void addToValidEvents(Order order) {
        order.addValidEvents(List.of(this));
    }

    // 할인을 실시하는 메서드
    abstract void discount(Order order);

    @Override
    public void doEvent(Order order) {
        if (!isValidEvent(order.getReservationDate(), order.getExpectedPaymentAmount())) {
            return;
        }
        addToValidEvents(order);
        discount(order);
    }
}
