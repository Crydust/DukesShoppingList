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
    @NamedQuery(name = ShoppingList.FIND_ALL, query = "SELECT s FROM ShoppingList s ORDER BY s.updated DESC"),
    @NamedQuery(name = ShoppingList.FIND_NOTEMPTY, query = "SELECT s FROM ShoppingList s WHERE s.items IS NOT EMPTY ORDER BY s.updated DESC"),
    @NamedQuery(name = ShoppingList.DELETE_ALL, query = "DELETE FROM ShoppingList s")
})
@Getter
@Setter
@NoArgsConstructor
public class ShoppingList extends AbstractEntity {

    public static final String FIND_ALL = "ShoppingList.findAll";
    public static final String FIND_NOTEMPTY = "ShoppingList.findNotEmpty";
    public static final String DELETE_ALL = "ShoppingList.deleteAll";

    public ShoppingList(String name) {
        this.name = name;
    }

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "shoppingList", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ShoppingListItem> items;
}
