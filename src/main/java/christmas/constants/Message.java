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
    CHRISTMAS_DDAY_EVENT_MSG("크리스마스 디데이 할인:"),
    WEEKDAYS_EVENT_MSG("평일 할인"),
    WEEKEND_EVENT_MSG("주말 할인"),
    SPECIAL_EVENT_MSG("특별 할인"),
    GIVEAWAY_EVENT_MSG("증정 이벤트"),
    INVALID_DATE_ERROR_MSG("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_MENU_ERROR_MSG("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NONE("없음");

    final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
