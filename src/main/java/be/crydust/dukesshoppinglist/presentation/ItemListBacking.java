package be.crydust.dukesshoppinglist.presentation;

import be.crydust.dukesshoppinglist.business.shoppinglist.boundary.ItemListBoundary;
import be.crydust.dukesshoppinglist.business.shoppinglist.boundary.ProductBoundary;
import be.crydust.dukesshoppinglist.business.shoppinglist.boundary.UnitBoundary;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Item;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ItemList;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Unit;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author kristof
 */
@Named
@RequestScoped
@Slf4j
public class ItemListBacking {

    // Properties
    List<ItemList> itemLists = null;
    List<Unit> units = null;
    List<Product> products = null;

    ItemList currentItemList = null;
    Item currentItem = null;
 
    // Services
    @Inject
    ItemListBoundary itemListBoundary;
    @Inject
    UnitBoundary unitBoundary;
    @Inject
    ProductBoundary productBoundary;

    // Init
    public void init() {
        //NOOP
    }

    // Actions
    public String navigateWithViewParams() {
        return "index?faces-redirect=true&amp;includeViewParams=true";
        // NOOP
    }

    public void create() {
//        postBoundary.create(getCurrentPost());
    }

    public void delete(Long id) {
//        postBoundary.deleteById(id);
    }

    // Getters/setters
    public List<ItemList> getItemLists() {
        if (itemLists == null) {
            itemLists = itemListBoundary.findAllItemLists();
        }
        return itemLists;
    }
    
    public ItemList getCurrentItemList() {
        return currentItemList;
    }

    public void setCurrentItemList(ItemList currentItemList) {
        this.currentItemList = currentItemList;
    }

    public Item getCurrentItem() {
        if (currentItem == null) {
            currentItem = new Item();
        }
        return currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }

    public List<Unit> getUnitSelectItems() {
        if (units == null) {
            units = unitBoundary.findAllUnits();
        }
        return units;
    }

    public List<Product> getProductSelectItems() {
        if (products == null) {
            products = productBoundary.findAllProducts();
        }
        return products;
    }

}
