package christmas.constants;

import christmas.domain.CalculateAmount;
import christmas.domain.Order;

public enum Badge {
    NONE("없음"), STAR("별"), TREE("트리"), SANTA("산타");

    final String name;
    final CalculateAmount calculateAmount = new CalculateAmount();

    Badge(String name) {
        this.name = name;
    }

    // 해당 금액에 맞는 적절한 뱃지를 찾는 메서드
    public Badge findAppropriateBadgeForAmount(Order order) {
        if (calculateAmount.getTotalBenefits(order) >= Amount.MINIMUM_SANTA_AMOUNT.amount) return SANTA;
        if (calculateAmount.getTotalBenefits(order) >= Amount.MINIMUM_STAR_AMOUNT.amount) return STAR;
        if (calculateAmount.getTotalBenefits(order) >= Amount.MINIMUM_TREE_AMOUNT.amount) return TREE;
        return NONE;
    }

    // 뱃지를 부여하는 메서드
    public void giveBadge(Order order) {
        order.setBadge(findAppropriateBadgeForAmount(order));
    }

    public String getName() {
        return name;
    }
}
