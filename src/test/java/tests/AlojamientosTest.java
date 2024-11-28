package test.java.tests;

import main.java.driver.DriverSetup;
import main.java.pages.HomePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AlojamientosTest {
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
    public void testBuscarAlojamientos() throws InterruptedException {

//      Crea variables para el tiempo de espera
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        JavascriptExecutor js = (JavascriptExecutor) driver;
//      Variable para el boton del menu
        WebElement menuButton = null;
        try {
            menuButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mc6t-nav-button")));            if (menuButton.isDisplayed()) {
                menuButton.click(); // Despliega el menú si está colapsado
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            }
        } catch (Exception e) {
            System.out.println("Error al desplegar el menu");
        }


//        Dentro del try esta la prueba de dar click en el apartado de alojamientos y si no se puede acceder acaba el test

        try {
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/div/div[2]/div/div[4]/div/div[1]/div[1]/nav/ul/li[2]")).click();
            System.out.println("Da click en alojamientos");
        } catch (Exception e) {
            System.out.println("Error al entrar a alojamientos");
            driver.quit();
        }

//        Introduce el nombre la ciudad y lo selecciona de la lista

        WebElement searchInput = driver.findElement(By.xpath("//*[@id=\"main-search-form\"]/div/div[2]/div[1]/div/div/input"));
        searchInput.sendKeys("Nueva York, Estados Unidos");
        Thread.sleep(2000);
        searchInput.sendKeys(Keys.DOWN);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        searchInput.sendKeys(Keys.ENTER);
        System.out.println("Introduce la ciudad");

        //        Introduce la fecha de entrada y de salida de uso de la habitacion

        driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[1]/div/div/div/div[3]/table/tbody/tr[1]/td[7]")).click();
        System.out.println("Selecciona la entrada");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[5]/div/div[2]/div[1]/div/div/div/div[3]/table/tbody/tr[3]/td[3]"))).click();
        System.out.println("Selecciona la salida");

        //        Introducir numero de adultos

        driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/div[2]/div/input")).click();


        //Aumentar numero de adultos
        WebElement adultos = driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/div[2]/div/input"));
        adultos.click();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(d -> true);
        for (int i = 0; i <= 1; i++) {
            adultos.sendKeys(Keys.ARROW_LEFT);
            new WebDriverWait(driver, Duration.ofSeconds(2)).until(d -> true);
        }

        for (int i = 0; i <= 1; i++) {
            adultos.sendKeys(Keys.ARROW_RIGHT);
        }
        System.out.println("Aumenta la cantidad a 3 adultos");
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(d -> true);


        //        Introducir numero de Ninios

        WebElement ninios = driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/div[3]/div/div/input"));
        ninios.sendKeys("3"); //Agregar 3 ninios
        System.out.println("Aumenta la cantidad a 3 niños");
        Thread.sleep(2000);

        //Edad_ninio1

        WebElement ninio1 = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[2]/div/div[3]/div[2]/div[1]/div/div/div/div"));
        ninio1.click();
        Thread.sleep(2000);

        for (int i = 0; i <= 2; i++) {  //Digita 3 años
            ninio1.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
        }

        ninio1.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        //Edad_ninio2

        WebElement ninio2 = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[2]/div/div[3]/div[2]/div[2]/div/div/div/div"));
        ninio1.click();


        Thread.sleep(3000);
        for (int i = 0; i <= 6; i++) {  //Digita 7 años
            ninio2.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
        }
        ninio2.sendKeys(Keys.RETURN);
        Thread.sleep(2000);
//
//        //Edad_ninio3
//
//        WebElement ninio3 = driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/div[3]/div[2]/div[3]/div/div/div/div"));
//        ninio3.click();
//        Thread.sleep(2000);
//        ninio3.sendKeys(Keys.ARROW_DOWN);
//        Thread.sleep(2000);
//        ninio3.sendKeys(Keys.ENTER);
//        Thread.sleep(2000);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("idDelSpinner")));

        //        Da click en el boton de buscar

        WebElement buscarButton = driver.findElement(By.xpath("//*[@id=\"main-search-form\"]/div/div[2]/span/span/button/div"));
        buscarButton.click();
        System.out.println("Da click en buscar ");
        Thread.sleep(10000);

        // Cambiar al contexto de la nueva pestaña
        String originalWindow = driver.getWindowHandle();
        wait.until(driver -> driver.getWindowHandles().size() > 1);

        // Cambiar al nuevo contexto
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        System.out.println("Se mueve a nueva pestaña");
        // Esperar a que la nueva pestaña esté completamente cargada
        wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
        System.out.println("Página completamente cargada en la nueva pestaña");


        // Esperar que los elementos estén presentes
        try {
            List<WebElement> elementNames = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".gfww")));
            System.out.println("Se encontraron " + elementNames.size() + " elementos");

            // Recorrer los elementos y obtener el texto
            for (WebElement element : elementNames) {
                System.out.println("Resultado: " + element.getText());
            }
        } catch (TimeoutException e) {
            System.out.println("Error: No se encontraron elementos con el selector '.gfww'. Verifica el selector o aumenta el tiempo de espera.");
        }

        // Esperar a que los resultados estén visibles
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".gfww")));
        System.out.println("Se localizo");
        List<WebElement> elementNames = driver.findElements(By.cssSelector(".gfww"));
        System.out.println("Se encontro");


        // Recorrer la lista y obtener el texto de cada elemento
        for (WebElement element : elementNames) {
            String resultados = element.getText();
            System.out.println("Total de elementos encontrados: " + resultados + " de Alojamientos");
        }

// Recorrer la lista y obtener el texto de cada elemento
        for (WebElement element : elementNames) {
            String resultados = element.getText();
            System.out.println("Total de elementos encontrados: " + resultados + " de Alojamientos");
        }
        Thread.sleep(10000);



    }


    @AfterClass
    public void tearDown() {
        DriverSetup.quitDriver();
    }
}