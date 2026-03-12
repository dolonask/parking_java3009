package model;

import model.enums.EntryStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Entry {
    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer carId;
    private EntryStatus status;
    private double cost;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public EntryStatus getStatus() {
        return status;
    }

    public void setStatus(EntryStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", carId=" + carId +
                ", status=" + status +
                ", cost=" + cost +
                '}';
    }
}
