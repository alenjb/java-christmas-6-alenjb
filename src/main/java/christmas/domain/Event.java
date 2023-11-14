package christmas.domain;

import java.util.List;

public interface Event {
    // 유효한 이벤트인지 확인하는 메서드
    boolean isValidEvent(int day, int totalAmount);

    // 이벤트 기간인지 확인하는 메서드
    boolean isValidEventPeriod(int day);

    // 이벤트 가능 금액인지 확인하는 메서드
    boolean isValidEventAmount(int totalAmount);

    // 유효한 이벤트 리스트에 해당 이벤트를 추가하는 메서드
    void addToValidEvents(Order order);

    // 이벤트의 할인 금액을 계산하는 메서드
    public int calculateDiscountAmount(Order order);
    
    // 이벤트를 실행하는 메서드
    void doEvent(Order order);
}
