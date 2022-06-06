package com.learning;

//import java.util.logging.Logger;
//import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@WebListener
public class EMF implements ServletContextListener {
    private static Logger logger = LogManager.getLogger();
    
    
    private static EntityManagerFactory emf;
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.debug("Creating entity manager factory.");
        
        emf = Persistence.createEntityManagerFactory("JPAWebAppPU");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        emf.close();
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }

        return emf.createEntityManager();
    }  
}