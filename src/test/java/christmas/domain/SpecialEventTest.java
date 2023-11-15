package christmas.domain;

import camp.nextstep.edu.missionutils.Randoms;
import christmas.constant.AmountForTest;
import christmas.constant.DayForTest;
import christmas.constants.Amount;
import christmas.constants.Day;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialEventTest {
    final SpecialEvent SPECIAL_EVENT = new SpecialEvent();
    final Order ORDER = new Order();

    @Test
    @DisplayName("예약 날짜가 이벤트 기간인 경우 테스트")
    void isValidEventPeriod() {
        assertTrue(SPECIAL_EVENT.isValidEventPeriod(Day.STAR_DAYS.getDay()));
    }

    @Test
    @DisplayName("예약 날짜가 이벤트 기간이 아닌 경우 테스트")
    void isInvalidEventPeriod() {
        assertFalse(SPECIAL_EVENT.isValidEventPeriod(getInvalidEventDate()));
    }


    @Test
    @DisplayName("주문 금액이 이벤트 최소 주문 금액을 만족하는 경우 테스트")
    void isValidEventAmount() {
        assertTrue(SPECIAL_EVENT.isValidEventAmount(AmountForTest.VALID_AMOUNT_FOR_EVENT.amount));
    }

    @Test
    @DisplayName("주문 금액이 이벤트 최소 주문 금액을 만족하지 않는 경우 테스트")
    void isInValidEventAmount() {
        assertFalse(SPECIAL_EVENT.isValidEventAmount(AmountForTest.INVALID_AMOUNT_FOR_EVENT.amount));
    }

    @Test
    @DisplayName("이벤트 혜택 금액 계산 테스트")
    void calculateDiscountAmount() {
        assertEquals(Amount.SPECIAL_DISCOUNT_AMOUNT.amount, SPECIAL_EVENT.calculateDiscountAmount(ORDER));
    }

    @Test
    @DisplayName("이벤트 금액 차감 테스트")
    void discount() {
        // 예약 날짜 설정
        int reservationDate = DayForTest.WEEKDAYS_EVENT_INVALID_DAY.getDay();
        ORDER.setReservationDate(reservationDate);
        // 올바른 혜택 금액 계산
        int expectedResult = ORDER.getExpectedPaymentAmount() - Amount.SPECIAL_DISCOUNT_AMOUNT.amount;
        // calculateDiscountAmount를 이용한 혜택 금액 계산
        SPECIAL_EVENT.discount(ORDER);
        // 금액 비교
        assertEquals(ORDER.getExpectedPaymentAmount(), expectedResult);

    }

    // 할인 이벤트가 유효하지 않은 예약 날짜를 생성하는 메서드
    int getInvalidEventDate(){
        while (true){
            int date = Randoms.pickNumberInRange(Day.EVENT_START_DAY.getDay(), Day.EVENT_END_DAY.getDay());
            if(!Day.STAR_DAYS.contains(date))
                return date;
        }
    }
}