package test.java.tests;

import main.java.driver.DriverSetup;
import main.java.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VuelosdirectosTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass
    public void setUp() {
        driver = DriverSetup.getDriver();
        homePage = new HomePage(driver);
    }

    @Test
    public void testBuscarVuelosDirectos() {
        try {
            homePage.searchKayak();
            homePage.selectKayakLink();
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.kayak.com.sv/direct", "No se accedió a la página correcta de vuelos directos en Kayak");
            System.out.println("Test de búsqueda de vuelos directos completado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error durante el test de búsqueda de vuelos directos: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        DriverSetup.quitDriver();
    }
}
