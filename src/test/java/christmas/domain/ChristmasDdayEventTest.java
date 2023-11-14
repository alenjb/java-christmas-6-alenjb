package christmas.domain;

import christmas.constant.AmountForTest;
import christmas.constant.DayForTest;
import christmas.constants.Amount;
import christmas.constants.Day;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChristmasDdayEventTest {
    final ChristmasDdayEvent CHRISTMAS_DDAY_EVENT = new ChristmasDdayEvent();
    final Order ORDER = new Order();

    @Test
    @DisplayName("예약 날짜가 이벤트 기간인 경우 테스트")
    void isValidEventPeriod() {
        assertTrue(CHRISTMAS_DDAY_EVENT.isValidEventPeriod(DayForTest.CHRISTMAS_D_DAY_EVENT_VALID_DAY.getDay()));
    }

    @Test
    @DisplayName("예약 날짜가 이벤트 기간이 아닌 경우 테스트")
    void isInvalidEventPeriod() {
        assertFalse(CHRISTMAS_DDAY_EVENT.isValidEventPeriod(DayForTest.CHRISTMAS_D_DAY_EVENT_INVALID_DAY.getDay()));
    }

    @Test
    @DisplayName("주문 금액이 이벤트 최소 주문 금액을 만족하는 경우 테스트")
    void isValidEventAmount() {
        assertTrue(CHRISTMAS_DDAY_EVENT.isValidEventAmount(AmountForTest.VALID_AMOUNT_FOR_EVENT.amount));
    }

    @Test
    @DisplayName("주문 금액이 이벤트 최소 주문 금액을 만족하지 않는 경우 테스트")
    void isInValidEventAmount() {
        assertFalse(CHRISTMAS_DDAY_EVENT.isValidEventAmount(AmountForTest.INVALID_AMOUNT_FOR_EVENT.amount));
    }

    @Test
    @DisplayName("이벤트 혜택 금액 계산 테스트")
    void calculateDiscountAmount() {
        // 예약 날짜 설정
        int reservationDate = DayForTest.CHRISTMAS_D_DAY_EVENT_VALID_DAY.getDay();
        ORDER.setReservationDate(reservationDate);
        // 올바른 혜택 금액 계산
        int expectedResult = Amount.INITIAL_CHRISTMAS_DDAY_DISCOUNT_AMOUNT.amount +
                (reservationDate - Day.CHRISTMAS_D_DAY_EVENT_START_DAY.getDay()) *
                Amount.CHRISTMAS_DDAY_DISCOUNT_INCREMENT_AMOUNT_BY_DAY.amount;
        // calculateDiscountAmount를 이용한 혜택 금액 계산
        CHRISTMAS_DDAY_EVENT.calculateDiscountAmount(ORDER);
        assertEquals(CHRISTMAS_DDAY_EVENT.discountAmount, expectedResult);
    }

    @Test
    @DisplayName("이벤트 금액 차감 테스트")
    void discount() {
        // 예약 날짜 설정
        int reservationDate = DayForTest.CHRISTMAS_D_DAY_EVENT_VALID_DAY.getDay();
        ORDER.setReservationDate(reservationDate);
        // 올바른 혜택 금액 계산
        int expectedResult = ORDER.getExpectedPaymentAmount() -
                (Amount.INITIAL_CHRISTMAS_DDAY_DISCOUNT_AMOUNT.amount +
                (reservationDate - Day.CHRISTMAS_D_DAY_EVENT_START_DAY.getDay()) *
                Amount.CHRISTMAS_DDAY_DISCOUNT_INCREMENT_AMOUNT_BY_DAY.amount);
        // calculateDiscountAmount를 이용한 혜택 금액 계산
        CHRISTMAS_DDAY_EVENT.discount(ORDER);
        assertEquals(ORDER.getExpectedPaymentAmount(), expectedResult);
    }
}