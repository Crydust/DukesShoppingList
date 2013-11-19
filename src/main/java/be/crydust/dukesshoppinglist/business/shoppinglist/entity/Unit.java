package be.crydust.dukesshoppinglist.business.shoppinglist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author kristof
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Unit extends AbstractEntity {

    @Column(unique = true)
    private String name;

    public Unit(String name) {
        this.name = name;
    }
}
