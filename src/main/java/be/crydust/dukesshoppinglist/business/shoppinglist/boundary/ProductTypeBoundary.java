package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.control.CrudService;
import be.crydust.dukesshoppinglist.business.shoppinglist.control.QueryParameter;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ProductType;
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
public class ProductTypeBoundary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    CrudService crudService;

    @SuppressWarnings("unchecked")
    public List<ProductType> findAll() {
        return (List<ProductType>) crudService.findWithNamedQuery(ProductType.FIND_ALL);
    }

    public ProductType find(Long id) {
        return crudService.find(ProductType.class, id);
    }

    public ProductType findByName(String name) {
        List<?> productTypes = crudService.findWithNamedQuery(
                ProductType.FIND_BY_NAME,
                QueryParameter.with("name", name).parameters());
        ProductType productType = null;
        if (!productTypes.isEmpty()) {
            productType = (ProductType) productTypes.get(0);
        }
        return productType;
    }
}
