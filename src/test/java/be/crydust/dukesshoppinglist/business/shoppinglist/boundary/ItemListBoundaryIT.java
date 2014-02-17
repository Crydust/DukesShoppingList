package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ItemList;
import be.crydust.dukesshoppinglist.business.shoppinglist.test.AbstractIT;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

/**
 *
 * @author kristof
 */
@ManagedBean
public class ItemListBoundaryIT extends AbstractIT {
    
    @Inject
    ItemListBoundary cut;
    
    @Test
    public void testCreateFromCsv(){
        ItemList list = cut.createFromCsv("name", ""
                + "1,x,Eye of newt,Witchcraft\n"
                + "2,x,Toad,Witchcraft");
        assertThat(list, is(not(nullValue())));
    }
    
}
