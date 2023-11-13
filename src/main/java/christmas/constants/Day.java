package christmas.constants;

import java.util.List;

public enum Day {

    STAR_DAYS(List.of(3, 10, 17, 24, 25, 31)),
    WEEKDAYS(List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31)),
    WEEKEND(List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)),
    CHRISTMAS_D_DAY_EVENT_START_DAY(List.of(1)),
    CHRISTMAS_D_DAY_EVENT_END_DAY(List.of(25)),
    EVENT_START_DAY(List.of(1)),
    EVENT_END_DAY(List.of(31));

    private final List<Integer> days;

    Day(List<Integer> days) {
        this.days = days;
    }

    public List<Integer> getDays(){
        return this.days;
    }
    public int getDay(){
        return this.days.get(0);
    }

    public boolean contains(int day) {
        return days.contains(day);
    }
}
