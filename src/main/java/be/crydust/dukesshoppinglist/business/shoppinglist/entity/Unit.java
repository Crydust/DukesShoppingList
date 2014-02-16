package be.crydust.dukesshoppinglist.business.shoppinglist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author kristof
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Unit.DELETE_ALL, query = "DELETE FROM Unit u"),
    @NamedQuery(name = Unit.FIND_BY_NAME, query = "SELECT u FROM Unit u WHERE u.name = :name")
})
@Getter
@Setter
@NoArgsConstructor
public class Unit extends AbstractEntity {

    public static final String DELETE_ALL = "Unit.deleteAll";
    public static final String FIND_BY_NAME = "Unit.findByName";
    
    @Column(unique = true)
    private String name;

    public Unit(String name) {
        this.name = name;
    }
}
