package christmas.controller;

import christmas.constants.Badge;
import christmas.domain.*;
import christmas.util.InputValidator;
import christmas.util.Parser;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasPromotionController {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidator inputValidator;
    private final Parser parser;

    private ChristmasDdayEvent christmasDdayEvent = new ChristmasDdayEvent();
    private WeekdaysEvent weekdaysEvent = new WeekdaysEvent();
    private WeekendEvent weekendEvent = new WeekendEvent();
    private SpecialEvent specialEvent = new SpecialEvent();
    private GiveawayEvent giveawayEvent = new GiveawayEvent();
    private Order order;
    private Badge badge = Badge.NONE;

    public ChristmasPromotionController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.inputValidator = new InputValidator();
        this.parser = new Parser();
    }

    public void run() {
        outputView.greet();
        order = new Order(inputView.readDate(), inputView.readMenus());
        christmasDdayEvent.doEvent(order);
        weekdaysEvent.doEvent(order);
        weekendEvent.doEvent(order);
        specialEvent.doEvent(order);
        giveawayEvent.doEvent(order);
        badge.giveBadge(order);
        outputView.printPreviewMsg(order);
        outputView.printOrderedMenus(order);
        outputView.printTotalAmountBeforeDiscount(order);
        outputView.printGiveaway(order);
        outputView.printBenefits(order);
        outputView.printTotalBenefits(order);
        outputView.printExpectedTotalPaymentAmount(order);
        outputView.printBadge(order);
    }
}
