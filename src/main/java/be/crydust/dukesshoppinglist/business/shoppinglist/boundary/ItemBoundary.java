package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.control.CrudService;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Item;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author kristof
 */
@Stateless
@Slf4j
public class ItemBoundary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    CrudService crudService;

    public void delete(Long id) {
        crudService.delete(Item.class, id);
    }

}
