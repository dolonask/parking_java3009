package service.impl;

import dao.EntryDao;
import model.Car;
import model.Entry;
import model.enums.EntryStatus;
import service.CarService;
import service.EntryService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        if (!carService.existsByCarNumber(carNumber)) {
            System.out.println("Ошибка: автомобиль не найден на парковке");
            return;
        }

        Car car = carService.findCarOrSaveIfNotExists(carNumber);
        Entry entry = entryDao.findByCarIdAndStatus(car.getId(), EntryStatus.IN);

        entry.setEndDate(LocalDateTime.now());

        Duration duration =  Duration.between(entry.getStartDate(), entry.getEndDate());
        double cost = getCostByDuration(duration.toMinutes());

        entry.setStatus(EntryStatus.OUT);
        entry.setCost(cost);

        entryDao.setEntry(entry);
        printEntry(car, entry, duration);

    }

    private void printEntry(Car car, Entry entry, Duration duration) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        System.out.println("Автомобиль: " + car.getCarNumber());
        System.out.println("Время въезда: " + dateTimeFormatter.format(entry.getStartDate()));
        System.out.println("Время выезда: " + dateTimeFormatter.format(entry.getEndDate()));
        System.out.println("Время на парковке: " + duration.toHours() + " час " + (duration.toMinutes() - (duration.toHours() * 60)) + " минут") ;
        System.out.println("К оплате: " + entry.getCost());
    }

    private double getCostByDuration(long minutes) {
        double cost;
        if (minutes < 15)
            cost = 0;
        else if (minutes <= 60)
            cost = 50;
        else {
            cost = 50;
            minutes -= 60;
            double hours = Math.ceil(minutes / 60.0);
            cost = cost + (hours * 10);
        }
        return cost;
    }
}
