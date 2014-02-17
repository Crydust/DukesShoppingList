package be.crydust.dukesshoppinglist.business.shoppinglist.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @NamedQuery(name = Item.DELETE_ALL, query = "DELETE FROM Item i"),
    @NamedQuery(name = Item.DELETE_BY_ID, query = "DELETE FROM Item i WHERE i.id = :id")
})
@Getter
@Setter
@NoArgsConstructor
public class Item extends AbstractEntity {

    public static final String DELETE_ALL = "Item.deleteAll";
    public static final String DELETE_BY_ID = "Item.deleteById";

    public Item(String quantity, Unit unit, Product product) {
        this.quantity = quantity;
        this.unit = unit;
        this.product = product;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ItemList itemList;

    @Column
    private String quantity;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Unit unit;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Product product;
}
