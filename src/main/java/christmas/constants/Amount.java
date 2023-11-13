package christmas.constants;

public enum Amount {
    MINIMUM_AMOUNT_FOR_EVENT(10000), // 이벤트 최소 주문 금액 -10,000원
    WEEKDAYS_DISCOUNT_AMOUNT(2023),// 평일 할인 금액 - 2023원
    WEEKEND_DISCOUNT_AMOUNT(2023),// 주말 할인 금액 - 2023원
    INITIAL_CHRISTMAS_DDAY_DISCOUNT_AMOUNT(1000),   // 크리스마스 할인 시작 금액
    CHRISTMAS_DDAY_DISCOUNT_INCREMENT_AMOUNT_BY_DAY(100),  // 크리스마스 할인 증가분 금액
    SPECIAL_DISCOUNT_AMOUNT(1000),  // 특별 할인 금액
    MINIMUM_STAR_AMOUNT(5000),  // 스타뱃지 금액
    MINIMUM_TREE_AMOUNT(10000),  // 트리뱃지 금액
    MINIMUM_SANTA_AMOUNT(20000); // 산타 뱃지 금액

    public final double amount;

    Amount(double amount) {
        this.amount = amount;
    }
}
