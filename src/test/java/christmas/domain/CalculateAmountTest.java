package christmas.domain;

import camp.nextstep.edu.missionutils.Randoms;
import christmas.constant.AmountForTest;
import christmas.constant.DayForTest;
import christmas.constants.Day;
import christmas.constants.Menu;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CalculateAmountTest {
    static final int NONE = 0;
    static final CalculateAmount CALCULATE_AMOUNT = new CalculateAmount();
    static final Order ORDER = new Order();
    ChristmasDdayEvent christmasDdayEvent = new ChristmasDdayEvent();
    WeekdaysEvent weekdaysEvent = new WeekdaysEvent();
    WeekendEvent weekendEvent = new WeekendEvent();
    SpecialEvent specialEvent = new SpecialEvent();
    GiveawayEvent giveawayEvent = new GiveawayEvent();

    @BeforeAll
    static void init() {
        // 방문 날짜를 설정
        ORDER.setReservationDate(Randoms.pickNumberInRange(Day.EVENT_START_DAY.getDay(), Day.EVENT_END_DAY.getDay()));
        // 메뉴들을 주문
        Map<Menu, Integer> menus = Map.of(Menu.BBQ_RIBS, 6, Menu.CAESAR_SALAD, 4, Menu.CHOCOLATE_CAKE, 3);
        ORDER.addOrderedMenus(menus);
    }

    @BeforeEach
    void foreach() {
        ORDER.setExpectedPaymentAmount(0);
        ORDER.setValidEvents(new ArrayList<>());

    }

    @Test
    @DisplayName("할인 전 총주문 금액 계산 테스트")
    void getTotalAmountBeforeDiscount() {
        int expectedResult = 0;
        for (Menu menu : ORDER.getOrderedMenus().keySet()) {
            if (ORDER.getOrderedMenus().get(menu) != 0) { // 주문 수량이 있으면
                expectedResult += menu.getPrice() * ORDER.getOrderedMenus().get(menu);
            }
        }
        assertEquals(expectedResult, CALCULATE_AMOUNT.getTotalAmountBeforeDiscount(ORDER));
    }

    @Test
    @DisplayName("총할인 금액 계산 테스트")
    void getTotalDiscountAmount() {
        int expectedResult = 0;
        // 주문 메뉴를 바탕으로 총 주문 금액 설정
        ORDER.setExpectedPaymentAmount(CALCULATE_AMOUNT.getTotalAmountBeforeDiscount(ORDER));
        christmasDdayEvent.doEvent(ORDER);
        weekdaysEvent.doEvent(ORDER);
        weekendEvent.doEvent(ORDER);
        specialEvent.doEvent(ORDER);
        giveawayEvent.doEvent(ORDER);

        for (Event event : ORDER.getValidEvents()) {
            expectedResult += event.calculateDiscountAmount(ORDER);
        }
        assertEquals(expectedResult, CALCULATE_AMOUNT.getTotalDiscountAmount(ORDER));
    }

    @Test
    @DisplayName("총혜택 금액 계산 테스트")
    void getTotalBenefits() {
        int expectedResult = 0;
        ORDER.setReservationDate(4);

        // 주문 메뉴를 바탕으로 총 주문 금액 설정
        ORDER.setExpectedPaymentAmount(CALCULATE_AMOUNT.getTotalAmountBeforeDiscount(ORDER));
        christmasDdayEvent.doEvent(ORDER);
        weekdaysEvent.doEvent(ORDER);
        weekendEvent.doEvent(ORDER);
        specialEvent.doEvent(ORDER);
        giveawayEvent.doEvent(ORDER);
        for (Event event : ORDER.getValidEvents()) {
            expectedResult += event.calculateDiscountAmount(ORDER);
            if (event instanceof GiveawayEvent)
                expectedResult += ((GiveawayEvent) event).calculateGiveawayAmount(ORDER);
        }
        assertEquals(expectedResult, CALCULATE_AMOUNT.getTotalBenefits(ORDER));
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액 계산 테스트")
    void getExpectedTotalPaymentAmount() {
        // 주문 메뉴를 바탕으로 총 주문 금액 설정
        ORDER.setExpectedPaymentAmount(CALCULATE_AMOUNT.getTotalAmountBeforeDiscount(ORDER));
        christmasDdayEvent.doEvent(ORDER);
        weekdaysEvent.doEvent(ORDER);
        weekendEvent.doEvent(ORDER);
        specialEvent.doEvent(ORDER);
        giveawayEvent.doEvent(ORDER);
        int expectedResult = CALCULATE_AMOUNT.getTotalAmountBeforeDiscount(ORDER) - CALCULATE_AMOUNT.getTotalDiscountAmount(ORDER);
        assertEquals(expectedResult, CALCULATE_AMOUNT.getExpectedTotalPaymentAmount(ORDER));
    }
}