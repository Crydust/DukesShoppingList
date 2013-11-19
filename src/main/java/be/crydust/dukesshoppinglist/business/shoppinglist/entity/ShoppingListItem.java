package be.crydust.dukesshoppinglist.business.shoppinglist.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
public class ShoppingListItem extends AbstractEntity {

    public ShoppingListItem(ShoppingList shoppingList, String quantity, Unit unit, Product product) {
        this.shoppingList = shoppingList;
        this.quantity = quantity;
        this.unit = unit;
        this.product = product;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ShoppingList shoppingList;

    @Column
    private String quantity;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Unit unit;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Product product;
}
