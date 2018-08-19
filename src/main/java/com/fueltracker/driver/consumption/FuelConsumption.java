package com.fueltracker.driver.consumption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.springframework.util.Assert.notNull;

@Entity
@Table(name = "tb_consumption")
public class FuelConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "consumption_id")
    private Long id;
    @Column(name = "fuel_type_id")
    private Long fuelTypeId;
    @OneToOne
    @JoinColumn(name = "fuel_type_id", insertable = false, updatable = false)
    private FuelType fuelType;
    @Column(name = "price_per_liter")
    private BigDecimal pricePerLiter;
    @Column(name = "volume_in_liters")
    private BigDecimal volumeInLiters;
    @Column(name = "consumption_date")
    private LocalDateTime date;
    @Column(name = "driver_id")
    private Long driveId;

    public FuelConsumption() {
    }

    public FuelConsumption(Long fuelTypeId, BigDecimal pricePerLiter, BigDecimal volumeInLiters, LocalDateTime date, Long driveId) {
        notNull(fuelTypeId, "The fuel consumption attribute 'fuelTypeId' can't be null!");
        notNull(pricePerLiter, "The fuel consumption attribute 'pricePerLiter' can't be null!");
        notNull(volumeInLiters, "The fuel consumption attribute 'volumeInLiters' can't be null!");
        notNull(date, "The fuel consumption attribute 'date' can't be null!");
        notNull(driveId, "The fuel consumption attribute 'driveId' can't be null!");
        this.fuelTypeId = fuelTypeId;
        this.pricePerLiter = pricePerLiter;
        this.volumeInLiters = volumeInLiters;
        this.date = date;
        this.driveId = driveId;
    }

    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    Long getFuelTypeId() {
        return fuelTypeId;
    }

    void setFuelTypeId(Long fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    BigDecimal getPricePerLiter() {
        return pricePerLiter;
    }

    void setPricePerLiter(BigDecimal pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    BigDecimal getVolumeInLiters() {
        return volumeInLiters;
    }

    void setVolumeInLiters(BigDecimal volumeInLiters) {
        this.volumeInLiters = volumeInLiters;
    }

    LocalDateTime getDate() {
        return date;
    }

    void setDate(LocalDateTime date) {
        this.date = date;
    }

    Long getDriveId() {
        return driveId;
    }

    void setDriveId(Long driveId) {
        this.driveId = driveId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelConsumption that = (FuelConsumption) o;
        return Objects.equals(fuelTypeId, that.fuelTypeId) &&
                Objects.equals(pricePerLiter, that.pricePerLiter) &&
                Objects.equals(volumeInLiters, that.volumeInLiters) &&
                Objects.equals(date, that.date) &&
                Objects.equals(driveId, that.driveId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fuelTypeId, pricePerLiter, volumeInLiters, date, driveId);
    }

    @Override
    public String toString() {
        return "FuelConsumption{" +
                "id=" + id +
                ", fuelTypeId=" + fuelTypeId +
                ", fuelType=" + fuelType +
                ", pricePerLiter=" + pricePerLiter +
                ", volumeInLiters=" + volumeInLiters +
                ", date=" + date +
                ", driveId=" + driveId +
                '}';
    }
}
