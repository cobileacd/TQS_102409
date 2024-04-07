package pt.ua.tqs;

import io.cucumber.java.After;

import org.junit.jupiter.api.AfterEach;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BlazeDemoSteps {

    private WebDriver driver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @And("I select {string} on the {string} input")
    public void iSelectFromPort(String fromCity, String fromPort) {
        WebElement element = driver.findElement(By.name(fromPort));
        element.sendKeys(fromCity);
    }

    @And("I click {string}")
    public void iClickButton(String button) {
        WebElement element = driver.findElement(By.xpath("//input[@type='submit' and @value='" + button + "']"));
        element.click();
    }

    @When("I click \"Choose This Flight\" on flight {int}")
    public void iClickChooseFlight(Integer flightN) {
        List<WebElement> rows = driver.findElements(By.xpath("//tr[.//input[@type='submit']]"));

        WebElement element = null;
        for (WebElement row : rows) {
            WebElement flightNumber = row.findElement(By.xpath(".//td[2]"));
            if (Integer.parseInt(flightNumber.getText()) == flightN) {
                element = row.findElement(By.xpath(".//input[@type='submit']"));
                break;
            }
        }
        if (element == null) {
            throw new RuntimeException("Flight not found");
        }

        element.click();
    }

    @Then("I should be redirected to a page with the title {string}")
    public void CheckPageTitle(String title) {
        if (!driver.getTitle().equals(title)) {
            throw new RuntimeException("Title doesn't match");
        }
    }
}