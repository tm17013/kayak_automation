package test.java.tests;

import main.java.pages.HomePage;
import main.java.driver.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VuelosTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass
    public void setUp() {
        // Inicializar el WebDriver usando DriverSetup
        driver = DriverSetup.getDriver();
        // Inicializar la página principal para navegar a Google y Kayak
        homePage = new HomePage(driver);
    }
    @Test
    public void testBuscarVuelos() {
        try {
            // Navegar a Google y buscar Kayak
            homePage.searchKayak();
            // Seleccionar el enlace correcto de Kayak
            homePage.selectKayakLink();
            // Verificar que se accedió a la URL correcta de Kayak
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.kayak.com.sv/", "No se accedió a la página correcta de Kayak");
            // Imprimir mensaje de éxito
            System.out.println("Test de búsqueda de vuelos completado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error durante el test de búsqueda de vuelos: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        // Cerrar el WebDriver
        DriverSetup.quitDriver();
    }
}
