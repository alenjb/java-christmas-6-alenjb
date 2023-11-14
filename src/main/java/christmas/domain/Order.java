package christmas.domain;

import christmas.constants.Badge;
import christmas.constants.Giveaway;
import christmas.constants.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Order {
    // 방문 날짜
    private int reservationDate;
    // 적용 가능한 이벤트들
    private List<Event> validEvents = new ArrayList<>();
    // 주문 메뉴들
    private Map<Menu, Integer> orderedMenus;
    // 예상 결제 금액
    private int expectedPaymentAmount;
    // 배지를 NONE으로 초기화
    private Badge badge = Badge.NONE;
    //증정품을 NONE으로 초기화
    private Giveaway giveaway = Giveaway.NONE;

    public int getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(int reservationDate) {
        this.reservationDate = reservationDate;
    }

    public List<Event> getValidEvents() {
        return validEvents;
    }

    public void setValidEvents(List<Event> validEvents) {
        this.validEvents = validEvents;
    }

    public Map<Menu, Integer> getOrderedMenus() {
        return orderedMenus;
    }

    public void setOrderedMenus(Map<Menu, Integer> orderedMenus) {
        this.orderedMenus = orderedMenus;
    }

    public int getExpectedPaymentAmount() {
        return expectedPaymentAmount;
    }

    public void setExpectedPaymentAmount(int expectedPaymentAmount) {
        this.expectedPaymentAmount = expectedPaymentAmount;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public Giveaway getGiveaway() {
        return giveaway;
    }

    public void setGiveaway(Giveaway giveaway) {
        this.giveaway = giveaway;
    }
}
