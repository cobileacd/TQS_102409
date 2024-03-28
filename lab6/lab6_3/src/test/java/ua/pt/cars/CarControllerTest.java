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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * WebMvcTest loads a simplified web environment for the tests. Note that the normal
 * auto-discovery of beans (and dependency injection) is limited
 * This strategy deploys the required components to a test-friendly web framework, that can be accessed
 * by injecting a MockMvc reference
 */
@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mvc;    //entry point to the web framework

    // inject required beans as "mockeable" objects
    // note that @AutoWire would result in NoSuchBeanDefinitionException
    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenPostCar_thenCreateCar( ) throws Exception {
        Car bmwM5 = new Car("bmw", "M5");

        when( service.save(Mockito.any()) ).thenReturn( bmwM5);

        mvc.perform(
                post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(bmwM5)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("bmw")))
                .andExpect(jsonPath("$.model", is("M5")));

        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car sq8 = new Car("Audi", "SQ8");
        Car q8etron = new Car("Audi", "Q8 e-tron");
        Car gtetron= new Car("Audi", "GT e-tron");

        List<Car> allCars = Arrays.asList(sq8, q8etron, gtetron);

        when( service.getAllCars()).thenReturn(allCars);

        mvc.perform(
                get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].maker", is(sq8.getMaker())))
                .andExpect(jsonPath("$[0].model", is(sq8.getModel())))
                .andExpect(jsonPath("$[1].maker", is(q8etron.getMaker())))
                .andExpect(jsonPath("$[1].model", is(q8etron.getModel())))
                .andExpect(jsonPath("$[2].maker", is(gtetron.getMaker())))
                .andExpect(jsonPath("$[2].model", is(gtetron.getModel())));

        verify(service, times(1)).getAllCars();
    }

    @Test
    void whenGetCarById_thenReturnCar() throws Exception {
        Car bmwM5 =  new Car("bmw", "M5");

        when(service.getCarDetails(Mockito.any())).thenReturn(java.util.Optional.of(bmwM5));

        mvc.perform(get("/api/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maker", is(bmwM5.getMaker())))
                .andExpect(jsonPath("$.model", is(bmwM5.getModel())));

        verify(service, times(1)).getCarDetails(Mockito.any());
    }

}