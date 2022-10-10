

import enums.ItemName;
import exception.IncorrectItemPropertyValueException;
import model.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.GildedRose;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GildedRoseTest {

    private static GildedRose gildedRose;
    private static List<Item> itemList;

    @BeforeAll
    static void beforeAll() throws IncorrectItemPropertyValueException {
        itemList = Arrays.asList(
            new Item("I001", 10, 40),
            new Item("I002", 2, 30),
            new Item("I003", 5, 7),
            new Item("I004", 0),
            new Item("I004", 0),
            new Item("I005", 15, 20),
            new Item("I005", 10, 10),
            new Item("I005", 4, 49),
            new Item("I002", 30, 48),
            new Item("I005", 7, 46),
            new Item("I006", 3, 4),
            new Item("I006", 4, 20)
        );
        gildedRose = new GildedRose(itemList);
        for (int i = 0; i <= 5; i++) {
            gildedRose.updateQuality();
        }
    }

    @Test
    void decrease_sellIn_after_day_passed() {
        assertEquals(4, itemList.get(0).getSellIn());
        assertEquals(0, itemList.get(2).getSellIn());
    }

    @Test
    void enum_should_be_mapped_to_item_name() {
        assertEquals(ItemName.I001.getItemName(), ItemName.valueOf(itemList.get(0).getName()).getItemName());
        assertEquals(ItemName.I002.getItemName(), ItemName.valueOf(itemList.get(1).getName()).getItemName());
        assertEquals(ItemName.I003.getItemName(), ItemName.valueOf(itemList.get(2).getName()).getItemName());
        assertEquals(ItemName.I004.getItemName(), ItemName.valueOf(itemList.get(3).getName()).getItemName());
        assertEquals(ItemName.I005.getItemName(), ItemName.valueOf(itemList.get(5).getName()).getItemName());
        assertEquals(ItemName.I006.getItemName(), ItemName.valueOf(itemList.get(10).getName()).getItemName());
    }

    @Test
    void should_decrease_quality_by_one_for_default_items_after_days_pass() {
        assertEquals(34, itemList.get(0).getQuality());
        assertEquals(0, itemList.get(2).getQuality());
    }

    @Test
    void should_increase_quality_by_one_for_aged_brie_after_days_pass() {
        assertEquals(36, itemList.get(1).getQuality());
    }

    @Test
    void should_increase_quality_by_two_and_three_for_backstage_after_days_pass() {
        assertEquals(27, itemList.get(5).getQuality());
        assertEquals(23, itemList.get(6).getQuality());
    }

    @Test
    void should_drop_quality_to_zero_after_concert() {
        assertEquals(0, itemList.get(7).getQuality());
    }

    @Test
    void should_not_increase_quality_more_than_fifty() {
        assertEquals(50, itemList.get(8).getQuality());
        assertEquals(50, itemList.get(9).getQuality());
    }

    @Test
    void should_not_sold_or_decrease_quality_for_sulfuras() {
        assertEquals(80, itemList.get(3).getQuality());
    }

    @Test
    void should_decrease_quality_twice_as_fast_as_normal_for_conjured() {
        assertEquals(0, itemList.get(10).getQuality());
        assertEquals(4, itemList.get(11).getQuality());
    }

    @Test
    void should_throw_exception_when_item_name_does_not_exists() {
        IncorrectItemPropertyValueException exception = assertThrows(IncorrectItemPropertyValueException.class, () -> {
            new Item("I009", 10, 40);
        });

        assertEquals("Item with name I009 does not exists.", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_item_quality_is_over_fifty() {
        IncorrectItemPropertyValueException exception = assertThrows(IncorrectItemPropertyValueException.class, () -> {
            new Item("I005", 10, 60);
        });

        assertEquals("I005(Backstage passes to a TAFKAL80ETC concert) : QUALITY should not be greater than 50", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_item_sellIn_is_lower_than_zero() {
        IncorrectItemPropertyValueException exception = assertThrows(IncorrectItemPropertyValueException.class, () -> {
            new Item("I005", -2, 40);
        });

        assertEquals("I005(Backstage passes to a TAFKAL80ETC concert) : SellIn should not be lower than 0", exception.getMessage());
    }

}
