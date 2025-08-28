package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemDecorator itemDecorator = new ItemDecorator(item);

            // legendary item
            if (itemDecorator.getQuality() == 80) {
                break;
                // backstage pass
            } else if (itemDecorator.getName().contains("Backstage passes")) {
                if (itemDecorator.hasNotReachedQualityCap()) {
                    itemDecorator.increaseQualityByOne();

                    if (itemDecorator.getSellIn() < 11) {
                        if (itemDecorator.hasNotReachedQualityCap()) {
                            itemDecorator.increaseQualityByOne();
                        }
                    }

                    if (itemDecorator.getSellIn() < 6) {
                        if (itemDecorator.hasNotReachedQualityCap()) {
                            itemDecorator.increaseQualityByOne();
                        }
                    }

                    itemDecorator.decreaseSellInByOne();

                    if (itemDecorator.getSellIn() < 0) {
                        itemDecorator.setQuality(0);
                    }
                }
                // aged brie
            } else if (itemDecorator.getName().equals("Aged Brie")) {
                itemDecorator.decreaseSellInByOne();

                if (itemDecorator.hasNotReachedQualityCap()) {
                    itemDecorator.increaseQualityByOne();

                    if (itemDecorator.getSellIn() < 0 && itemDecorator.hasNotReachedQualityCap()) {
                        itemDecorator.increaseQualityByOne();
                    }
                }

                // conjured item
            } else if (itemDecorator.getName().contains("Conjured")) {
                // TODO: add conjured logic (degrades twice as fast as normal items)
            } else {
                itemDecorator.decreaseSellInByOne();

                if (itemDecorator.getQuality() > 0) {
                    itemDecorator.decreaseQualityByOne();
                }

                if (itemDecorator.getSellIn() < 0) {
                    itemDecorator.decreaseQualityByOne();
                }

                if (itemDecorator.getQuality() < 0) {
                    itemDecorator.setQuality(0);
                }
            }
        }
    }
}
