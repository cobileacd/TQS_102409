package ua.pt.cars;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ua.pt.cars.data.Car;
import ua.pt.cars.data.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DataJpaTest limits the test scope to the data access context (no web environment loaded, for example)
 * tries to autoconfigure the database, if possible (e.g.: in memory db)
 */
@DataJpaTest
class A_EmployeeRepositoryTest {

    // get a test-friendly Entity Manager
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void whenFindEmployedByExistingId_thenReturnEmployee() {
        Car bmw = new Car("BMW", "M5");
        entityManager.persistAndFlush(bmw);

        Car fromDb = carRepository.findByCarId(bmw.getCarId());
        assertThat(fromDb).isNotNull();
        assertThat(fromDb).isEqualTo(bmw);
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Car fromDb = carRepository.findByCarId(-111L);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car sq8 = new Car("Audi", "SQ8");
        Car q8etron = new Car("Audi", "Q8 e-tron");
        Car gtetron= new Car("Audi", "GT e-tron");

        entityManager.persist(sq8);
        entityManager.persist(q8etron);
        entityManager.persist(gtetron);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getMaker).containsOnly(sq8.getMaker(), q8etron.getMaker(), gtetron.getMaker());
    }
}