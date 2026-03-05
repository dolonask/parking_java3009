package service.impl;

import dao.EntryDao;
import model.Car;
import model.Entry;
import model.enums.EntryStatus;
import service.CarService;
import service.EntryService;

import java.time.LocalDateTime;

public class EntryServiceImpl implements EntryService {

    private CarService carService = CarService.INSTANCE;
    private EntryDao entryDao = EntryDao.INSTANCE;

    @Override
    public void in(String carNumber) {
        Car car = carService.findCarOrSaveIfNotExists(carNumber);

        if (entryDao.isCarInside(car.getId())){
            System.out.println("Ошибка: автомобиль уже находится на парковке.");
            return;
        }

        Entry entry = new Entry();
        entry.setCarId(car.getId());
        entry.setStartDate(LocalDateTime.now());
        entry.setStatus(EntryStatus.IN);
        entryDao.saveEntry(entry);
        System.out.println("Автомобиль "+ carNumber +" заехал на парковку.");

    }

    @Override
    public void out(String carNumber) {

    }
}
