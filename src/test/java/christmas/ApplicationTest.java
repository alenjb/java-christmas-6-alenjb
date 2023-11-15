package christmas;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("<주문 메뉴>", "<할인 전 총주문 금액>", "<증정 메뉴>", "<혜택 내역>", "<총혜택 금액>", "<할인 후 예상 결제 금액>", "<12월 이벤트 배지>");
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>", LINE_SEPARATOR, "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        // 1. 빈 값인 경우
        assertSimpleTest(() -> {
            runException(" ");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
        // 2. 숫자가 아닌 경우
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
        // 3. 정수가 아닌 경우
        assertSimpleTest(() -> {
            runException("5.5");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
        // 4. 1~31 범위가 아닌 경우
        assertSimpleTest(() -> {
            runException("0");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });


    }

    @Test
    void 주문_예외_테스트() {
        // 1. 메뉴 형식이 올바르지 않은 경우
        // 1-a. 마지막에 쉼표를 추가한 경우
        assertSimpleTest(() -> {
            runException("3", "해산물파스타-2,레드와인-1,초코케이크-1,");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        // 1-b. 마지막에 수량을 누락한 경우
        assertSimpleTest(() -> {
            runException("3", "해산물파스타-2,레드와인-1,초코케이크-1,레드-");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        // 1-c. 마지막에 수량과 "-"를 누락한 경우
        assertSimpleTest(() -> {
            runException("3", "해산물파스타-2,레드와인-1,초코케이크-1,레드");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        // 1-d. 마지막에 수량을 누락하고 쉼표를 누락한 경우
        assertSimpleTest(() -> {
            runException("3", "해산물파스타-2,레드와인-1,초코케이크-1레드-");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        // 1-e. 1개의 메뉴를 주문하면서 쉼표를 추가한 경우
        assertSimpleTest(() -> {
            runException("3", "해산물파스타-2,");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        // 1-f. 1개의 메뉴를 주문하면서 수량과 "-"를 누락한 경우
        assertSimpleTest(() -> {
            runException("3", "해산물파스타");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        // 1-g. 빈 값인 경우
        assertSimpleTest(() -> {
            runException("3", " ");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        // 2, 중복 메뉴를 입력한 경우
        assertSimpleTest(() -> {
            runException("3", "해산물파스타-2,레드와인-1,해산물파스타-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        // 3. 메뉴판에 없는 메뉴를 입력한 경우
        assertSimpleTest(() -> {
            runException("3", "해산물파스타-2,당근케이크-3");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        // 4. 음료를 제외한 음식을 1개 이상 주문하지 않은 경우
        assertSimpleTest(() -> {
            runException("3", "레드와인-5,제로콜라-4");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        // 5. 메뉴를 한번에 20개를 초과해서 주문한 경우
        assertSimpleTest(() -> {
            runException("3", "해산물파스타-5,제로콜라-12,초코케이크-8");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        // 6. 메뉴의 개수에 1 미만의 값을 입력한 경우
        assertSimpleTest(() -> {
            runException("3", "제로콜라-0,해산물파스타-3");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 증정_이벤트_적용_정상_기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("25", "해산물파스타-2,티본스테이크-2,레드와인-2,초코케이크-1");
                    assertThat(output()).contains(
                            "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.",
                            "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)",
                            "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)",
                            "12월 25일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!",
                            "<주문 메뉴>",
                            "레드와인 2개",
                            "해산물파스타 2개",
                            "초코케이크 1개",
                            "티본스테이크 2개",
                            "<할인 전 총주문 금액>",
                            "315,000원",
                            "<증정 메뉴>",
                            "샴페인 1개",
                            "<혜택 내역>",
                            "크리스마스 디데이 할인: -3,400원",
                            "평일 할인 -2,023원",
                            "특별 할인 -1,000원",
                            "증정 이벤트 -25,000원",
                            "<총혜택 금액>",
                            "-31,423원",
                            "<할인 후 예상 결제 금액>",
                            "308,577원",
                            "<12월 이벤트 배지>",
                            "산타"
                    );
                },
                List.of());
    }

    @Test
    void 증정_이벤트_미적용_정상_기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("25", "티본스테이크-1,초코케이크-1");
                    assertThat(output()).contains(
                            "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.",
                            "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)",
                            "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)",
                            "12월 25일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!",
                            "<주문 메뉴>",
                            "초코케이크 1개",
                            "티본스테이크 1개",
                            "<할인 전 총주문 금액>",
                            "70,000원",
                            "<증정 메뉴>",
                            "없음",
                            "<혜택 내역>",
                            "크리스마스 디데이 할인: -3,400원",
                            "평일 할인 -2,023원",
                            "특별 할인 -1,000원",
                            "<총혜택 금액>",
                            "-6,423원",
                            "<할인 후 예상 결제 금액>",
                            "63,577원",
                            "<12월 이벤트 배지>",
                            "별"
                    );
                },
                List.of());
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
