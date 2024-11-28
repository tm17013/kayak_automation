package test.java.tests;

import main.java.driver.DriverSetup;
import main.java.pages.HomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        Actions actions = new Actions(driver);


//        Dentro del try esta la prueba de dar click en el apartado de vuelos directos y si no se puede acceder acaba el test

        try {
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/div/div[2]/div/div[4]/div/div[1]/div[3]/nav/ul/li[3]")).click();
            System.out.println("Da click en vuelos directos");
        } catch (Exception e) {
            System.out.println("Error al entrar a vuelos directos");
            driver.quit();
        }



        // Desplazar la página hasta el campo del aeropuerto y seleccionarlo
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement aeropuertoInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main\"]/div[1]/div/div[1]/div[1]/div/div")));
        js.executeScript("arguments[0].scrollIntoView(true);", aeropuertoInput); // Desplaza el campo a la vista
        aeropuertoInput.sendKeys("Internacional de El Salvador, San Salvador, El Salvador");
        System.out.println("Introduce el aeropuerto");

        // Desplazar la página hasta el campo de fecha y seleccionarlo
        WebElement fechaInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main\"]/div[1]/div/div[1]/div[2]/div/div")));
        js.executeScript("arguments[0].scrollIntoView(true);", fechaInput); // Desplaza el campo de fecha a la vista
        fechaInput.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"2024-12\"]"))).click();
        System.out.println("Selecciona diciembre");


//        Da click en el botón de buscar

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