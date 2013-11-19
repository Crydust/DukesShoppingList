package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Child;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Parent;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ShoppingList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        return em.createNamedQuery(ShoppingList.FIND_ALL).getResultList();
    }

    public void deleteAllShoppingLists() {
        LOGGER.trace("deleteAllShoppingLists");
        em.createNamedQuery(ShoppingList.DELETE_ALL).executeUpdate();
    }

    public void saveShoppingList(ShoppingList list) {
        LOGGER.trace("saveShoppingList");
        em.persist(list);
    }
    
    public void family(){
        LOGGER.trace("family");
        Parent p = new Parent();
        p.setItems(Arrays.asList(new Child()));
        LOGGER.trace("persist");
        em.persist(p);
        LOGGER.trace("remove");
        em.remove(p);
        LOGGER.trace("done");
    }
}
