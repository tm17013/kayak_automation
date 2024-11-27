package test.java.tests;

import main.java.driver.DriverSetup;
import main.java.pages.HomePage;
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
import java.util.List;

public class VuelosdirectosTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass
    public void setUp() {
        driver = DriverSetup.getDriver();
        homePage = new HomePage(driver);
        homePage.searchKayak();
        homePage.selectKayakLink();
    }

    @Test
    public void testBuscarVuelosDirectos() {


//      Crea variables para el tiempo de espera
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
//      Variable para el boton del menu
        WebElement menuButton = null;
//        Verifica si el menu esta desplegado u da click por si no
        menuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mc6t-nav-button")));
        menuButton.click();
        System.out.println("Despliega el menu");

//        Dentro del try esta la prueba de dar click en el apartado de vuelos directos y si no se puede acceder acaba el test

        try {
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/div/div[2]/div/div[4]/div/div[1]/div[3]/nav/ul/li[3]")).click();
            System.out.println("Da click en vuelos directos");
        } catch (Exception e) {
            System.out.println("Error al entrar a vuelos directos");
            driver.quit();
        }

//        Introduce el nombre del aeropuerto y lo selecciona de la lista

            driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div/div[1]/div[1]/div/div")).sendKeys("Internacional de El Salvador, San Salvador, El Salvador");
            System.out.println("Introduce el aeropuesrto");



//        Introduce la fecha y la selecciona de la lista emergente

        driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div/div[1]/div[2]/div/div/div[2]")).click();
        System.out.println("Selecciona la fecha");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"2024-12\"]"))).click();
        System.out.println("Selecciona diciembre");

//        Da click en el boton de buscar

        driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div/div[1]/div[3]/button")).click();
        System.out.println("Da click en buscar ");

//        Hace un conteo de los resultados

        List<WebElement> elementName = driver.findElements(By.className("c2A-P"));
        System.out.println("Total de elementos encontrados:"+elementName.size()+" Vuelos directos");
        System.out.println("Test de búsqueda de vuelos directos completado exitosamente.");

    }

    @AfterClass
    public void tearDown() {
        DriverSetup.quitDriver();
    }
}
