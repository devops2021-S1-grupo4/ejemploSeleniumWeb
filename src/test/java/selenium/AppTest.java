package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.Select;

import org.junit.Test;
import org.junit.Before;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App
 */
public class AppTest 
{
    
    private WebDriver driver;

    @Before
    public void setUp(){
        System.out.println("TAREA 2 - Modulo 4 - Seccion 1");
        System.out.println("version chrome 99.0.4844.51 ");
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        driver = new ChromeDriver();
        
        

    }

    @Test
    public void shouldAnswerWithTrue()
    {
        System.out.println("version chrome 99.0.4844.51 ");
        driver.get("https://www.google.com");
        
        driver.manage().window().maximize();
        System.out.println("estamos en la pagina :"+driver.getCurrentUrl());
        System.out.println("El titulo de la pagina es: " + driver.getTitle());
        System.out.println("Buscando libro DevOps");
        WebElement inputBusqueda = driver.findElement(By.name("q"));
        inputBusqueda.sendKeys("HandBook Devops");
        inputBusqueda.submit();
        System.out.println("espero 4 segundos");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        System.out.println("Hago CLIC en imagenes");
 
        driver.findElement(By.xpath("/html/body/div[7]/div/div[4]/div/div[1]/div/div[1]/div/div[2]/a")).click();
        
        System.out.println("espero 4 segundos");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[2]/c-wiz/div[1]/div/div[1]/div[1]/div/div/a[4]")).click();
        System.out.println("espero 4 segundos");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.amazon.com");
       
        System.out.println("estamos en la pagina :"+driver.getCurrentUrl());
        System.out.println("El titulo de la pagina es: " + driver.getTitle());

        System.out.println("Buscando libro The Phoenix Project en Amazon");
        WebElement inputBusquedaAmazon = driver.findElement(By.name("field-keywords"));
        inputBusquedaAmazon.sendKeys("The Phoenix Project");
        inputBusquedaAmazon.submit();



        //driver.close();
    }
}
