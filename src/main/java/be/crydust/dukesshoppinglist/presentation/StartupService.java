package be.crydust.dukesshoppinglist.presentation;

import be.crydust.dukesshoppinglist.business.shoppinglist.boundary.ShoppingListBoundary;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Item;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ItemList;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ProductType;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Unit;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kristof
 */
@javax.ejb.Singleton
@javax.ejb.Startup
public class StartupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupService.class);

    @Inject
    ShoppingListBoundary boundary;

    @PostConstruct
    public void onStartup() {

        LOGGER.trace("StartupService.onStartup");

        boundary.deleteAllItemLists();
        ItemList list = new ItemList("list");
        ProductType type = new ProductType("type");
        Product product = new Product("product", type);
        Unit unit = new Unit("unit");
        Item item = new Item("5", unit, product);
        list.setItems(Arrays.asList(item));
        item.setItemList(list);
        boundary.saveItemList(list);

        boundary.saveItemList(ItemList.createItemList("list2", Arrays.asList(
                new Item("1", new Unit("x"), new Product("Chicken", new ProductType("Meat"))),
                new Item("25", new Unit("Kg"), new Product("Potatoes", new ProductType("Vegetables"))),
                new Item("2", new Unit("bottles"), new Product("Wine", new ProductType("Beverages"))),
                new Item("1", new Unit("loaf"), new Product("Bread", new ProductType("Other")))
        )));

    }
}
