package christmas.constants;

public enum Menu {
    // 애피타이저
    MUSHROOM_SOUP("양송이수프", 6000, MenuCategory.APPETIZER),
    TAPAS("타파스", 5500, MenuCategory.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, MenuCategory.APPETIZER),

    // 메인
    T_BONE_STEAK("티본스테이크", 55000, MenuCategory.MAIN),
    BBQ_RIBS("바비큐립", 54000, MenuCategory.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MenuCategory.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuCategory.MAIN),

    // 디저트
    CHOCOLATE_CAKE("초코케이크", 15000, MenuCategory.DESSERT),
    ICE_CREAM("아이스크림", 5000, MenuCategory.DESSERT),

    // 음료
    ZERO_COLA("제로콜라", 3000, MenuCategory.BEVERAGE),
    RED_WINE("레드와인", 60000, MenuCategory.BEVERAGE),
    CHAMPAGNE("샴페인", 25000, MenuCategory.BEVERAGE);

    private final String name;
    private final int price;
    private final MenuCategory category;

    Menu(String name, int price, MenuCategory MenuCategory) {
        this.name = name;
        this.price = price;
        this.category = MenuCategory;
    }

    public static Menu getMenuByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.name.equals(name)) {
                return menu;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public MenuCategory getCategory() {
        return category;
    }
}