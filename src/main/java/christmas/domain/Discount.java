package christmas.domain;

import java.util.List;

public abstract class Discount implements Event {
    double discountAmount;

    @Override
    public boolean isValidEvent() {
        return isValidEventPeriod() && isValidEventAmount();
    }

    @Override
    public void addToValidEvents(List<Event> validEvents) {
        validEvents.add(this);
    }

    // 할인을 실시하는 메서드
    abstract void discount();

    @Override
    public void doEvent(List<Event> validEvents) {
        if (isValidEvent()) {
            addToValidEvents(validEvents);
            discount();
        }
    }
}
