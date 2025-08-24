package com.gildedrose

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Spock unit tests.
 */
class GildedRoseSpec extends Specification {

    def "should update quality correctly"() {
        given: "some items"
        Item[] items = [new Item("foo", 0, 0)];

        and: "the application with these items"
        GildedRose app = new GildedRose(items);

        when: "updating quality"
        app.updateQuality();

        then: "the quality is correct"
        app.items[0].name == "foo"
        app.items[0].sellIn == -1
        app.items[0].quality == 0
    }

    def "should decrease quality of normal item twice as fast when sell in has passed"() {
        given: "some items"
        Item[] items = [new Item("foo", 0, 4)];

        and: "the application with these items"
        GildedRose app = new GildedRose(items);

        when: "updating quality"
        app.updateQuality();

        then: "the quality is correct"
        app.items[0].name == "foo"
        app.items[0].sellIn == -1
        app.items[0].quality == 2
    }

    def "should increase quality of aged brie twice as fast when sell in has passed"() {
        given: "some items"
        Item[] items = [new Item("Aged Brie", 0, 4)];

        and: "the application with these items"
        GildedRose app = new GildedRose(items);

        when: "updating quality"
        app.updateQuality();

        then: "the quality is correct"
        app.items[0].name == "Aged Brie"
        app.items[0].sellIn == -1
        app.items[0].quality == 6
    }

    def "should not increase quality past 50"() {
        given: "some items"
        Item[] items = [new Item("Aged Brie", 0, 49)];

        and: "the application with these items"
        GildedRose app = new GildedRose(items);

        when: "updating quality"
        app.updateQuality();

        then: "the quality is correct"
        app.items[0].name == "Aged Brie"
        app.items[0].sellIn == -1
        app.items[0].quality == 50
    }

    def "should not decrease quality of legendary item"() {
        given: "some items"
        Item[] items = [new Item("Sulfuras, Hand of Ragnaros", 6, 30)];

        and: "the application with these items"
        GildedRose app = new GildedRose(items);

        when: "updating quality"
        app.updateQuality();

        then: "the quality is correct"
        app.items[0].name == "Sulfuras, Hand of Ragnaros"
        app.items[0].sellIn == 6
        app.items[0].quality == 30
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
