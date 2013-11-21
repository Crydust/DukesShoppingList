package be.crydust.dukesshoppinglist.business.shoppinglist.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 *
 * @author kristof
 */
@Entity
@NamedQueries({
    @NamedQuery(name = ShoppingList.DELETE_ALL, query = "DELETE FROM ShoppingList s")
})
@Getter
@Setter
@NoArgsConstructor
public class ShoppingList extends AbstractEntity {

    public static final String DELETE_ALL = "ShoppingList.deleteAll";

    public ShoppingList(String name) {
        this.name = name;
    }

    public ShoppingList(String name, List<ShoppingListItem> items) {
        this.name = name;
        this.items = items;
        for (ShoppingListItem item : items) {
            item.setShoppingList(this);
        }
    }

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL)
    private List<ShoppingListItem> items;
}
