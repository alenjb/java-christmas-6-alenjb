package christmas.constant;

import camp.nextstep.edu.missionutils.Randoms;
import christmas.constants.Amount;
import christmas.constants.Day;

public enum AmountForTest {
    MAX_AMOUNT(Integer.MAX_VALUE-1),
    INVALID_AMOUNT_FOR_EVENT(Randoms.pickNumberInRange(0, (int) Amount.MINIMUM_AMOUNT_FOR_EVENT.amount - 1)),
    VALID_AMOUNT_FOR_EVENT(Randoms.pickNumberInRange((int) Amount.MINIMUM_AMOUNT_FOR_EVENT.amount, (int) MAX_AMOUNT.amount)),
    INVALID_AMOUNT_FOR_GIVEAWAY_EVENT(Randoms.pickNumberInRange(0, (int) Amount.MINIMUM_AMOUNT_FOR_GIVEAWAY_EVENT.amount - 1)),
    VALID_AMOUNT_FOR_GIVEAWAY_EVENT(Randoms.pickNumberInRange((int) Amount.MINIMUM_AMOUNT_FOR_GIVEAWAY_EVENT.amount, (int) MAX_AMOUNT.amount));

    public final int amount;

    AmountForTest(int amount) {
        this.amount = amount;
    }
}
