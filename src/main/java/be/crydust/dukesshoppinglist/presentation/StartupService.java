package be.crydust.dukesshoppinglist.presentation;

import be.crydust.dukesshoppinglist.business.shoppinglist.boundary.ItemListBoundary;
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
        
        itemListboundary.deleteAllItemLists();

        log.trace("StartupService.onStartup");
        itemListboundary.createFromCsv("Grocery List", ""
                + "2,x,Paprika (red),Vegetables\n"
                + "200,g,Spinach,Vegetables\n"
                + "10,x,Tomato,Vegetables\n"
                + "1,x,Garlic,Vegetables\n"
                + "1,x,Brussels sprout,Vegetables\n"
                + "2,x,Avocado,Fruit\n"
                + "2,x,Lemon,Fruit\n"
                + "1,x,Grapes,Fruit\n"
                + "2,x,Grapefruit (pink),Fruit\n"
                + "2,x,Spirelli tricolore,Dry Goods & Grains\n"
                + "1,x,Rosemary,Herbs\n"
                + "1,x,Thyme,Herbs\n"
                + "1,x,Wine (white dry),Drinks\n"
                + "1,x,Fanta,Drinks\n"
                + "1,l,Milk,Dairy\n"
                + "12,x,Eggs,Dairy"
        );

    }
}
