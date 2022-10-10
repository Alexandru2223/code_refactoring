import exception.IncorrectItemPropertyValueException;
import model.Item;
import service.GildedRose;

import java.util.Arrays;

public class TexttestFixture {
    public static void main(String[] args) throws IncorrectItemPropertyValueException {

        GildedRose shop = new GildedRose(
            Arrays.asList(
                new Item("I001", 10, 40), //
                new Item("I002", 2, 30), //
                new Item("I003", 5, 7), //
                new Item("I004", 0), //
                new Item("I004", 0),
                new Item("I005", 15, 20),
                new Item("I005", 10, 49),
                new Item("I005", 5, 49),
                // this conjured item does not work properly yet
                new Item("I006", 3, 6)
            )
        );

        int days = 12;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : shop.getItemList()) {
                System.out.println(item);
            }
            System.out.println();
            shop.updateQuality();
        }
    }

}
