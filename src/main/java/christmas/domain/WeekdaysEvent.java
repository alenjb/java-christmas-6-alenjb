package christmas.domain;

import christmas.constants.Amount;
import christmas.constants.Day;
import christmas.constants.Menu;
import christmas.constants.MenuCategory;

public class WeekdaysEvent extends Discount implements Event {

    @Override
    public boolean isValidEventPeriod(int day) {
        return Day.WEEKDAYS.contains(day);
    }

    @Override
    public boolean isValidEventAmount(double totalAmount) {
        return totalAmount >= Amount.MINIMUM_AMOUNT_FOR_EVENT.amount;
    }

    public int checkNumberOfDesserts(Order order) {
        int numberOfDesserts = 0;
        for (Menu menu : order.getOrderedMenus().keySet()) {
            if (menu.getCategory().equals(MenuCategory.DESSERT))
                numberOfDesserts++;
        }
        return numberOfDesserts;
    }
}
