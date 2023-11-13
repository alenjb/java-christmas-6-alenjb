package christmas.domain;

import christmas.constants.Badge;
import christmas.constants.Giveaway;

import java.util.List;
import java.util.Map;

public class Order {
    // 방문 날짜
    int reservationDate;
    // 적용 가능한 이벤트들
    List<Event> validEvents;
    // 주문 메뉴들
    Map<String, Integer> orderedMenus;
    // 예상 결제 금액
    double expectedPaymentAmount;
    // 배지
    Badge badge;
    //증정품
    Giveaway giveaway;
}
