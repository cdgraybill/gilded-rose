package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            if (item.quality == 80) {
                break;
            } else if (item.name.contains("Backstage passes")) {
                if (item.hasNotReachedQualityCap()) {
                    item.increaseQualityByOne();

                    if (item.sellIn < 11) {
                        if (item.hasNotReachedQualityCap()) {
                            item.increaseQualityByOne();
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.hasNotReachedQualityCap()) {
                            item.increaseQualityByOne();
                        }
                    }

                    item.decreaseSellInByOne();

                    if (item.sellIn < 0) {
                        item.quality = 0;
                    }
                }
            } else if (item.name.equals("Aged Brie")) {
                item.decreaseSellInByOne();

                if (item.hasNotReachedQualityCap()) {
                    item.increaseQualityByOne();

                    if (item.sellIn < 0 && item.hasNotReachedQualityCap()) {
                        item.increaseQualityByOne();
                    }
                }
            } else {
                item.decreaseSellInByOne();

                if (item.quality > 0) {
                    item.decreaseQualityByOne();
                }

                if (item.sellIn < 0) {
                    item.decreaseQualityByOne();
                }

                if (item.quality < 0) {
                    item.quality = 0;
                }
            }
        }
    }
}
