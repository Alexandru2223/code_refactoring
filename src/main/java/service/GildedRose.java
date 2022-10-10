package service;

import model.Item;

import java.util.List;


public class GildedRose {

    private final List<Item> itemList;

    public GildedRose(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void updateQuality() {

        itemList.forEach(item -> {

            switch (item.getName()) {
                case "I002":
                    updateQualityForI002(item);
                    break;
                case "I004":
                    break;
                case "I005":
                    updateQualityForI005(item);
                    break;
                case "I006":
                    updateQualityForI006(item);
                    break;
                default:
                    updateQualityForDefault(item);
                    break;
            }
            decreaseSellIn(item);
        });

    }

    private void updateQualityForI006(Item item) {
        lowerQuality(item, 2);
    }

    private void updateQualityForDefault(Item item) {
        lowerQuality(item, 1);
    }

    private void updateQualityForI005(Item item) {
        if (!checkSellDateHasPassed(item)) {
            increaseQuality(item);
            if (item.getSellIn() <= 10) {
                increaseQuality(item);
            }
            if (item.getSellIn() <= 5) {
                increaseQuality(item);
            }
        } else {
            dropQualityToZero(item);
        }
    }

    private void updateQualityForI002(Item item) {
        increaseQuality(item);
    }

    private void lowerQuality(Item item, int factor) {
        if (checkSellDateHasPassed(item)) {
            item.setQuality(item.getQuality() - 2 * factor);
        } else {
            item.setQuality(item.getQuality() - factor);
        }
    }

    private void increaseQuality(Item item) {
        item.setQuality(item.getQuality() + 1);
    }

    private void decreaseSellIn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }


    private void dropQualityToZero(Item item) {
        if (checkSellDateHasPassed(item)) {
            item.setQuality(0);
        }
    }

    private boolean checkSellDateHasPassed(Item item) {
        return item.getSellIn() <= 0;
    }


}
