package christmas.constants;

public enum Message {

    GREETING_MSG("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    GET_DATE_MSG("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    GET_MENU_MSG("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    MENU_MSG("<주문 메뉴>"), TOTAL_AMOUNT_BEFORE_DISCOUNT_MSG("<할인 전 총주문 금액>"),
    GIVEAWAY_MSG("<증정 메뉴>"),
    BENEFITS_MSG("<혜택 내역>"),
    TOTAL_BENEFITS_AMOUNT_MSG("<총혜택 금액>"),
    EXPECTED_TOTAL_PAYMENT_AMOUNT_MSG("<할인 후 예상 결제 금액>"),
    BADGE_MSG("<12월 이벤트 배지>"),
    NONE("없음");

    final String message;

    Message(String message) {
        this.message = message;
    }
}
