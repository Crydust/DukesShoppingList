package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.control.CrudService;
import be.crydust.dukesshoppinglist.business.shoppinglist.control.QueryParameter;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ProductType;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ProductType_;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product_;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author kristof
 */
@Stateless
@Slf4j
public class ProductBoundary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    CrudService crudService;

    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        return (List<Product>) crudService.findWithNamedQuery(Product.FIND_ALL);
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAllOrderedByProductType() {
        return (List<Product>) crudService.findWithNamedQuery(Product.FIND_ALL_ORDER_BY_PRODUCTTYPE_NAME);
    }

    public Product find(Long id) {
        return crudService.find(Product.class, id);
    }

    public Product findByName(String name) {
        List<?> products = crudService.findWithNamedQuery(
                Product.FIND_BY_NAME,
                QueryParameter.with("name", name).parameters());
        Product product = null;
        if (!products.isEmpty()) {
            product = (Product) products.get(0);
        }
        return product;
    }
}
