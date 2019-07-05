package hellocucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"})
public class RunCucumberTest {
    public static Connection conn = null;

    @BeforeClass
    public static void preparaBase() {

        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCLCDB", "c##hr", "hr")) {

            if (con != null) {
                conn = con;
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
