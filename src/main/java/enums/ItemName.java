package enums;

public enum ItemName {
    I001("+5 Dexterity Vest"),
    I002("Aged Brie"),
    I003("Elixir of the Mongoose"),
    I004("Sulfuras, Hand of Ragnaros"),
    I005("Backstage passes to a TAFKAL80ETC concert"),
    I006("Conjured Mana Cake");

    private final String itemName;

    ItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
