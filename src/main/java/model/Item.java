package model;

import enums.ItemName;
import exception.IncorrectItemPropertyValueException;

public class Item {

    private String name;

    private int sellIn;

    private int quality;

    public Item(String name, int sellIn, int quality) throws IncorrectItemPropertyValueException {
        setNameForConstructor(name);
        setSellInForConstructor(sellIn);
        setQualityForConstructor(quality);

    }

    public Item(String name, int sellIn) throws IncorrectItemPropertyValueException {
        setNameForConstructor(name);
        setSellInForConstructor(sellIn);
        this.quality = 80;
    }

    private void setNameForConstructor(String name) throws IncorrectItemPropertyValueException {
        try {
            this.name = ItemName.valueOf(name).name();
        } catch (IllegalArgumentException e){
            throw new IncorrectItemPropertyValueException("Item with name " + name + " does not exists.");
        }

    }

    private void setSellInForConstructor(int sellIn) throws IncorrectItemPropertyValueException {
        if (sellIn < 0) {
            throw new IncorrectItemPropertyValueException(this.name + "(" + ItemName.valueOf(this.name).getItemName() + ") : SellIn should not be lower than 0");
        }
        this.sellIn = sellIn;
    }

    private void setQualityForConstructor(int quality) throws IncorrectItemPropertyValueException {
        if (quality > 50) {
            throw new IncorrectItemPropertyValueException(this.name + "(" + ItemName.valueOf(this.name).getItemName() + ") : QUALITY should not be greater than 50");
        }
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        if(sellIn >= 0) {
            this.sellIn = sellIn;
        }
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        if(quality <= 50 && quality >= 0 && !this.name.equals("I004")) {
            this.quality = quality;
        }
    }

    @Override
    public String toString() {
        return ItemName.valueOf(this.name).getItemName() + ", " + this.sellIn + ", " + this.quality;
    }
}
