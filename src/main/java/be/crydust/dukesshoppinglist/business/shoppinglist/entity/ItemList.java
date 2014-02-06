package be.crydust.dukesshoppinglist.business.shoppinglist.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 *
 * @author kristof
 */
@Entity
@NamedQueries({
    @NamedQuery(name = ItemList.DELETE_ALL, query = "DELETE FROM ItemList l")
})
@Getter
@Setter
@NoArgsConstructor
public class ItemList extends AbstractEntity {

    public static final String DELETE_ALL = "ItemList.deleteAll";

    public ItemList(String name) {
        this.name = name;
    }

    public static ItemList createItemList(String name, List<Item> items) {
        ItemList itemList = new ItemList(name);
        itemList.items = new ArrayList<>();
        for (Item item : items) {
            itemList.items.add(item);
            item.setItemList(itemList);
        }
        return itemList;
    }

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "itemList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn(name = "sequenceNumber")
    private List<Item> items;
    
}
