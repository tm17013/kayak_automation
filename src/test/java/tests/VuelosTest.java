package test.java.tests;

import main.java.pages.HomePage;
import main.java.driver.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class VuelosTest {
    private WebDriver driver;
    private HomePage homePage;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Inicializar el WebDriver usando DriverSetup
        driver = DriverSetup.getDriver();
        // Inicializar la página principal para navegar a Google y Kayak
        homePage = new HomePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test
    public void testBuscarVuelos() {
        {
            // Paso 1-KTC-01: Navegar a Google, buscar Kayak y verificar la URL de Kayak
            homePage.searchKayak();
            homePage.selectKayakLink();
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.kayak.com.sv/", "No se accedió a la página correcta de Kayak");
            System.out.println("Paso 1 completado: Navegar a la URL de Kayak correctamente.");

            // Paso 2-KTC-01: Seleccionar el tipo de vuelo Ida y Vuelta
            // Esperar y hacer clic en el botón de viaje de ida
            WebElement oneWayTripButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".AFFP-m")));
            oneWayTripButton.click();
            System.out.println("Botón de viaje de ida seleccionado.");
            // Esperar y hacer clic en el botón de ida y vuelta
            WebElement roundTripButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#roundtrip > span")));
            roundTripButton.click();
            System.out.println("Botón de ida y vuelta seleccionado.");
            System.out.println("Paso 2 completado: Tipo de vuelo Ida y Vuelta seleccionado correctamente.");

           //TestCaseVuelos Exitoso
            System.out.println("KTC-01 exitoso!");
        }
    }
    @AfterClass
    public void tearDown() {
        // Cerrar el WebDriver
        DriverSetup.quitDriver();
    }
}
