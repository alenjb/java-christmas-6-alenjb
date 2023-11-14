package christmas.constant;

import camp.nextstep.edu.missionutils.Randoms;
import christmas.constants.Day;

import java.util.List;

public enum DayForTest {

    CHRISTMAS_D_DAY_EVENT_VALID_DAY(Randoms.pickNumberInRange(Day.CHRISTMAS_D_DAY_EVENT_START_DAY.getDay(), Day.CHRISTMAS_D_DAY_EVENT_END_DAY.getDay())),
    CHRISTMAS_D_DAY_EVENT_INVALID_DAY(Randoms.pickNumberInRange(26, 31)),
    EVENT_VALID_DAY(Randoms.pickNumberInRange(Day.EVENT_START_DAY.getDay(), Day.EVENT_END_DAY.getDay())),
    EVENT_INVALID_DAY(0),
    WEEKDAYS_EVENT_VALID_DAY(Randoms.pickNumberInList(Day.WEEKDAYS.getDays())),
    WEEKDAYS_EVENT_INVALID_DAY(Randoms.pickNumberInList(Day.WEEKEND.getDays())),
    WEEKEND_EVENT_VALID_DAY(Randoms.pickNumberInList(Day.WEEKEND.getDays())),
    WEEKEND_EVENT_INVALID_DAY(Randoms.pickNumberInList(Day.WEEKDAYS.getDays()));

    private final int day;

    DayForTest(int day) {
        this.day = day;
    }

    public int getDay() {
        return this.day;
    }
}
