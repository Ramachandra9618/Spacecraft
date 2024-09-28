package testcases;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
        public static WebDriver driver;
    Properties prop = new Properties();

        @BeforeMethod
        void setUp() {
            try {
                FileInputStream files = new FileInputStream("configurations/config.properties");
                prop.load(files);
            } catch (Exception e) {
                System.out.println(e);
            }
            String browser =  prop.getProperty("browser");
            System.out.println(browser);
            switch (browser.toLowerCase()){
                case "chrome" :
                    driver = new ChromeDriver();
                    break;
                case "Edge" :
                    driver = new EdgeDriver();
                    break;
                default:
                    System.out.println( "No Browser found");
            }

            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(prop.getProperty("url"));

        }

    @AfterMethod
    void tearDown()  {
        driver.quit();
    }


    }
