// Generated by Selenium IDE
package pt.ua.BusTicket.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;

import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class ViewControllerTest {

    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    /*
    @BeforeAll
    static void setupClass() {
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
    }

    @BeforeEach
    public void setUp() {
      driver = new FirefoxDriver();
      js = (JavascriptExecutor) driver;
      vars = new HashMap<String, Object>();
    }

    @AfterEach
    public void tearDown() {
      driver.quit();
    }
    */
    @Test
    public void test(ChromeDriver driver) throws Exception {
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1796, 1080));
    driver.findElement(By.name("fromPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Chicago']")).click();
    }
    driver.findElement(By.cssSelector(".mt-5")).click();
    //driver.findElement(By.name("toPort")).click();
    {
      WebElement selectElement = driver.findElement(By.xpath("//select[@name='toPort']"));
      Select select = new Select(selectElement);

      select.selectByVisibleText("Miami");
    }
    driver.findElement(By.cssSelector(".mt-5")).click();

    WebElement date = driver.findElement(By.name("tripDate"));
    //date.clear();
    js = (JavascriptExecutor) driver;
    js.executeScript("document.getElementById('date').value = '2024-04-15'");
    //date.sendKeys("15-04-2024");

    driver.findElement(By.name("currency")).click();
    {
      WebElement dropdown = driver.findElement(By.name("currency"));
      dropdown.findElement(By.xpath("//option[. = 'EUR']")).click();
    }
    driver.findElement(By.cssSelector(".btn")).click();
    driver.findElement(By.linkText("Provide Information")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).sendKeys("asdf");
    driver.findElement(By.id("address")).click();
    driver.findElement(By.id("address")).sendKeys("asdf");
    driver.findElement(By.id("city")).click();
    driver.findElement(By.id("city")).sendKeys("asdf");
    driver.findElement(By.id("state")).click();
    driver.findElement(By.id("state")).sendKeys("asdf");
    driver.findElement(By.id("zipCode")).click();
    driver.findElement(By.id("zipCode")).sendKeys("asdf");
    driver.findElement(By.id("cardType")).click();
    driver.findElement(By.id("cardType")).sendKeys("asdf");
    driver.findElement(By.id("cardNumber")).click();
    driver.findElement(By.id("cardNumber")).sendKeys("asdf");
    driver.findElement(By.id("month")).click();
    driver.findElement(By.id("month")).sendKeys("asdf");
    driver.findElement(By.id("year")).click();
    driver.findElement(By.id("year")).sendKeys("asdf");
    driver.findElement(By.id("nameOnCard")).click();
    driver.findElement(By.id("nameOnCard")).sendKeys("asdf");
    driver.findElement(By.cssSelector(".btn")).click();
    assertThat(driver.findElement(By.cssSelector(".mt-5")).getText()).contains("Thank you for trusting us!");
  }

/*
    @Test
    public void test(ChromeDriver driver) throws Exception {

    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1796, 1080));
    driver.findElement(By.name("fromPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Chicago']")).click();
    }
    driver.findElement(By.name("toPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'Miami']")).click();
    }
    driver.findElement(By.name("tripDate")).click();
    driver.findElement(By.name("tripDate")).sendKeys("2024-04-15");
    driver.findElement(By.name("currency")).click();
    driver.findElement(By.cssSelector("html")).click();
    driver.findElement(By.name("currency")).click();
    {
      WebElement dropdown = driver.findElement(By.name("currency"));
      dropdown.findElement(By.xpath("//option[. = 'EUR']")).click();
    }
    Thread.sleep(5000);
    driver.findElement(By.cssSelector(".btn")).click();
    driver.findElement(By.linkText("Provide Information")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).sendKeys("asdf");
    driver.findElement(By.id("address")).click();
    driver.findElement(By.id("address")).sendKeys("asdf");
    driver.findElement(By.id("city")).click();
    driver.findElement(By.id("city")).sendKeys("asdf");
    driver.findElement(By.id("state")).click();
    driver.findElement(By.id("state")).sendKeys("asdf");
    driver.findElement(By.id("zipCode")).click();
    driver.findElement(By.id("zipCode")).sendKeys("asdf");
    driver.findElement(By.id("cardType")).click();
    driver.findElement(By.id("cardType")).sendKeys("asdf");
    driver.findElement(By.id("cardNumber")).click();
    driver.findElement(By.id("cardNumber")).sendKeys("asdf");
    driver.findElement(By.cssSelector(".form-group:nth-child(9)")).click();
    driver.findElement(By.id("month")).click();
    driver.findElement(By.id("month")).sendKeys("asdf");
    driver.findElement(By.id("year")).click();
    driver.findElement(By.id("year")).sendKeys("asdf");
    driver.findElement(By.id("nameOnCard")).click();
    driver.findElement(By.id("nameOnCard")).sendKeys("asdf");
    driver.findElement(By.cssSelector(".btn")).click();
        assertThat(driver.findElement(By.cssSelector(".mt-5")).getText()).contains("Thank you for trusting us!");
  }
  */
}
