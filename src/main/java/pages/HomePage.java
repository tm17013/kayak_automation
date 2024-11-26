package main.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchKayak() {
        try {
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("kayak");
        searchBox.submit();
        System.out.println("Búsqueda de Kayak completada.");
        } catch (Exception e) {
            System.out.println("Error durante la búsqueda: " + e.getMessage());
        }
    }

    public void selectKayakLink() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("https://www.kayak.com.sv/")));
            driver.findElement(By.linkText("https://www.kayak.com.sv/")).click();
            System.out.println("Enlace de Kayak seleccionado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al seleccionar el enlace de Kayak: " + e.getMessage());
        }
    }
}

