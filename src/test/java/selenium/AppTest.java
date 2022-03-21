package selenium;

import static org.junit.Assert.assertEquals;

import net.bytebuddy.utility.RandomString;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	private WebDriver driver;
	String randomEmail;

    @Before
    public void setUp(){
        System.out.println("Iniciando configuración...");
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();

        // GENERACION DE CORREO RANDOM
        // PARA PUNTO 02. Sign in -> CREATE AN ACCOUNT
        System.out.println("GENERACION DE CORREO RANDOM");
		randomEmail = String.format("%s@correo.com", RandomString.make(15)).toLowerCase();
		System.out.println("Random email : " + randomEmail);
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        WebElement productContainer, btnAddToCart, alias;
        String name, address_company, address, address2, address_country_name, address_phone_mobile;

        // Seleccionar el producto
        System.out.println("Seleccionar el producto");
		productContainer = driver.findElement(By.cssSelector(".ajax_block_product"));
		Actions actions = new Actions(driver);
		actions.moveToElement(productContainer).perform();
		btnAddToCart = productContainer.findElement(By.xpath(".//span[text()='Add to cart']"));
		btnAddToCart.click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Proceed to checkout']"))).click();

        // 01. summary
        System.out.println("01. summary");
		driver.findElement(By.xpath("//a[contains(@href, 'step=1')]")).click();

        // 02. Sign in -> CREATE AN ACCOUNT
        System.out.println("02. Sign in -> CREATE AN ACCOUNT");
		driver.findElement(By.id("email_create")).sendKeys(randomEmail);
		driver.findElement(By.id("SubmitCreate")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("id_gender1"))).click();

        // 02. Sign in -> CREATE AN ACCOUNT -> YOUR PERSONAL INFORMATION
        System.out.println("02. Sign in -> CREATE AN ACCOUNT -> YOUR PERSONAL INFORMATION");
		driver.findElement(By.id("customer_firstname")).sendKeys("Grupo");
		driver.findElement(By.id("customer_lastname")).sendKeys("Cuatro");
		String email = driver.findElement(By.id("email")).getAttribute("value");
		assertEquals(randomEmail, email);
		driver.findElement(By.id("passwd")).sendKeys("password");
		new Select(driver.findElement(By.id("days"))).selectByValue("1");
		new Select(driver.findElement(By.id("months"))).selectByValue("1");
		new Select(driver.findElement(By.id("years"))).selectByValue("2001");
		driver.findElement(By.id("firstname")).clear();
		driver.findElement(By.id("firstname")).sendKeys("Grupo");
		driver.findElement(By.id("lastname")).clear();
		driver.findElement(By.id("lastname")).sendKeys("Cuatro");
		driver.findElement(By.id("company")).sendKeys("Grupo cuatro ltda.");
		driver.findElement(By.id("address1")).sendKeys("Direccion 111");
		driver.findElement(By.id("address2")).sendKeys("Departamento 111");
		driver.findElement(By.id("city")).sendKeys("Santiago");
		Select cboState = new Select(driver.findElement(By.id("id_state")));
		cboState.selectByValue("2");
		driver.findElement(By.id("postcode")).sendKeys("11111");
		driver.findElement(By.id("other")).sendKeys("this is a test");
		driver.findElement(By.id("phone_mobile")).sendKeys("912345678");
		alias = driver.findElement(By.id("alias"));
		alias.clear();
		alias.sendKeys("Mi dirección");
		driver.findElement(By.id("submitAccount")).click();

        // VALICACION DE DATOS DE DIRECCION
        System.out.println("VALICACION DE DATOS DE DIRECCION");
		name = driver.findElement(By.xpath("//li[@class='address_firstname address_lastname']")).getText();
		address_company = driver.findElement(By.xpath("//li[@class='address_company']")).getText();
		address = driver.findElement(By.xpath("//li[@class='address_address1 address_address2']")).getText();
		address2 = driver.findElement(By.xpath("//li[@class='address_city address_state_name address_postcode']")).getText();
		address_country_name = driver.findElement(By.xpath("//li[@class='address_country_name']")).getText();
		address_phone_mobile = driver.findElement(By.xpath("//li[@class='address_phone_mobile']")).getText();
		assertEquals("Grupo Cuatro", name);
		assertEquals("Grupo cuatro ltda.", address_company);
		assertEquals("Direccion 111 Departamento 111", address);
		assertEquals("Santiago, Alaska 11111", address2);
		assertEquals("United States", address_country_name);
		assertEquals("912345678", address_phone_mobile);

         // 03. Address -> Proceed to checkout
         System.out.println("03. Address -> Proceed to checkout");
		driver.findElement(By.name("processAddress")).click();

        // 04. Shipping -> I agree to the terms
        System.out.println("04. Shipping -> I agree to the terms");
		driver.findElement(By.id("cgv")).click();

        // 04. Shipping -> Proceed to checkout
        System.out.println("04. Shipping -> Proceed to checkout");
		driver.findElement(By.name("processCarrier")).click();

        // 05. Payment -> Pay by bank wire
        System.out.println("05. Payment -> Pay by bank wire");
		driver.findElement(By.cssSelector(".bankwire")).click();

        // 05. Payment -> I confirm my order
        System.out.println("05. Payment -> I confirm my order");
		driver.findElement(By.xpath("//span[contains(text(), 'I confirm my order')]")).click();
    }
}
