package com.gildedrose;

public class NormalItem implements ItemStrategy {

    @Override
    public void updateItem(ItemDecorator item) {
        item.decreaseSellInByOne();

        if (item.getQuality() > 0) {
            item.decreaseQualityByOne();
        }

        if (item.getSellIn() < 0) {
            item.decreaseQualityByOne();
        }

        if (item.getQuality() < 0) {
            item.setQuality(0);
        }
    }
}
