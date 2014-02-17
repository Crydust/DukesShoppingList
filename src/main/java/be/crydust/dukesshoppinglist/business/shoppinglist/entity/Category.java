package be.crydust.dukesshoppinglist.business.shoppinglist.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author kristof
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Category.DELETE_ALL, query = "DELETE FROM Category c")
})
@Getter
@Setter
@NoArgsConstructor
public class Category extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String DELETE_ALL = "Category.deleteAll";

    public Category(String name) {
        this.name = name;
    }

    public static Category createCategory(String name, List<Item> items) {
        Category category = new Category(name);
        category.items = new ArrayList<>();
        for (Item item : items) {
            category.items.add(item);
        }
        return category;
    }

    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy(value = "name")
    private List<Item> items;
}
