package ua.pt.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.pt.cars.data.Car;
import ua.pt.cars.data.CarRepository;
import ua.pt.cars.service.CarManagerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test scenario: verify the logic of the Service, mocking the response of the datasource
 * Results in standard unit test with mocks
 */
@ExtendWith(MockitoExtension.class)
class CarServiceUnitTest {

    // mocking the responses of the repository (i.e., no database will be used)
    // lenient is required because we load more expectations in the setup
    // than those used in some tests. As an alternative, the expectations
    // could move into each test method and be trimmed (no need for lenient then)
    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    @BeforeEach
    public void setUp() {
        //these expectations provide an alternative to the use of the repository
        Car bmw = new Car("BMW", "M6");
        bmw.setId(1L);
        Car mazda = new Car("Mazda", "RX7");
        mazda.setId(2L);
        Car porsche = new Car("Porsche", "911 GT3 RS");
        porsche.setId(3L);

        List<Car> allCars = Arrays.asList(bmw, mazda, porsche);

        Mockito.when(carRepository.findByCarId(bmw.getCarId())).thenReturn(bmw);
        Mockito.when(carRepository.findByCarId(mazda.getCarId())).thenReturn(mazda);
        Mockito.when(carRepository.findByCarId(porsche.getCarId())).thenReturn(porsche);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
    }

    @Test
     void whenGetAll_thenReturnAll() {
        Car bmw = new Car("BMW", "M6");
        Car mazda = new Car("Mazda", "RX7");
        Car porsche = new Car("Porsche", "911 GT3 RS");

        List<Car> allCars = carService.getAllCars();
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
        assertThat(allCars).hasSize(3).extracting(Car::getModel).contains(bmw.getModel(), mazda.getModel(), porsche.getModel());
    }

    @Test
     void whenSearchByValidId_thenCarShouldBeFound() {
        Car bmw = new Car("BMW", "M6");
        bmw.setId(1L);

        assertThat(carService.getCarDetails(1L).get()).isEqualTo(bmw);

        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(1L);
     }
}
