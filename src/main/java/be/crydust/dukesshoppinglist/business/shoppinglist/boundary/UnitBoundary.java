package be.crydust.dukesshoppinglist.business.shoppinglist.boundary;

import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Unit;
import be.crydust.dukesshoppinglist.business.shoppinglist.entity.Unit_;
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
public class UnitBoundary implements Serializable {

    @PersistenceContext
    EntityManager em;

    public List<Unit> findAllUnits() {
        log.trace("findAllUnits");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Unit> cq = cb.createQuery(Unit.class);
        Root<Unit> unitRoot = cq.from(Unit.class);
        cq.distinct(true);
        cq.select(unitRoot);
        cq.orderBy(cb.asc(unitRoot.get(Unit_.name)));
        TypedQuery<Unit> q = em.createQuery(cq);
        return q.getResultList();
    }

    public Unit findUnitById(Long id) {
        log.trace("findUnitById");
        return em.getReference(Unit.class, id);
    }

}
