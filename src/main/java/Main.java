package main.java;
import main.java.driver.DriverSetup;
import test.java.tests.VuelosdirectosTest;
import test.java.tests.VuelosTest;
import test.java.tests.AlojamientosTest;
import main.java.pages.HomePage;
import org.openqa.selenium.WebDriver;

import test.java.tests.VuelosdirectosTest;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private WebDriver driver;
    private HomePage homePage;

    static VuelosdirectosTest vuelosdirectosTest = new VuelosdirectosTest();
    static VuelosTest vuelosTest = new VuelosTest();

    public static void main(String[] args) {
        // Instanciar las clases de prueba
        VuelosTest vuelosTest = new VuelosTest();
        VuelosdirectosTest vuelosdirectosTest = new VuelosdirectosTest();

        // Ejecutar las pruebas para vuelosTest
        try {
            System.out.println("Iniciando setUp para VuelosTest...");
            vuelosTest.setUp(); // Inicializa el driver y la configuración necesaria
            vuelosTest.testBuscarVuelos(); // Ejecuta el test principal
        } catch (Exception e) {
            System.out.println("Error en VuelosTest: " + e.getMessage());
        } finally {
            vuelosTest.tearDown(); // Cierra el driver y libera recursos
        }

        // Ejecutar las pruebas para vuelosDirectosTest
        try {
            System.out.println("Iniciando setUp para VuelosDirectosTest...");
            vuelosdirectosTest.setUp(); // Inicializa el driver y la configuración necesaria
            vuelosdirectosTest.testBuscarVuelosDirectos(); // Ejecuta el test principal
        } catch (Exception e) {
            System.out.println("Error en VuelosDirectosTest: " + e.getMessage());
        } finally {
            vuelosdirectosTest.tearDown(); // Cierra el driver y libera recursos
        }
    }

}