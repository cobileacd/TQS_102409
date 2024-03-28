package ua.pt.cars.service;

import org.springframework.stereotype.Service;
import ua.pt.cars.data.CarRepository;
import ua.pt.cars.data.Car;

import java.util.List;
import java.util.Optional;

@Service
public class CarManagerService {
    final CarRepository carRepo;

    public CarManagerService(CarRepository carRepo) {
        this.carRepo = carRepo;
    }

    public Car save(Car oneCar) {
        return carRepo.save(oneCar);
    }

    public List<Car> getAllCars() {

        return carRepo.findAll();
    }

    public Optional<Car> getCarDetails(Long carId) {
        return Optional.of(carRepo.findByCarId(carId) );
    }
}
