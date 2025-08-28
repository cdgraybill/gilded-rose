package com.gildedrose;

import java.util.Set;

public class ItemDecorator {
    private final Item item;

    public ItemDecorator(Item item) {
        this.item = item;
    }

    public String getName() {
        return item.name;
    }

    public int getSellIn() {
        return item.sellIn;
    }

    public int getQuality() {
        return item.quality;
    }

    public void setSellIn(int value) {
        item.sellIn = value;
    }

    public void setQuality(int value) {
        item.quality = value;
    }

    public void increaseQualityByOne() {
        item.quality = item.quality + 1;
    }

    public void decreaseQualityByOne() {
        item.quality = item.quality - 1;
    }

    public void decreaseSellInByOne() {
        item.sellIn = item.sellIn - 1;
    }

    public boolean hasNotReachedQualityCap() {
        return item.quality < 50;
    }
}
