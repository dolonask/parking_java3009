package dao;

import dao.impl.CarDaoImpl;
import model.Car;

public interface CarDao {

    CarDao INSTANCE = new CarDaoImpl();

    Car findByCarNumber(String carNumber);
    Car saveCar(String carNumber);

}
