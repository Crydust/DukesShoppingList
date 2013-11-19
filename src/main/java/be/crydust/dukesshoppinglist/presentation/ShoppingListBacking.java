package be.crydust.dukesshoppinglist.presentation;

import be.crydust.dukesshoppinglist.business.shoppinglist.boundary.ShoppingListBoundary;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ShoppingList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author kristof
 */
@Named
@RequestScoped
public class ShoppingListBacking {
    
    @Inject
    ShoppingListBoundary boundary;
    
    List<ShoppingList> list = null;
    
    public List<ShoppingList> getLists(){
        if (list == null) {
            list = boundary.findAllShoppingLists();
        }
        return list;
    }
    
}
