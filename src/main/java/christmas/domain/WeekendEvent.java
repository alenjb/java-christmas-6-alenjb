package christmas.domain;

import christmas.constants.Amount;
import christmas.constants.Day;
import christmas.constants.Menu;
import christmas.constants.MenuCategory;

public class WeekendEvent extends Discount implements Event {

    @Override
    public boolean isValidEventPeriod(int day) {
        return Day.WEEKEND.contains(day);
    }

    @Override
    public boolean isValidEventAmount(double totalAmount) {
        return totalAmount >= Amount.MINIMUM_AMOUNT_FOR_EVENT.amount;
    }

    public int getNumberOfMains(Order order) {
        int numberOfMains = 0;
        for (Menu menu : order.getOrderedMenus().keySet()) {
            if (menu.getCategory().equals(MenuCategory.MAIN))
                numberOfMains++;
        }
        return numberOfMains;
    }

    public double calculateDiscountAmount(Order order) {
        return getNumberOfMains(order) * Amount.WEEKEND_DISCOUNT_AMOUNT.amount;
    }
}
