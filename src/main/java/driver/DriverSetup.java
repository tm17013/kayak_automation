package main.java.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class DriverSetup {
   // configuración común del WebDriver
    private static WebDriver driver;

   public static WebDriver getDriver() {
       if (driver == null) {
           System.setProperty("webdriver.chrome.driver", "C:\\Users\\kenit\\Downloads\\HerramientasJava\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
           //System.setProperty("webdriver.chrome.driver", "C:\\Users\\kenit\\Downloads\\HerramientasJava\\chromedriver-win64\\chromedriver.exe");
           driver = new ChromeDriver();
           driver.manage().window().maximize();
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       }
       return driver;
   }
   public static void quitDriver() {
       if (driver != null) {
           driver.quit();
           driver = null;
       }
   }
}

