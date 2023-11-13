package christmas.domain;

import java.util.List;

public interface Event {
    // 유효한 이벤트인지 확인하는 메서드
    boolean isValidEvent(int day, double totalAmount);

    // 이벤트 기간인지 확인하는 메서드
    boolean isValidEventPeriod(int day);

    // 이벤트 가능 금액인지 확인하는 메서드
    boolean isValidEventAmount(double totalAmount);

    // 유효한 이벤트 리스트에 해당 이벤트를 추가
    void addToValidEvents(List<Event> validEvents);

    // 이벤트를 실행하는 메서드
    void doEvent(Order order);
}
