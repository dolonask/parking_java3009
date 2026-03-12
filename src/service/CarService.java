package service;

import model.Car;
import service.impl.CarServiceImpl;

public interface CarService {

    CarService INSTANCE = new CarServiceImpl();

    Car findCarOrSaveIfNotExists(String carNumber);
    boolean existsByCarNumber(String carNumber);


}
