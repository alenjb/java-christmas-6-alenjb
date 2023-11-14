package christmas.domain;

import christmas.constant.AmountForTest;
import christmas.constant.DayForTest;
import christmas.constants.Amount;
import christmas.constants.Day;
import christmas.constants.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WeekdaysEventTest {
    final WeekdaysEvent WEEKDAYS_EVENT = new WeekdaysEvent();
    final Order ORDER = new Order();

    @Test
    @DisplayName("예약 날짜가 이벤트 기간인 경우 테스트")
    void isValidEventPeriod() {
        assertTrue(WEEKDAYS_EVENT.isValidEventPeriod(DayForTest.WEEKDAYS_EVENT_VALID_DAY.getDay()));
    }

    @Test
    @DisplayName("예약 날짜가 이벤트 기간이 아닌 경우 테스트")
    void isInvalidEventPeriod() {
        assertFalse(WEEKDAYS_EVENT.isValidEventPeriod(DayForTest.WEEKDAYS_EVENT_INVALID_DAY.getDay()));
    }


    @Test
    @DisplayName("주문 금액이 이벤트 최소 주문 금액을 만족하는 경우 테스트")
    void isValidEventAmount() {
        assertTrue(WEEKDAYS_EVENT.isValidEventAmount(AmountForTest.VALID_AMOUNT_FOR_EVENT.amount));
    }

    @Test
    @DisplayName("주문 금액이 이벤트 최소 주문 금액을 만족하지 않는 경우 테스트")
    void isInValidEventAmount() {
        assertFalse(WEEKDAYS_EVENT.isValidEventAmount(AmountForTest.INVALID_AMOUNT_FOR_EVENT.amount));
    }

    @Test
    @DisplayName("주문한 메뉴의 디저트 개수 테스트")
    void getNumberOfDesserts() {
        Map<Menu, Integer> menus = new HashMap<>();

        // 1. 디저트 메뉴가 없는 경우
        menus.put(Menu.BBQ_RIBS, 2);
        menus.put(Menu.CAESAR_SALAD, 4);
        ORDER.setOrderedMenus(menus);
        assertEquals(0, WEEKDAYS_EVENT.getNumberOfDesserts(ORDER));

        // 2. 디저트 메뉴가 있는 경우
        menus.put(Menu.BBQ_RIBS, 2);
        menus.put(Menu.CAESAR_SALAD, 4);
        menus.put(Menu.CHOCOLATE_CAKE, 3);
        ORDER.setOrderedMenus(menus);
        assertEquals(3, WEEKDAYS_EVENT.getNumberOfDesserts(ORDER));
    }

    @Test
    @DisplayName("이벤트 혜택 금액 계산 테스트")
    void calculateDiscountAmount() {
        // 예약 날짜 설정
        int reservationDate = DayForTest.WEEKDAYS_EVENT_INVALID_DAY.getDay();
        ORDER.setReservationDate(reservationDate);
        // 메뉴 설정
        Map<Menu, Integer> menus = new HashMap<>();
        menus.put(Menu.BBQ_RIBS, 2);
        menus.put(Menu.CAESAR_SALAD, 4);
        menus.put(Menu.CHOCOLATE_CAKE, 3);
        menus.put(Menu.ICE_CREAM, 10);
        ORDER.setOrderedMenus(menus);
        // 올바른 혜택 금액 계산
        int expectedResult =  (menus.get(Menu.CHOCOLATE_CAKE) + menus.get(Menu.ICE_CREAM))
                * Amount.WEEKDAYS_DISCOUNT_AMOUNT.amount;
        // calculateDiscountAmount를 이용한 혜택 금액 계산
        WEEKDAYS_EVENT.calculateDiscountAmount(ORDER);
        // 금액 비교
        assertEquals(WEEKDAYS_EVENT.discountAmount, expectedResult);
    }

    @Test
    @DisplayName("이벤트 금액 차감 테스트")
    void discount() {
        // 예약 날짜 설정
        int reservationDate = DayForTest.WEEKDAYS_EVENT_INVALID_DAY.getDay();
        ORDER.setReservationDate(reservationDate);
        // 메뉴 설정
        Map<Menu, Integer> menus = new HashMap<>();
        menus.put(Menu.BBQ_RIBS, 2);
        menus.put(Menu.CAESAR_SALAD, 4);
        menus.put(Menu.CHOCOLATE_CAKE, 12);
        menus.put(Menu.ICE_CREAM, 3);
        ORDER.setOrderedMenus(menus);
        // 올바른 혜택 금액 계산
        int expectedResult = ORDER.getExpectedPaymentAmount() -
                (menus.get(Menu.CHOCOLATE_CAKE) + menus.get(Menu.ICE_CREAM)) *
                Amount.WEEKDAYS_DISCOUNT_AMOUNT.amount;
        // calculateDiscountAmount를 이용한 혜택 금액 계산
        WEEKDAYS_EVENT.discount(ORDER);
        // 금액 비교
        assertEquals(ORDER.getExpectedPaymentAmount(), expectedResult);

    }
}