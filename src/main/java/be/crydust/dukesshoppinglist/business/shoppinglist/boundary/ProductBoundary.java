package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ProductType;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.ProductType_;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Product_;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
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

    @PersistenceContext
    EntityManager em;

    public List<Product> findAll() {
        log.trace("findAll");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> productRoot = cq.from(Product.class);
        cq.distinct(true);
        cq.select(productRoot);
        cq.orderBy(cb.asc(productRoot.get(Product_.name)));
        TypedQuery<Product> q = em.createQuery(cq);
        return q.getResultList();
    }

    public List<Product> findAllOrderedByProductType() {
        log.trace("findAll");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> productRoot = cq.from(Product.class);
        productRoot.fetch(Product_.type);
        cq.distinct(true);
        cq.select(productRoot);
        cq.orderBy(
                cb.asc(productRoot.get(Product_.type).get(ProductType_.name)),
                cb.asc(productRoot.get(Product_.name)));
        TypedQuery<Product> q = em.createQuery(cq);
        return q.getResultList();
    }

    public Product findById(Long id) {
        log.trace("findById");
        return em.getReference(Product.class, id);
    }

    public Product findByName(String name) {
        log.trace("findByName");
        try {
            return em.createNamedQuery(Product.FIND_BY_NAME, Product.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
