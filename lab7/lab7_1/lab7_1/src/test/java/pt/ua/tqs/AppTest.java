package pt.ua.tqs;

import static org.junit.Assert.assertTrue;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class AppTest 
{
    @Test
    public void isListAllTodosAvailable()
    {
        get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200);
    }

    @Test
    public void isTodo4ReturingExpected() 
    {
        get("https://jsonplaceholder.typicode.com/todos/4").then().body("title", equalTo("et porro tempora"));
    }

    @Test
    public void whenListingAllTodosDoesItReturnCorrectIds() 
    {
        get("https://jsonplaceholder.typicode.com/todos").then().body("id", hasItems(198, 199));
    }

    @Test
    public void isResponseTimeFromAllTodosLessThan2Seconds()
    {
        when().get("https://jsonplaceholder.typicode.com/todos").then().time(lessThan(2000L)); // Milliseconds
    }
}
