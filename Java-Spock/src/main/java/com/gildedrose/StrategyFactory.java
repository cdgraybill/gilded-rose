package com.gildedrose;

public class StrategyFactory {
    public static ItemStrategy getStrategy(Item item) {
        switch (item.name) {
            case "Aged Brie":
                return new AgedBrie();
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackstagePass();
            case "Sulfuras, Hand of Ragnaros":
                return new LegendaryItem();
            default:
                return new NormalItem();
        }
    }
}
