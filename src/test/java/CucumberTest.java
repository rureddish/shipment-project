
import dk.dtu.gbar.gitlab.shipment.persistence.HibernateUtil;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources")
public class CucumberTest {
    /* private static Configuration configuration;*/
    /*static String url = "jdbc:derby:memory:database;create=true";
    static String user = "SA";
    static String pass = "";*/

    @BeforeClass
    public static void setMemoryDB() {
        HibernateUtil.cucumber = true;
     /*   // gets the mapped configuration to the db
        configuration.setProperty("hibernate.connection.url", url);
        configuration.setProperty("hibernate.connection.username", user);
        configuration.setProperty("hibernate.connection.password", pass);

        // rebuilds the configuration using my test database
        HibernateUtil.rebuildSessionFactory(configuration);

        // executes a script stored in test/resources/cucumber*/
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);
            ScriptRunner runner = new ScriptRunner(conn, false, true);

            runner.runScript(new BufferedReader(new FileReader("test/resources/prepare.sql")));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }*/
    }
}
