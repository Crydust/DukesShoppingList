package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Item;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ItemList;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ItemList_;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Item_;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ProductType;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product_;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Unit;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
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

    public ItemList findItemListById(Long id) {
        log.trace("findItemListById");
        return em.getReference(ItemList.class, id);
    }

}
