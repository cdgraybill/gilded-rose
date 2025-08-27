package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            if (!item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0) {
                    if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                        item.decreaseQualityByOne();
                    }
                }
            }

            if (item.hasNotReachedQualityCap() && item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
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
            }

            makeQualityAdjustment(item);
        }
    }

    private void makeQualityAdjustment(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.decreaseSellInByOne();
        }

        if (item.hasNotReachedQualityCap() && item.name.equals("Aged Brie")) {
            item.increaseQualityByOne();

            if (item.sellIn < 0 && item.hasNotReachedQualityCap()) {
                item.increaseQualityByOne();
            }
        }

        if (item.sellIn < 0 && !item.name.equals("Aged Brie")) {
            if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.quality > 0) {
                    if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                        item.decreaseQualityByOne();
                    }
                }
            } else {
                item.quality = 0;
            }
        }
    }
}
