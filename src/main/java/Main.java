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
        vuelosTest.setUp();
        try {
            vuelosTest.testBuscarVuelos();
        } catch (Exception e){
            System.out.println(e);

            System.out.println("Se omitio este metodo");
            vuelosdirectosTest.setUp();
        }

        vuelosdirectosTest.testBuscarVuelosDirectos();
        vuelosdirectosTest.tearDown();


    }
}