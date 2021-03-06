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
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author kristof
 */
@Named
@ViewScoped
@Slf4j
public class ItemListBacking implements Serializable {

    private static final long serialVersionUID = 1L;

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
    }

    public String addItem() {
        log.trace("addItem");
        currentItemList = itemListBoundary.addItemToList(currentItemList, currentItem);
        currentItem = new Item();
        currentItem.setQuantity("1");
        return navigateWithViewParams();
    }

    public String removeItem(Item item) {
        currentItemList = itemListBoundary.removeItemFromList(currentItemList, item);
        currentItem = new Item();
        currentItem.setQuantity("1");
        return navigateWithViewParams();
    }

    // Getters/setters
    public List<ItemList> getItemLists() {
        if (itemLists == null) {
            itemLists = itemListBoundary.findAll();
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
            currentItem.setQuantity("1");
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
