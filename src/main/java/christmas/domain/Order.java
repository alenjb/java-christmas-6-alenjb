package christmas.domain;

import christmas.constants.Badge;
import christmas.constants.Giveaway;

import java.util.List;
import java.util.Map;

public class Order {
    // 방문 날짜
    private int reservationDate;
    // 적용 가능한 이벤트들
    private List<Event> validEvents;
    // 주문 메뉴들
    private Map<String, Integer> orderedMenus;
    // 예상 결제 금액
    private double expectedPaymentAmount;
    // 배지
    private Badge badge;
    //증정품
    private Giveaway giveaway;


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

    public Map<String, Integer> getOrderedMenus() {
        return orderedMenus;
    }

    public void setOrderedMenus(Map<String, Integer> orderedMenus) {
        this.orderedMenus = orderedMenus;
    }

    public double getExpectedPaymentAmount() {
        return expectedPaymentAmount;
    }

    public void setExpectedPaymentAmount(double expectedPaymentAmount) {
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
