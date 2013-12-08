package be.crydust.dukesshoppinglist.business.shoppinglist.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author kristof
 */
@Entity
@NamedQueries({
    @NamedQuery(name = ListView.DELETE_ALL, query = "DELETE FROM ListView l")
})
@Getter
@Setter
@NoArgsConstructor
public class ListView extends AbstractEntity {
    
    public static final String DELETE_ALL = "ListView.deleteAll";

    public ListView(String name) {
        this.name = name;
    }

    public static ListView createListView(String name, List<Category> categories) {
        ListView listView = new ListView(name);
        listView.categories = new ArrayList<>();
        for (Category category : categories) {
            listView.categories.add(category);
        }
        return listView;
    }

    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @OrderColumn(name = "sequenceNumber")
    private List<Category> categories;
}
