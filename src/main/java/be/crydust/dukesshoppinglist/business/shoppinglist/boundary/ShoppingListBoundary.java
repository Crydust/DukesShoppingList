package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Item;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ProductType;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product_;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ItemList;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ItemList_;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Item_;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Unit;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kristof
 */
@Stateless
public class ShoppingListBoundary {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListBoundary.class);

    @PersistenceContext
    EntityManager em;

    public List<ItemList> findAllItemLists() {
        LOGGER.trace("findAllItemLists");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ItemList> cq = cb.createQuery(ItemList.class);
        Root<ItemList> shoppingListRoot = cq.from(ItemList.class);
        // select
        cq.distinct(true);
        cq.select(shoppingListRoot);
        // join
        Fetch<ItemList, Item> shoppingListItemPath =
                shoppingListRoot.fetch(ItemList_.items, JoinType.INNER);
        Fetch<Item, Product> productPath =
                shoppingListItemPath.fetch(Item_.product, JoinType.INNER);
        Fetch<Item, Unit> unitPath =
                shoppingListItemPath.fetch(Item_.unit, JoinType.INNER);
        Fetch<Product, ProductType> productTypePath =
                productPath.fetch(Product_.type, JoinType.INNER);
        //where
//        Predicate predicate = cb.conjunction();
//        predicate.getExpressions().addAll(customerFilterUtil.convertCustomerToExpressions(cb, customerRoot, filter));
//        cq.where(predicate);
        // limit
        TypedQuery<ItemList> q = em.createQuery(cq);
        return q.getResultList();
//        return em.createNamedQuery(ItemList.FIND_ALL).getResultList();
    }

    public void deleteAllItemLists() {
        LOGGER.trace("deleteAllItemLists");
        em.createNamedQuery(Item.DELETE_ALL).executeUpdate();
        em.createNamedQuery(ItemList.DELETE_ALL).executeUpdate();
        em.createNamedQuery(Product.DELETE_ALL).executeUpdate();
        em.createNamedQuery(Unit.DELETE_ALL).executeUpdate();
        em.createNamedQuery(ProductType.DELETE_ALL).executeUpdate();
    }

    public ItemList saveItemList(ItemList list) {
        LOGGER.trace("saveItemList");
        em.persist(list);
        em.flush();
        em.refresh(list);
        return list;
    }

}
