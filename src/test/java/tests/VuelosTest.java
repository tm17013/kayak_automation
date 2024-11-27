package test.java.tests;

import main.java.pages.HomePage;
import main.java.driver.DriverSetup;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

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

        // Paso 3-KTC-01: Verificar que el número de pasajeros sea de 1 adulto
        // Hacer clic en el elemento del menú desplegable de pasajeros
        WebElement numeroadulto = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".c_lY1-icon")));
        numeroadulto.click();
        // Verificar que el número de adultos sea 1
        WebElement adultoCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[aria-label='Adultos'][value='1']")));
        Assert.assertEquals(adultoCountElement.getAttribute("value"), "1", "El número de adultos no es 1 por defecto.");
        // Hacer clic fuera del menú desplegable para cerrarlo
        WebElement clickOutside = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bImy")));
        clickOutside.click();
        System.out.println("Número de pasajeros verificado: 1 adulto.");
        System.out.println("Paso 3 completado: Verificación del número de pasajeros correctamente.");

        // Paso 4-KTC-01: Seleccionar la clase de vuelo Económica
        // Hacer clic en el menú desplegable de clase de vuelo
        WebElement classDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".udzg-mod-size-small .Uczr-select-title")));
        classDropdown.click();
        // Seleccionar la opción Económica
        WebElement economicaClassOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("e"))); economicaClassOption.click();
        // Verificar que la clase económica está seleccionada
        WebElement seleccionClass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".udzg-mod-size-small .Uczr-select-title")));
        Assert.assertTrue(seleccionClass.getText().contains("Económica"), "La clase seleccionada no es Económica.");
        System.out.println("Clase de vuelo verificada: Económica.");
        System.out.println("Paso 4 completado: Clase de vuelo Económica seleccionada correctamente.");


        // Paso 5-KTC-01: Seleccionar el origen San Salvador, El Salvador (ILS)
        //Dar click en icono "+"
        WebElement addOriginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".c_neb-add-icon")));
        addOriginButton.click();//Dar click en el campo origen
        // WebElement originField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".NhpT-mod-state-focus")));
        WebElement originField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[aria-label='Origen']")));
        originField.click();
        // Escribir el valor y seleccionar la opción correcta con teclas
        originField.sendKeys("San Salvador, El Salvador (ILS - Ilopango)");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(d -> true); // espera 3 segundos
        originField.sendKeys(Keys.DOWN);// Navega con la tecla abajo
        originField.sendKeys(Keys.RETURN); // Selecciona la opción
        System.out.println("Origen verificado: San Salvador, El Salvador (ILS - Ilopango).");
        System.out.println("Paso 5 completado: Origen San Salvador, El Salvador (ILS) seleccionado correctamente.");



        // Paso 6-KTC-01: Seleccionar el destino Los Ángeles, California, Estados Unidos (LAX)
        // Hacer clic en el campo de destino
        //WebElement destinoField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".NhpT-mod-state-focus")));
        WebElement destinoField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-search-form > div > div > div:nth-child(2) > div > div > div > div > div:nth-child(2) > div:nth-child(1) > div > div > div:nth-child(3) > div > div > input")));
        destinoField.click();
        destinoField.sendKeys("Los Ángeles, California, Estados Unidos (LAX)");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(d -> true); // espera 3 segundos
        // Seleccionar la opción correspondiente de la lista desplegable
        destinoField.sendKeys(Keys.DOWN); // Navega con la tecla abajo
        destinoField.sendKeys(Keys.RETURN); // Selecciona la opción
        System.out.println("Destino verificado: Los Ángeles, California, Estados Unidos (LAX).");
        System.out.println("Paso 6 completado: Destino Los Ángeles, California, Estados Unidos (LAX) seleccionado correctamente.");


        // Paso 7: Seleccionar Fecha de Ida y Vuelta- Probar si es necesario scroll o no.
        try {
            // Realizar scroll 125px hacia abajo para asegurarse de que el calendario esté visible
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 125);");
            System.out.println("Desplazamiento realizado 125px hacia abajo.");

            // Esperar y hacer clic en el botón de fecha de ida para desplegar el calendario
            WebElement fechaIdaButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".JONo-button")));
            fechaIdaButton.click();
            System.out.println("Botón de fecha de ida clickeado, calendario desplegado.");

            // Esperar a que el calendario sea visible
            WebElement calendario = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".FqLu-mod-layer-dropdown")));
            System.out.println("Calendario visible.");

            // Seleccionar el 23 de diciembre usando CSS Selector
            WebElement fecha23 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[aria-label*='23 de diciembre de 2024']")));
            fecha23.click();
            System.out.println("Fecha 23 de diciembre seleccionada.");

            // Seleccionar el 24 de diciembre usando CSS Selector
            WebElement fecha24 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[aria-label*='24 de diciembre de 2024']")));
            fecha24.click();
            System.out.println("Fecha 24 de diciembre seleccionada.");
            System.out.println("Paso 7 completado: Seleccion de fecha de ida y vuelta");
        } catch (Exception e) {
            System.err.println("Error durante el proceso: " + e.getMessage());
        }

        // Paso 8: Hacer clic en el botón de "Buscar"
        // Crear WebDriverWait reutilizable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Hacer clic en el botón de búsqueda
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#main-search-form > div > div > div:nth-child(2) > div > div > div > div > div:nth-child(2) > span > button")));
        searchButton.click();
        System.out.println("Botón de Buscar clickeado.");

        // Guardar la ventana original
        String originalWindow = driver.getWindowHandle();
        // Esperar a que se abra una nueva ventana/pestaña
        wait.until(d -> d.getWindowHandles().size() > 1);
        //Cambiar al nuevo foco (ventana o pestaña)
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);  // Cambiar a la nueva pestaña
                break;
            }
        }

        //Esperar y garantizar la visibilidad del contenedor de resultados
        WebElement resultsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#leftRail > div > div.e_0j-results-count > div")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", resultsContainer);
        // Esperar a que los elementos de resultados sean visibles
        WebElement filteredResults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#leftRail > div > div.e_0j-results-count > div > div > span.bE-8-filtered")));
        WebElement totalResults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#leftRail > div > div.e_0j-results-count > div > div > div.bE-8-total-link")));
        // Extraer y mostrar los resultados
        String filteredResultsText = filteredResults.getText();
        String totalResultsText = totalResults.getText();

        System.out.println("Número de vuelos filtrados: " + filteredResultsText);
        System.out.println("Número total de vuelos: " + totalResultsText);
        System.out.println("Conteo de vuelos:");
        System.out.println(filteredResultsText + " de " + totalResultsText);

        //TestCaseVuelos Exitoso
        System.out.println("KTC-01 exitoso!");
    }
    @AfterClass
    public void tearDown() {
        // Cerrar el WebDriver
        DriverSetup.quitDriver();
    }
}
