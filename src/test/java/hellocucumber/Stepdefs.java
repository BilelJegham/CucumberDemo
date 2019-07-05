package hellocucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import static org.junit.Assert.*;

public class Stepdefs {
    private Object answer;


@When("I ask how many {string}")
public void i_ask_how_many(String string) {
    // Write code here that turns the phrase above into concrete actions
    
    this.answer = countTable(string);
}

@Then("I should be told {int}")
public void i_should_be_told(int var) {
    // Write code here that turns the phrase above into concrete actions
    
    assertEquals(var, answer);
}


private int countTable(String name){
    int rowCount = -1;

    try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:/@0.0.0.0:1521/ORCLCDB", "c##hr", "hr")) {

        if (con != null) {
            
            try{
                Statement stmt = con.createStatement();

                
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM "+name);
                

                while(rs.next()){
                    rowCount = rs.getInt("COUNT(*)");				
                }
                // close ResultSet rs
                rs.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to make connection!");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
    }


    return rowCount;
}
}
