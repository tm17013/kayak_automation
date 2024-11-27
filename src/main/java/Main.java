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

    public static void main(String[] args) {
        // Instancias de las clases de prueba
        VuelosTest vuelosTest = new VuelosTest();
        AlojamientosTest alojamientosTest = new AlojamientosTest();
        VuelosdirectosTest vuelosdirectosTest = new VuelosdirectosTest();
        //Pruebas Consecutivas
        // Ejecuta las prueba VuelosTest
        try {
            System.out.println("Iniciando setUp para VuelosTest...");
            vuelosTest.setUp(); // Inicializa el driver y la configuración necesaria
            vuelosTest.testBuscarVuelos(); // Ejecuta el test principal
        } catch (Exception e) {
            System.out.println("Error en VuelosTest: " + e.getMessage());
        }

//        // Ejecuta las prueba AlojamientosTest
        try {
            System.out.println("Iniciando setUp para AlojamientosTest...");
            alojamientosTest.setUp(); // Inicializa el entorno para AlojamientosTest
            alojamientosTest.testBuscarAlojamientos(); // Ejecuta la prueba
        } catch (Exception e) {
            System.out.println("Error en AlojamientosTest: " + e.getMessage());
        }

         //Ejecuta las pruebas para VuelosDirectosTest
        try {
            System.out.println("Iniciando setUp para VuelosDirectosTest...");
            vuelosdirectosTest.setUp(); // Inicializa el driver y la configuración necesaria
            vuelosdirectosTest.testBuscarVuelosDirectos(); // Ejecuta el test principal
        } catch (Exception e) {
            System.out.println("Error en VuelosDirectosTest: " + e.getMessage());
        }

        // Se cierra el WebDriver después de todas las pruebas
        DriverSetup.quitDriver();
        System.out.println("Driver cerrado correctamente.");
    }

}