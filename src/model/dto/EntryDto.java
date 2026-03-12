package model.dto;

import java.time.LocalDateTime;

public class EntryDto {
    private Integer entryId;
    private LocalDateTime startDate;
    private String carNumber;

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public String toString() {
        return "EntryDto{" +
                "startDate=" + startDate +
                ", carNumber='" + carNumber + '\'' +
                '}';
    }
}
