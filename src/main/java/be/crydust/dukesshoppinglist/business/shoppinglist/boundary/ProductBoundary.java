package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product_;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author kristof
 */
@Stateless
@Slf4j
public class ProductBoundary implements Serializable {
    
    @PersistenceContext
    EntityManager em;

    public List<Product> findAllProducts() {
        log.trace("findAllProducts");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> productRoot = cq.from(Product.class);
        cq.distinct(true);
        cq.select(productRoot);
        cq.orderBy(cb.asc(productRoot.get(Product_.name)));
        TypedQuery<Product> q = em.createQuery(cq);
        return q.getResultList();
    }

    public Product findProductById(Long id) {
        log.trace("findProductById");
        return em.getReference(Product.class, id);
    }
}
