package com.gildedrose

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Spock unit tests.
 */
class GildedRoseSpec extends Specification {

    @Unroll
    def "should update quality of normal items correctly"() {
        given: "some items"
        Item[] items = [new Item("Normal Item", sellIn, quality)];

        and: "the application with these items"
        GildedRose app = new GildedRose(items);

        when: "updating quality"
        app.updateQuality();

        then: "the quality is correct"
        app.items[0].name == "Normal Item"
        app.items[0].sellIn == sellInResult
        app.items[0].quality == qualityResult

        where:
        sellIn | quality || sellInResult | qualityResult
        // sellIn not passed, normal quality degradation
        0      | 0      || -1            | 0
        5     | 5      || 4            | 4
        // sellIn passed, doubled quality degradation
        0     | 5      || -1            | 3
        -7     | 5      || -8            | 3
        -8     | 5      || -9            | 3
        -10     | 5      || -11            | 3
    }

    def "should increase quality of aged brie correctly"() {
        given: "some items"
        Item[] items = [new Item("Aged Brie", sellIn, quality)];

        and: "the application with these items"
        GildedRose app = new GildedRose(items);

        when: "updating quality"
        app.updateQuality();

        then: "the quality is correct"
        app.items[0].name == "Aged Brie"
        app.items[0].sellIn == sellInResult
        app.items[0].quality == qualityResult

        where:
        sellIn | quality || sellInResult | qualityResult
        // sellIn not passed, normal quality increase
        5     | 5      || 4            | 6
        1     | 5      || 0            | 6
        // sellIn passed, doubled quality increase
        0      | 0      || -1            | 2
        -4      | 9      || -5            | 11
        // doesn't exceed 50 quality
        0      | 49      || -1           | 50
        0      | 50      || -1           | 50
    }

    @Unroll
    def "should update quality of conjured items correctly"() {
        given: "some items"
        Item[] items = [new Item("Conjured weapon", sellIn, quality)];

        and: "the application with these items"
        GildedRose app = new GildedRose(items);

        when: "updating quality"
        app.updateQuality();

        then: "the quality is correct"
        app.items[0].name == "Conjured weapon"
        app.items[0].sellIn == sellInResult
        app.items[0].quality == qualityResult

        where:
        sellIn | quality || sellInResult | qualityResult
        // sellIn not passed, normal quality degradation
        0      | 0       || -1           | 0
        5      | 5       || 4            | 3
        // sellIn passed, doubled quality degradation
        0      | 5       || -1           | 1
        -7     | 5       || -8           | 1
        -8     | 5       || -9           | 1
        -10    | 5       || -11          | 1
    }

    def "should not decrease quality of legendary item"() {
        given: "some items"
        Item[] items = [new Item("Sulfuras, Hand of Ragnaros", 6, 80)];

        and: "the application with these items"
        GildedRose app = new GildedRose(items);

        when: "updating quality"
        app.updateQuality();

        then: "the quality is correct"
        app.items[0].name == "Sulfuras, Hand of Ragnaros"
        app.items[0].sellIn == 6
        app.items[0].quality == 80
    }

    @Unroll
    def "should update backstage passes correctly"() {
        given: "some items"
        Item[] items = [new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)];

        and: "the application with these items"
        GildedRose app = new GildedRose(items);

        when: "updating quality"
        app.updateQuality();

        then: "the quality is correct"
        app.items[0].name == "Backstage passes to a TAFKAL80ETC concert"
        app.items[0].sellIn == sellInResult
        app.items[0].quality == qualityResult

        where:
        sellIn | quality || sellInResult | qualityResult
        5      | 10      || 4            | 13
        10     | 10      || 9            | 12
        0      | 10      || -1           | 0
    }
}
