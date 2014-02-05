package be.crydust.dukesshoppinglist.presentation;

import be.crydust.dukesshoppinglist.business.shoppinglist.boundary.ItemListBoundary;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Item;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ItemList;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ProductType;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Unit;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author kristof
 */
@Singleton
@Startup
@Slf4j
public class StartupService {

    @Inject
    ItemListBoundary itemListboundary;

    @PostConstruct
    public void onStartup() {

        log.trace("StartupService.onStartup");

        itemListboundary.deleteAllItemLists();
        ItemList list = new ItemList("list");
        ProductType type = new ProductType("type");
        Product product = new Product("product", type);
        Unit unit = new Unit("unit");
        Item item = new Item("5", unit, product);
        list.setItems(Arrays.asList(item));
        item.setItemList(list);
        itemListboundary.saveItemList(list);

        itemListboundary.saveItemList(ItemList.createItemList("list2", Arrays.asList(
                new Item("1", new Unit("x"), new Product("Chicken", new ProductType("Meat"))),
                new Item("25", new Unit("Kg"), new Product("Potatoes", new ProductType("Vegetables"))),
                new Item("2", new Unit("bottles"), new Product("Wine", new ProductType("Beverages"))),
                new Item("1", new Unit("loaf"), new Product("Bread", new ProductType("Other")))
        )));

    }
}
