package com.fueltracker.driver.consumption;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * DTO to receive and send information about fuel consumption
 */
public class FuelConsumptionDTO {

    private String fuelType;
    private LocalDateTime date;
    private BigDecimal pricePerLiter;
    private BigDecimal volumeInLiters;
    private Long driverId;

    public FuelConsumptionDTO() {
    }

    public FuelConsumptionDTO(String fuelType, LocalDateTime date, BigDecimal pricePerLiter, BigDecimal volumeInLiters, Long driverId) {
        this.fuelType = fuelType;
        this.date = date;
        this.pricePerLiter = pricePerLiter;
        this.volumeInLiters = volumeInLiters;
        this.driverId = driverId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getPricePerLiter() {
        return pricePerLiter;
    }

    public void setPricePerLiter(BigDecimal pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    public BigDecimal getVolumeInLiters() {
        return volumeInLiters;
    }

    public void setVolumeInLiters(BigDecimal volumeInLiters) {
        this.volumeInLiters = volumeInLiters;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelConsumptionDTO that = (FuelConsumptionDTO) o;
        return Objects.equals(fuelType, that.fuelType) &&
                Objects.equals(date, that.date) &&
                Objects.equals(pricePerLiter, that.pricePerLiter) &&
                Objects.equals(volumeInLiters, that.volumeInLiters) &&
                Objects.equals(driverId, that.driverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelType, date, pricePerLiter, volumeInLiters, driverId);
    }

    @Override
    public String toString() {
        return "FuelConsumptionDTO{" +
                "fuelType='" + fuelType + '\'' +
                ", date=" + date +
                ", pricePerLiter=" + pricePerLiter +
                ", volumeInLiters=" + volumeInLiters +
                ", driverId=" + driverId +
                '}';
    }
}
