package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.control.CrudService;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Item;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ItemList;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ProductType;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Unit;
import com.google.common.base.Splitter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author kristof
 */
@Stateless
@Slf4j
public class ItemListBoundary implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    EntityManager em;
    @Inject
    UnitBoundary unitBoundary;
    @Inject
    ProductBoundary productBoundary;
    @Inject
    ProductTypeBoundary productTypeBoundary;
    @Inject
    ItemBoundary itemBoundary;
    @Inject
    CrudService crudService;

    @SuppressWarnings("unchecked")
    public List<ItemList> findAll() {
        return (List<ItemList>) crudService.findWithNamedQuery(ItemList.FIND_ALL);
    }

    public void deleteAll() {
        em.createNamedQuery(Item.DELETE_ALL).executeUpdate();
        em.createNamedQuery(ItemList.DELETE_ALL).executeUpdate();
        em.createNamedQuery(Product.DELETE_ALL).executeUpdate();
        em.createNamedQuery(Unit.DELETE_ALL).executeUpdate();
        em.createNamedQuery(ProductType.DELETE_ALL).executeUpdate();
    }

    public ItemList addItemToList(ItemList list, Item item) {
        list = em.merge(list);
        item.setItemList(list);
        em.persist(item);
        list.getItems().add(item);
        list = em.merge(list);
        em.flush();
        em.refresh(list);
        return list;
    }

    public ItemList removeItemFromList(ItemList list, Item item) {
        itemBoundary.delete(item.getId());
        list.getItems().remove(item);
        return crudService.update(list);
    }

    public ItemList update(ItemList list) {
        return crudService.update(list);
    }

    public ItemList find(Long id) {
        return crudService.find(ItemList.class, id);
    }

    public ItemList createFromCsv(String name, String csv) {
        log.trace("createFromCsv");
        Iterable<String> lines = Splitter.on('\n').split(csv);
        List<Item> items = new ArrayList<>();
        for (String line : lines) {
            log.trace("line {}", line);
            Iterator<String> fields = Splitter.on(',').trimResults().split(line).iterator();
            String quantity = fields.next();
            String unitName = fields.next();
            String productName = fields.next();
            String productTypeName = fields.next();
            Unit unit = unitBoundary.findByName(unitName);
            if (unit == null) {
                log.trace("new Unit");
                unit = new Unit(unitName);
                em.persist(unit);
            }
            Product product = productBoundary.findByName(productName);
            if (product == null) {
                ProductType productType = productTypeBoundary.findByName(productTypeName);
                if (productType == null) {
                    log.trace("new ProductType");
                    productType = new ProductType(productTypeName);
                    em.persist(productType);
                }
                log.trace("new Product");
                product = new Product(productName, productType);
                em.persist(product);
            }
            log.trace("new Item");
            Item item = new Item(quantity, unit, product);
            em.persist(item);
            items.add(item);
        }
        ItemList list = new ItemList(name);
        list.setItems(items);
        log.trace("before save");
        return crudService.create(list);
    }
}
