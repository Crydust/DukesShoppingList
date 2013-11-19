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
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author kristof
 */
@Entity
@NamedQueries({
    @NamedQuery(name = ShoppingList.FIND_ALL, query = "SELECT s FROM ShoppingList s ORDER BY s.name"),
    @NamedQuery(name = ShoppingList.DELETE_ALL, query = "DELETE FROM ShoppingList s")
})
@Getter
@Setter
@NoArgsConstructor
public class ShoppingList extends AbstractEntity {

    public static final String FIND_ALL = "ShoppingList.findAll";
    public static final String DELETE_ALL = "ShoppingList.deleteAll";

    public ShoppingList(String name) {
        this.name = name;
    }

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "shoppingList", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ShoppingListItem> items;
}
