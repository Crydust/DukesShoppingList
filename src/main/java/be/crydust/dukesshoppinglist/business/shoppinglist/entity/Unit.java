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
    @NamedQuery(name = Unit.DELETE_ALL, query = "DELETE FROM Unit u")
})
@Getter
@Setter
@NoArgsConstructor
public class Unit extends AbstractEntity {

    public static final String DELETE_ALL = "Unit.deleteAll";
    
    @Column(unique = true)
    private String name;

    public Unit(String name) {
        this.name = name;
    }
}
