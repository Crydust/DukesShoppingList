package be.crydust.dukesshoppinglist.presentation;

import be.crydust.dukesshoppinglist.business.shoppinglist.boundary.ItemListBoundary;
import be.crydust.dukesshoppinglist.business.shoppinglist.boundary.ProductBoundary;
import be.crydust.dukesshoppinglist.business.shoppinglist.boundary.UnitBoundary;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Item;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ItemList;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ProductType;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Unit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author kristof
 */
@Named
@SessionScoped
@Slf4j
public class ItemListBacking implements Serializable {

    // Properties
    List<ItemList> itemLists = null;
    List<Unit> units = null;
    List<Product> products = null;
    List<SelectItem> productSelectItems = null;

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

    public void addItem() {
        currentItemList = itemListBoundary.addItemToList(currentItemList, currentItem);
        currentItem = new Item();
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
            units = unitBoundary.findAll();
        }
        return units;
    }

    public List<SelectItem> getProductSelectItems() {
        if (products == null) {
            products = productBoundary.findAllOrderedByProductType();
        }
        if (productSelectItems == null) {
            ProductType productType = null;
            List<SelectItem> selectItems = new ArrayList<>();
            Map<String, List<SelectItem>> map = new LinkedHashMap<>();
            for (Product product : products) {
                if (!product.getType().equals(productType)) {
                    productType = product.getType();
                    selectItems = new ArrayList<>();
                    map.put(productType.getName(), selectItems);
                }
                selectItems.add(new SelectItem(product, product.getName()));
            }
            productSelectItems = new ArrayList<>();
            for (Map.Entry<String, List<SelectItem>> entry : map.entrySet()) {
                SelectItemGroup selectItemGroup = new SelectItemGroup(entry.getKey());
                selectItemGroup.setSelectItems(entry.getValue().toArray(new SelectItem[0]));
                productSelectItems.add(selectItemGroup);
            }
        }
        return productSelectItems;
    }
}
