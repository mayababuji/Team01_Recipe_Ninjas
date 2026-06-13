package base;

import driverManager.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DBConnection;
import utils.DBQueries;

import java.sql.Connection;

public class BaseTest {

    protected WebDriver driver;
    protected Connection conn;
    protected DBQueries dbQueries;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.initializeDriver();
        conn = DBConnection.getConnection();

        dbQueries = new DBQueries();
        dbQueries.createAllTables(conn);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        DBConnection.closeConnection(conn);
    }
}