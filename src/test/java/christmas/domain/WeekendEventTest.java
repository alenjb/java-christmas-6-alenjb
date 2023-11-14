package christmas.domain;

import christmas.constant.AmountForTest;
import christmas.constant.DayForTest;
import christmas.constants.Amount;
import christmas.constants.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WeekendEventTest {
    final WeekendEvent WEEKEND_EVENT = new WeekendEvent();
    final Order ORDER = new Order();

    @Test
    @DisplayName("예약 날짜가 이벤트 기간인 경우 테스트")
    void isValidEventPeriod() {
        assertTrue(WEEKEND_EVENT.isValidEventPeriod(DayForTest.WEEKEND_EVENT_VALID_DAY.getDay()));
    }

    @Test
    @DisplayName("예약 날짜가 이벤트 기간이 아닌 경우 테스트")
    void isInvalidEventPeriod() {
        assertFalse(WEEKEND_EVENT.isValidEventPeriod(DayForTest.WEEKEND_EVENT_INVALID_DAY.getDay()));
    }

    @Test
    @DisplayName("주문 금액이 이벤트 최소 주문 금액을 만족하는 경우 테스트")
    void isValidEventAmount() {
        assertTrue(WEEKEND_EVENT.isValidEventAmount(AmountForTest.VALID_AMOUNT_FOR_EVENT.amount));
    }

    @Test
    @DisplayName("주문 금액이 이벤트 최소 주문 금액을 만족하지 않는 경우 테스트")
    void isInValidEventAmount() {
        assertFalse(WEEKEND_EVENT.isValidEventAmount(AmountForTest.INVALID_AMOUNT_FOR_EVENT.amount));
    }

    @Test
    @DisplayName("주문한 메뉴의 메인 메뉴 개수 테스트")
    void getNumberOfMains() {
        Map<Menu, Integer> menus = new HashMap<>();

        // 1. 메인 메뉴가 없는 경우
        menus = Map.of(Menu.MUSHROOM_SOUP, 2, Menu.CAESAR_SALAD, 4, Menu.CHOCOLATE_CAKE, 1);
        ORDER.addOrderedMenus(menus);
        assertEquals(0, WEEKEND_EVENT.getNumberOfMains(ORDER));

        // 2. 메인 메뉴가 있는 경우
        menus = Map.of(Menu.BBQ_RIBS, 6, Menu.CHRISTMAS_PASTA, 2, Menu.MUSHROOM_SOUP, 2, Menu.CAESAR_SALAD, 4, Menu.CHOCOLATE_CAKE, 1);
        ORDER.addOrderedMenus(menus);
        assertEquals(ORDER.getOrderedMenus().get(Menu.BBQ_RIBS) + ORDER.getOrderedMenus().get(Menu.CHRISTMAS_PASTA) + ORDER.getOrderedMenus().get(Menu.T_BONE_STEAK) + ORDER.getOrderedMenus().get(Menu.SEAFOOD_PASTA), WEEKEND_EVENT.getNumberOfMains(ORDER));
    }

    @Test
    @DisplayName("이벤트 혜택 금액 계산 테스트")
    void calculateDiscountAmount() {
        // 예약 날짜 설정
        int reservationDate = DayForTest.WEEKEND_EVENT_INVALID_DAY.getDay();
        ORDER.setReservationDate(reservationDate);
        // 메뉴 설정
        Map<Menu, Integer> menus = Map.of(Menu.BBQ_RIBS, 6, Menu.T_BONE_STEAK, 10, Menu.MUSHROOM_SOUP, 2, Menu.CAESAR_SALAD, 4, Menu.CHOCOLATE_CAKE, 1);
        ORDER.addOrderedMenus(menus);
        // 올바른 혜택 금액 계산
        int expectedResult = (ORDER.getOrderedMenus().get(Menu.BBQ_RIBS) + ORDER.getOrderedMenus().get(Menu.CHRISTMAS_PASTA) + ORDER.getOrderedMenus().get(Menu.T_BONE_STEAK) + ORDER.getOrderedMenus().get(Menu.SEAFOOD_PASTA)) * Amount.WEEKEND_DISCOUNT_AMOUNT.amount;
        // calculateDiscountAmount를 이용한 혜택 금액 계산
        WEEKEND_EVENT.calculateDiscountAmount(ORDER);
        // 금액 비교
        assertEquals(WEEKEND_EVENT.discountAmount, expectedResult);
    }

    @Test
    @DisplayName("이벤트 금액 차감 테스트")
    void discount() {
        // 예약 날짜 설정
        int reservationDate = DayForTest.WEEKEND_EVENT_INVALID_DAY.getDay();
        ORDER.setReservationDate(reservationDate);
        // 메뉴 설정
        Map<Menu, Integer> menus = Map.of(Menu.BBQ_RIBS, 6, Menu.CHRISTMAS_PASTA, 2, Menu.T_BONE_STEAK, 10, Menu.MUSHROOM_SOUP, 2, Menu.CAESAR_SALAD, 4, Menu.CHOCOLATE_CAKE, 1);
        ORDER.addOrderedMenus(menus);
        // 올바른 예상 결제 금액 계산
        int expectedResult = ORDER.getExpectedPaymentAmount() - (ORDER.getOrderedMenus().get(Menu.BBQ_RIBS) + ORDER.getOrderedMenus().get(Menu.CHRISTMAS_PASTA) + ORDER.getOrderedMenus().get(Menu.T_BONE_STEAK) + ORDER.getOrderedMenus().get(Menu.SEAFOOD_PASTA)) * Amount.WEEKEND_DISCOUNT_AMOUNT.amount;
        // calculateDiscountAmount를 이용한 혜택 금액 계산
        WEEKEND_EVENT.discount(ORDER);
        // 금액 비교
        assertEquals(ORDER.getExpectedPaymentAmount(), expectedResult);
    }
}