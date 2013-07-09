package com.savekirk.lecturenote;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Singleton class that creates a single instance of a JPA entity
 * factory as the factory is an expensive creation operation. Code
 * taken from Programming Google App Engine page 208
 * 
 * @author savekirk@gmail.com
  */
public final class EMF {
    private static final EntityManagerFactory emfInstance = Persistence
	    .createEntityManagerFactory("transactions-optional");

    private EMF() {
	// intentionally empty
    }

    /**
     * Gets the singleton instance of a
     * <code>EntityManagerFactory</code>. This instance is statically
     * created upon class loading.
     * 
     * @return EntityManagerFactory instance
     */
    public static EntityManagerFactory get() {
	return emfInstance;
    }
}