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
    @NamedQuery(name = ProductType.DELETE_ALL, query = "DELETE FROM ProductType t"),
    @NamedQuery(name = ProductType.FIND_BY_NAME, query = "SELECT t FROM ProductType t WHERE t.name = :name")
})
@Getter
@Setter
@NoArgsConstructor
public class ProductType extends AbstractEntity {

    public static final String DELETE_ALL = "ProductType.deleteAll";
    public static final String FIND_BY_NAME = "ProductType.findByName";

    public ProductType(String name) {
        this.name = name;
    }

    @Column(unique = true)
    private String name;
}
