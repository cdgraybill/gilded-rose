package com.gildedrose;

import java.util.Set;

//TODO: revert all changes to this file, per the requirements
public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public void increaseQualityByOne() {
        this.quality = this.quality + 1;
    }

    public void decreaseQualityByOne() {
        this.quality = this.quality - 1;
    }

    public void decreaseSellInByOne() {
        this.sellIn = this.sellIn - 1;
    }

    public boolean hasNotReachedQualityCap() {
        return this.quality < 50;
    }

    private static final Set<String> SPECIAL_ITEMS = Set.of(
            "Sulfuras, Hand of Ragnaros",
            "Aged Brie",
            "Backstage passes to a TAFKAL80ETC concert"
    );

    public boolean isNormalItem() {
        return !SPECIAL_ITEMS.contains(this.name);
    }
}
