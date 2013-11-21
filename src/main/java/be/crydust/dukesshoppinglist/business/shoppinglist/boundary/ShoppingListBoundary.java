package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ProductType;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product_;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ShoppingList;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ShoppingListItem;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ShoppingListItem_;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ShoppingList_;
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

    public List<ShoppingList> findAllShoppingLists() {
        LOGGER.trace("findAllShoppingLists");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ShoppingList> cq = cb.createQuery(ShoppingList.class);
        Root<ShoppingList> shoppingListRoot = cq.from(ShoppingList.class);
        // select
        cq.distinct(true);
        cq.select(shoppingListRoot);
        // join
        Fetch<ShoppingList, ShoppingListItem> shoppingListItemPath =
                shoppingListRoot.fetch(ShoppingList_.items, JoinType.INNER);
        Fetch<ShoppingListItem, Product> productPath =
                shoppingListItemPath.fetch(ShoppingListItem_.product, JoinType.INNER);
        Fetch<ShoppingListItem, Unit> unitPath =
                shoppingListItemPath.fetch(ShoppingListItem_.unit, JoinType.INNER);
        Fetch<Product, ProductType> productTypePath =
                productPath.fetch(Product_.type, JoinType.INNER);
        //where
//        Predicate predicate = cb.conjunction();
//        predicate.getExpressions().addAll(customerFilterUtil.convertCustomerToExpressions(cb, customerRoot, filter));
//        cq.where(predicate);
        // limit
        TypedQuery<ShoppingList> q = em.createQuery(cq);
        return q.getResultList();
//        return em.createNamedQuery(ShoppingList.FIND_ALL).getResultList();
    }

    public void deleteAllShoppingLists() {
        LOGGER.trace("deleteAllShoppingLists");
        em.createNamedQuery(ShoppingListItem.DELETE_ALL).executeUpdate();
        em.createNamedQuery(ShoppingList.DELETE_ALL).executeUpdate();
        em.createNamedQuery(Product.DELETE_ALL).executeUpdate();
        em.createNamedQuery(Unit.DELETE_ALL).executeUpdate();
        em.createNamedQuery(ProductType.DELETE_ALL).executeUpdate();
    }

    public ShoppingList saveShoppingList(ShoppingList list) {
        LOGGER.trace("saveShoppingList");
        em.persist(list);
        em.flush();
        em.refresh(list);
        return list;
    }

}
