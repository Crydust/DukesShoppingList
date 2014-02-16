package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Item;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ItemList;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ItemList_;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Item_;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ProductType;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product_;
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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author kristof
 */
@Stateless
@Slf4j
public class ItemListBoundary implements Serializable {

    @PersistenceContext
    EntityManager em;
    @Inject
    UnitBoundary unitBoundary;
    @Inject
    ProductBoundary productBoundary;
    @Inject
    ProductTypeBoundary productTypeBoundary;

    public List<ItemList> findAllItemLists() {
        log.trace("findAllItemLists");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ItemList> cq = cb.createQuery(ItemList.class);
        Root<ItemList> shoppingListRoot = cq.from(ItemList.class);
        cq.distinct(true);
        cq.select(shoppingListRoot);
        Fetch<ItemList, Item> shoppingListItemPath
                = shoppingListRoot.fetch(ItemList_.items, JoinType.INNER);
        Fetch<Item, Product> productPath
                = shoppingListItemPath.fetch(Item_.product, JoinType.INNER);
        shoppingListItemPath.fetch(Item_.unit, JoinType.INNER);
        productPath.fetch(Product_.type, JoinType.INNER);
        TypedQuery<ItemList> q = em.createQuery(cq);
        return q.getResultList();
    }

    public void deleteAllItemLists() {
        log.trace("deleteAllItemLists");
        em.createNamedQuery(Item.DELETE_ALL).executeUpdate();
        em.createNamedQuery(ItemList.DELETE_ALL).executeUpdate();
        em.createNamedQuery(Product.DELETE_ALL).executeUpdate();
        em.createNamedQuery(Unit.DELETE_ALL).executeUpdate();
        em.createNamedQuery(ProductType.DELETE_ALL).executeUpdate();
    }

    public ItemList saveItemList(ItemList list) {
        log.trace("saveItemList");
        em.persist(list);
        em.flush();
        em.refresh(list);
        return list;
    }

    public ItemList updateItemList(ItemList list) {
        log.trace("updateItemList");
        return em.merge(list);
    }

    public ItemList findItemListById(Long id) {
        log.trace("findItemListById");
        // getReference doesnt retrieve items
        //return em.getReference(ItemList.class, id);
        return em.find(ItemList.class, id);
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
        return saveItemList(list);
    }
}
