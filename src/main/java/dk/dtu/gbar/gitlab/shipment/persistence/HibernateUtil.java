package dk.dtu.gbar.gitlab.shipment.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    public static boolean cucumber = false;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();

                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);

                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();

                // Create SessionFactory
                if (cucumber) {
                    System.out.println("USING MEMORY DB");
                    String url = "jdbc:hsqldb:mem:database";
                    String user = "sa";
                    String pass = "";
                    sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                            .setProperty("hibernate.connection.url", url)
                            .setProperty("hibernate.connection.username", user)
                            .setProperty("hibernate.connection.driver_class","org.hsqldb.jdbcDriver")
                            .setProperty("hibernate.hbm2ddl.auto","update")
                            .setProperty("hibernate.connection.password", pass).buildSessionFactory();

                } else {
                    sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                            .setProperty("hibernate.connection.url","jdbc:hsqldb:file:./src/main/resources/persistence/database")
                            .setProperty("hibernate.connection.driver_class","org.hsqldb.jdbcDriver").buildSessionFactory();
                    //sessionFactory = metadata.getSessionFactoryBuilder().build();
                }
              /*  sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                        .setProperty("hibernate.connection.url", url)
                        .setProperty("hibernate.connection.username", user)
                        .setProperty("hibernate.connection.password", pass);*/
                //sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}