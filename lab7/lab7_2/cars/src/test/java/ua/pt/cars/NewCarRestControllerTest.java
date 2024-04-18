package ua.pt.cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ua.pt.cars.boundary.CarController;
import ua.pt.cars.data.Car;
import ua.pt.cars.service.CarManagerService;

import org.json.*;

import java.util.Arrays;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.*;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class NewCarRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void whenPostCar_thenCreateCar( ) throws Exception {
        Car bmwM5 = new Car("bmw", "M5");

        when( service.save(Mockito.any()) ).thenReturn( bmwM5);

        RestAssuredMockMvc.when().get("/api/cars").then().statusCode(200);
    }

    @Test
    void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car sq8 = new Car("Audi", "SQ8");
        Car q8etron = new Car("Audi", "Q8 e-tron");
        Car gtetron= new Car("Audi", "GT e-tron");

        List<Car> allCars = Arrays.asList(sq8, q8etron, gtetron);

        when( service.getAllCars()).thenReturn(allCars);

        RestAssuredMockMvc.when().get("/api/cars").then().contentType("application/json").body("$", hasSize(3))
                                              .body("[0].maker", equalTo(sq8.getMaker()))
                                              .body("[0].model", equalTo(sq8.getModel()))
                                              .body("[1].maker", equalTo(q8etron.getMaker()))
                                              .body("[1].model", equalTo(q8etron.getModel()))
                                              .body("[2].maker", equalTo(gtetron.getMaker()))
                                              .body("[2].model", equalTo(gtetron.getModel())).statusCode(200);
    }

    @Test
    void whenGetCarById_thenReturnCar() throws Exception {
        Car bmwM5 =  new Car("bmw", "M5");

        when(service.getCarDetails(Mockito.any())).thenReturn(java.util.Optional.of(bmwM5));

        RestAssuredMockMvc.when().get("/api/cars/1").then().body("maker", equalTo(bmwM5.getMaker()))
                                                           .body("model", equalTo(bmwM5.getModel()));
 
        verify(service, times(1)).getCarDetails(Mockito.any());
    }

}