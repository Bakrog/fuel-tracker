package com.fueltracker.driver.consumption.report;

import java.math.BigDecimal;
import java.util.Objects;

public class FuelConsumptionByFuelTypeDTO {

    private String fuelType;
    private BigDecimal volume;
    private BigDecimal averagePrice;
    private BigDecimal totalPrice;

    public FuelConsumptionByFuelTypeDTO() {
    }

    public FuelConsumptionByFuelTypeDTO(String fuelType, BigDecimal volume, double averagePrice, BigDecimal totalPrice) {
        this.fuelType = fuelType;
        this.volume = volume;
        this.averagePrice = new BigDecimal(averagePrice);
        this.totalPrice = totalPrice;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelConsumptionByFuelTypeDTO that = (FuelConsumptionByFuelTypeDTO) o;
        return Objects.equals(fuelType, that.fuelType) &&
                Objects.equals(volume, that.volume) &&
                Objects.equals(averagePrice, that.averagePrice) &&
                Objects.equals(totalPrice, that.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelType, volume, averagePrice, totalPrice);
    }

    @Override
    public String toString() {
        return "FuelConsumptionByFuelTypeDTO{" +
                "fuelType='" + fuelType + '\'' +
                ", volume=" + volume +
                ", averagePrice=" + averagePrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
