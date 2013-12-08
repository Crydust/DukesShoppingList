package be.crydust.dukesshoppinglist.presentation;

import be.crydust.dukesshoppinglist.business.shoppinglist.boundary.ShoppingListBoundary;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ItemList;
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
    
    List<ItemList> list = null;
    
    public List<ItemList> getLists(){
        if (list == null) {
            list = boundary.findAllItemLists();
        }
        return list;
    }
    
}
