package christmas.domain;

import christmas.constant.AmountForTest;
import christmas.constant.DayForTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {
    int validDate = DayForTest.WEEKDAYS_EVENT_VALID_DAY.getDay();
    int invalidDate = DayForTest.WEEKDAYS_EVENT_INVALID_DAY.getDay();
    int validAmount = AmountForTest.VALID_AMOUNT_FOR_EVENT.amount;
    int invalidAmount = AmountForTest.INVALID_AMOUNT_FOR_EVENT.amount;
    Order order = new Order();
    Discount event = new WeekdaysEvent();    // 추상클래스의 인스턴스를 주중 할인 이벤트로 생성

    @Test
    @DisplayName("해당 할인 이벤트를 적용받을 수 있는지 테스트")
    void isValidEvent() {
        // 유효한 경우
        assertTrue(event.isValidEvent(validDate, validAmount));

        // 유효하지 않은 경우
        //1. 유효한 날짜, 유효하지 않은 주문 금액
        assertFalse(event.isValidEvent(validDate, invalidAmount));

        //2. 유효하지 않은 날짜, 유효한 주문 금액
        assertFalse(event.isValidEvent(invalidDate, validAmount));

        //3. 유효하지 않은 날짜, 유효하지 않은 주문 금액
        assertFalse(event.isValidEvent(invalidDate, invalidAmount));
    }

    @Test
    @DisplayName("이벤트 적용 목록에 해당 할인 이벤트를 정확히 추가하는지 테스트")
    void addToValidEvents() {
        event.addToValidEvents(order);
        assertEquals(order.getValidEvents(), List.of(event));
    }


    @Test
    @DisplayName("해당 할인 이벤트를 정확히 실행하는지 테스트")
    void doEvent() {
        int originalExpectedPaymentAmount = order.getExpectedPaymentAmount();
        event.doEvent(order);
        assertEquals(order.getExpectedPaymentAmount(), originalExpectedPaymentAmount - event.discountAmount);
    }
}