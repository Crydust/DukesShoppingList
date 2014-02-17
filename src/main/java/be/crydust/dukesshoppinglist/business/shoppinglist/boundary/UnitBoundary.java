package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.control.CrudService;
import be.crydust.dukesshoppinglist.business.shoppinglist.control.QueryParameter;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Unit;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author kristof
 */
@Stateless
@Slf4j
public class UnitBoundary implements Serializable {

    @Inject
    CrudService crudService;

    @SuppressWarnings("unchecked")
    public List<Unit> findAll() {
        return (List<Unit>) crudService.findWithNamedQuery(Unit.FIND_ALL);
    }

    public Unit find(Long id) {
        return crudService.find(Unit.class, id);
    }

    public Unit findByName(String name) {
        List<?> units = crudService.findWithNamedQuery(
                Unit.FIND_BY_NAME,
                QueryParameter.with("name", name).parameters());
        Unit unit = null;
        if (!units.isEmpty()) {
            unit = (Unit) units.get(0);
        }
        return unit;
    }

}
