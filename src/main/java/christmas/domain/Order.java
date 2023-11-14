package christmas.domain;

import christmas.constants.Badge;
import christmas.constants.Giveaway;
import christmas.constants.Menu;

import java.util.*;

public class Order {
    final int NONE = 0;
    final CalculateAmount calculator = new CalculateAmount();
    // 방문 날짜
    private int reservationDate;
    // 적용 가능한 이벤트들
    private List<Event> validEvents = new ArrayList<>();
    // 주문 메뉴들
    private Map<Menu, Integer> orderedMenus = new HashMap<>();
    // 예상 결제 금액
    private int expectedPaymentAmount;
    // 배지를 NONE으로 초기화
    private Badge badge = Badge.NONE;
    //증정품을 NONE으로 초기화
    private Giveaway giveaway = Giveaway.NONE;

    public Order() {
        setOrderedMenus(
                Map.ofEntries(
                        // 애피타이저
                        Map.entry(Menu.MUSHROOM_SOUP, NONE),
                        Map.entry(Menu.TAPAS, NONE),
                        Map.entry(Menu.CAESAR_SALAD, NONE),
                        // 메인
                        Map.entry(Menu.T_BONE_STEAK, NONE),
                        Map.entry(Menu.BBQ_RIBS, NONE),
                        Map.entry(Menu.SEAFOOD_PASTA, NONE),
                        Map.entry(Menu.CHRISTMAS_PASTA, NONE),
                        // 디저트
                        Map.entry(Menu.CHOCOLATE_CAKE, NONE),
                        Map.entry(Menu.ICE_CREAM, NONE),
                        // 음료
                        Map.entry(Menu.ZERO_COLA, NONE),
                        Map.entry(Menu.RED_WINE, NONE),
                        Map.entry(Menu.CHAMPAGNE, NONE)
                )
        );
    }

    public Order(int date, Map<Menu, Integer> orderedMenus) {
        setOrderedMenus(
                Map.ofEntries(
                        // 애피타이저
                        Map.entry(Menu.MUSHROOM_SOUP, NONE),
                        Map.entry(Menu.TAPAS, NONE),
                        Map.entry(Menu.CAESAR_SALAD, NONE),
                        // 메인
                        Map.entry(Menu.T_BONE_STEAK, NONE),
                        Map.entry(Menu.BBQ_RIBS, NONE),
                        Map.entry(Menu.SEAFOOD_PASTA, NONE),
                        Map.entry(Menu.CHRISTMAS_PASTA, NONE),
                        // 디저트
                        Map.entry(Menu.CHOCOLATE_CAKE, NONE),
                        Map.entry(Menu.ICE_CREAM, NONE),
                        // 음료
                        Map.entry(Menu.ZERO_COLA, NONE),
                        Map.entry(Menu.RED_WINE, NONE),
                        Map.entry(Menu.CHAMPAGNE, NONE)
                )
        );
        this.reservationDate = date;
        this.addOrderedMenus(orderedMenus);
        this.expectedPaymentAmount = calculator.getTotalAmountBeforeDiscount(this);
    }

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

    public void addValidEvents(List<Event> validEvents) {
        this.validEvents.addAll(validEvents);
    }

    public Map<Menu, Integer> getOrderedMenus() {
        return orderedMenus != null ? orderedMenus : Collections.emptyMap();
    }

    public void setOrderedMenus(Map<Menu, Integer> orderedMenus) {
        this.orderedMenus = orderedMenus;
    }

    public void addOrderedMenus(Map<Menu, Integer> orderedMenus) {
        // 맵을 복사하여 새로운 맵 생성
        Map<Menu, Integer> existingMenus = new HashMap<>(getOrderedMenus());
        // 새로운 맵에 새로운 매핑을 추가
        existingMenus.putAll(orderedMenus);
        // ORDER의 orderedMenus를 새로운 맵으로 갱신
        setOrderedMenus(existingMenus);
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
