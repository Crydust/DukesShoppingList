package be.crydust.dukesshoppinglist.presentation;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author kristof
 */
@WebListener
public class TomcatServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // For Tomcat and Jboss this isn't enough
        /*
         <context-param>
         <param-name>javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL</param-name>
         <param-value>true</param-value>
         </context-param>
         */
        System.setProperty("org.apache.el.parser.COERCE_TO_ZERO", "false");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // NOOP
    }

}
