package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ProductType;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ProductType_;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class ProductTypeBoundary implements Serializable {

    @PersistenceContext
    EntityManager em;

    public List<ProductType> findAll() {
        log.trace("findAll");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductType> cq = cb.createQuery(ProductType.class);
        Root<ProductType> productTypeRoot = cq.from(ProductType.class);
        cq.distinct(true);
        cq.select(productTypeRoot);
        cq.orderBy(cb.asc(productTypeRoot.get(ProductType_.name)));
        TypedQuery<ProductType> q = em.createQuery(cq);
        return q.getResultList();
    }

    public ProductType findById(Long id) {
        log.trace("findById");
        return em.getReference(ProductType.class, id);
    }

    public ProductType findByName(String name) {
        log.trace("findByName");
        try {
            return em.createNamedQuery(ProductType.FIND_BY_NAME, ProductType.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
