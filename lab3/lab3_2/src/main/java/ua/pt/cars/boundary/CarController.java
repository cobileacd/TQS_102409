package ua.pt.cars.boundary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.pt.cars.data.Car;
import ua.pt.cars.exception.ResourceNotFoundException;
import ua.pt.cars.service.CarManagerService;

import java.util.List;

/**
 * API endpoints. Try with Postman or curl
 * $curl -v http://localhost:8080/api/cars
 */
@RestController
@RequestMapping("/api")
public class CarController {

    private final CarManagerService carService;

    /**
     * Using constructor Injection instead of @autowired
     * when using a constructor to set injected properties, you do not have to provide the autowire annotation
     * @param carService
     */
    public CarController(CarManagerService carService) {
        this.carService = carService;
    }

    @PostMapping("/cars" )
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        HttpStatus status = HttpStatus.CREATED;
        Car saved = carService.save( car );
        return new ResponseEntity<>(saved, status);
    }


    @GetMapping(path="/cars" )
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId) throws ResourceNotFoundException {
        Car car = carService.getCarDetails(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found for id: " + carId));
        return ResponseEntity.ok().body(car);
    }

}
