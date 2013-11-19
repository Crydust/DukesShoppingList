package be.crydust.dukesshoppinglist.business.shoppinglist.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author kristof
 */
public class ProductTest {

    @Test
    public void testEquals() {
        Product one = new Product();
        one.setId(1L);
        one.setName("one");
        Product anotherOne = new Product();
        anotherOne.setId(1L);
        anotherOne.setName("anotherOne");
        Product two = new Product();
        two.setId(2L);
        two.setName("two");
        assertTrue(one.equals(one));
        assertTrue(one.equals(anotherOne));
        assertFalse(one.equals(two));
    }
}
