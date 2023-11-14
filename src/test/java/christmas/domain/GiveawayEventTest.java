package christmas.domain;

import christmas.constant.AmountForTest;
import christmas.constant.DayForTest;
import christmas.constants.Amount;
import christmas.constants.Giveaway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GiveawayEventTest {
    final GiveawayEvent GIVEAWAY_EVENT = new GiveawayEvent();
    final Order ORDER = new Order();
    final int NONE = 0;
    int validDate = DayForTest.EVENT_VALID_DAY.getDay();
    int invalidDate = DayForTest.EVENT_INVALID_DAY.getDay();
    int validAmount = AmountForTest.VALID_AMOUNT_FOR_GIVEAWAY_EVENT.amount;
    int invalidAmount = AmountForTest.INVALID_AMOUNT_FOR_GIVEAWAY_EVENT.amount;

    @Test
    @DisplayName("증정 이벤트를 적용받을 수 있는지 테스트")
    void isValidEvent() {
        // 유효한 경우
        assertTrue(GIVEAWAY_EVENT.isValidEvent(validDate, validAmount));

        // 유효하지 않은 경우
        //1. 유효한 날짜, 유효하지 않은 주문 금액
        assertFalse(GIVEAWAY_EVENT.isValidEvent(validDate, invalidAmount));

        //2. 유효하지 않은 날짜, 유효한 주문 금액
        assertFalse(GIVEAWAY_EVENT.isValidEvent(invalidDate, validAmount));

        //3. 유효하지 않은 날짜, 유효하지 않은 주문 금액
        assertFalse(GIVEAWAY_EVENT.isValidEvent(invalidDate, invalidAmount));
    }

    @Test
    @DisplayName("예약 날짜가 이벤트 기간인 경우 테스트")
    void isValidEventPeriod() {
        assertTrue(GIVEAWAY_EVENT.isValidEventPeriod(DayForTest.EVENT_VALID_DAY.getDay()));
    }

    @Test
    @DisplayName("예약 날짜가 이벤트 기간이 아닌 경우 테스트")
    void isInvalidEventPeriod() {
        assertFalse(GIVEAWAY_EVENT.isValidEventPeriod(DayForTest.EVENT_INVALID_DAY.getDay()));
    }

    @Test
    @DisplayName("주문 금액이 이벤트 최소 주문 금액을 만족하는 경우 테스트")
    void isValidEventAmount() {
        assertTrue(GIVEAWAY_EVENT.isValidEventAmount(AmountForTest.VALID_AMOUNT_FOR_GIVEAWAY_EVENT.amount));
    }

    @Test
    @DisplayName("주문 금액이 이벤트 최소 주문 금액을 만족하지 않는 경우 테스트")
    void isInValidEventAmount() {
        assertFalse(GIVEAWAY_EVENT.isValidEventAmount(AmountForTest.INVALID_AMOUNT_FOR_GIVEAWAY_EVENT.amount));
    }

    @Test
    @DisplayName("이벤트 적용 목록에 증정 이벤트를 정확히 추가하는지 테스트")
    void addToValidEvents() {
        GIVEAWAY_EVENT.addToValidEvents(ORDER);
        assertEquals(ORDER.getValidEvents(), List.of(GIVEAWAY_EVENT));
    }

    @Test
    @DisplayName("이벤트 할인 금액 계산 테스트")
    void calculateDiscountAmount() {
        assertEquals(0, GIVEAWAY_EVENT.calculateDiscountAmount(ORDER));
    }

    @Test
    @DisplayName("이벤트 혜택 금액 계산 테스트")
    void calculateGiveawayAmount() {
        assertEquals(Amount.GIVEAWAY_AMOUNT.amount, GIVEAWAY_EVENT.calculateGiveawayAmount(ORDER));
    }

    @Test
    @DisplayName("증정품 증정 테스트")
    void giveaway() {
        GIVEAWAY_EVENT.giveaway(ORDER);
        assertEquals(Giveaway.CHAMPAGNE, ORDER.getGiveaway());
    }
}