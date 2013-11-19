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
public class ProductType extends AbstractEntity {

    public ProductType(String name) {
        this.name = name;
    }

    @Column(unique = true)
    private String name;
}
