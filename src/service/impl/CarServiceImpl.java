package service.impl;

import dao.CarDao;
import model.Car;
import service.CarService;

public class CarServiceImpl implements CarService {

    private CarDao carDao = CarDao.INSTANCE;

    @Override
    public Car findCarOrSaveIfNotExists(String carNumber) {

        Car car = carDao.findByCarNumber(carNumber);
        if (car == null){
            car = carDao.saveCar(carNumber);
        }

        System.out.println(car);
        return car;
        /*
        1. Найти машину в БД с указанным номером
        2. Если машины нет, то создать
         */

    }
}
