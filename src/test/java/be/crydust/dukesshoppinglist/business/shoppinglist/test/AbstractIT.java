package be.crydust.dukesshoppinglist.business.shoppinglist.test;

import java.util.Properties;
import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author kristof
 */
public abstract class AbstractIT {
    
    private static EJBContainer ejbContainer;

    @BeforeClass
    public static void startTheContainer() {
        Properties p = new Properties();
        p.put("testDB", "new://Resource?type=DataSource");
        p.put("testDB.JdbcDriver", "org.h2.Driver");
        p.put("testDB.JdbcUrl", "jdbc:h2:mem:testDB;DB_CLOSE_DELAY=-1");
        p.put("DukesShoppingListPU.openjpa.jdbc.SynchronizeMappings", "buildSchema(ForeignKeys=true)");
        p.put("DukesShoppingListPU.eclipselink.ddl-generation", "drop-and-create-tables");
        p.put("DukesShoppingListPU.eclipselink.logging.level", "fine");
        p.put("DukesShoppingListPU.eclipselink.logging.logger", "be.crydust.org.eclipse.persistence.logging.SLF4JLogger");
        p.put("DukesShoppingListPU.hibernate.hbm2ddl.auto", "create-drop");
        ejbContainer = EJBContainer.createEJBContainer(p);
    }

    @Before
    public void setUp() throws Exception {
        ejbContainer.getContext().bind("inject", this);
    }

    @AfterClass
    public static void afterClass() {
        if (ejbContainer != null) {
            ejbContainer.close();
        }
    }
}
