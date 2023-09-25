package org.example.bl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        try {
            File configFile = new File("src\\main\\resources\\hibernate.cfg.xml");
            return new Configuration().configure(configFile).buildSessionFactory();
        }
        catch (Throwable ex) {
            System.err.println("Initial Session Factory creation failed" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            initSessionFactory();
        }
        return sessionFactory;
    }

    public static void close() {
        getSessionFactory().close();
    }
}
