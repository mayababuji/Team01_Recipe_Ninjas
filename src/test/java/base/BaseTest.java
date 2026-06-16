package base;

import driverManager.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DBConnection;
import utils.DBQueries;

import java.sql.Connection;
import java.util.Set;

public class BaseTest {

    protected WebDriver driver;
    protected Connection conn;
    protected DBQueries dbQueries;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.initializeDriver();
        conn = DBConnection.getConnection();

        dbQueries = new DBQueries();
        //dbQueries.createAllTables(conn);
        
 /* ***************************************Code Added*************************************** */
        
        /*this code first checks for the table, if the table doesn't exist in DB then it will create the table*/
        
        Set<String> existingTables = dbQueries.getExistingTables(conn);

		for (String table : DBQueries.tableNames) {

			if (existingTables.contains(table.toLowerCase())) {

				System.out.println("Table already exists : " + table);

			} else {

				System.out.println("Table does not exist : " + table);

				dbQueries.createTable(conn, table);
			}
		}

    }
    	 	
/* ********************************************** Added Code End*********************************** */

    

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        DBConnection.closeConnection(conn);
    }
}