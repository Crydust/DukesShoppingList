package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Unit;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Unit_;
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
public class UnitBoundary implements Serializable {

    @PersistenceContext
    EntityManager em;

    public List<Unit> findAll() {
        log.trace("findAll");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Unit> cq = cb.createQuery(Unit.class);
        Root<Unit> unitRoot = cq.from(Unit.class);
        cq.distinct(true);
        cq.select(unitRoot);
        cq.orderBy(cb.asc(unitRoot.get(Unit_.name)));
        TypedQuery<Unit> q = em.createQuery(cq);
        return q.getResultList();
    }

    public Unit findById(Long id) {
        log.trace("findById");
        return em.getReference(Unit.class, id);
    }

    public Unit findByName(String name) {
        log.trace("findByName");
        try {
            return em.createNamedQuery(Unit.FIND_BY_NAME, Unit.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
