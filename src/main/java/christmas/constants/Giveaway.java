package christmas.constants;

public enum Giveaway {
    CHAMPAGNE("샴페인"),
    NONE("없음");

    final String name;

    Giveaway(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
