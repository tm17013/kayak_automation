package test.java.tests;

import main.java.driver.DriverSetup;
import main.java.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AlojamientosTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass
    public void setUp() {
        driver = DriverSetup.getDriver();
        homePage = new HomePage(driver);
    }

    @Test
    public void testBuscarAlojamientos() {
        try {
            homePage.searchKayak();
            homePage.selectKayakLink();
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.kayak.com.sv/stays", "No se accedió a la página correcta de Kayak - Alojamientos");
            System.out.println("Test de búsqueda de alojamientos completado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error durante el test de búsqueda de alojamientos: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        DriverSetup.quitDriver();
    }
}

