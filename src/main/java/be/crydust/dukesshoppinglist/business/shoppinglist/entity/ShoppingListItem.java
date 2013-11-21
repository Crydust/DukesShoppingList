package be.crydust.dukesshoppinglist.business.shoppinglist.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author kristof
 */
@Entity
@NamedQueries({
    @NamedQuery(name = ShoppingListItem.DELETE_ALL, query = "DELETE FROM ShoppingListItem i"),
    @NamedQuery(name = ShoppingListItem.DELETE_BY_SHOPPINGLIST, query = "DELETE FROM ShoppingListItem i WHERE i.shoppingList = :shoppingList")
})
@Getter
@Setter
@NoArgsConstructor
public class ShoppingListItem extends AbstractEntity {

    public static final String DELETE_ALL = "ShoppingListItem.deleteAll";
    public static final String DELETE_BY_SHOPPINGLIST = "ShoppingListItem.deleteByShoppingList";

    public ShoppingListItem(ShoppingList shoppingList, String quantity, Unit unit, Product product) {
        this.shoppingList = shoppingList;
        this.quantity = quantity;
        this.unit = unit;
        this.product = product;
    }

    @ManyToOne
    @JoinColumn
    private ShoppingList shoppingList;

    @Column
    private String quantity;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Unit unit;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Product product;
}
