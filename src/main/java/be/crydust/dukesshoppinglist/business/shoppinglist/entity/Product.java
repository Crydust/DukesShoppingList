package be.crydust.dukesshoppinglist.business.shoppinglist.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
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
public class Product extends AbstractEntity {

    public Product(String name, ProductType type) {
        this.name = name;
        this.type = type;
    }

    @Column(unique = true)
    private String name;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ProductType type;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Unit defaultUnit;
}
