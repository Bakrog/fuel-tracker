package com.fueltracker.driver.consumption;

import javax.persistence.*;

import java.util.Objects;

import static org.springframework.util.Assert.notNull;

@Entity
@Table(name = "tb_fuel_type")
public class FuelType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fuel_type_id")
    private Long id;
    @Column(name = "name")
    private String name;

    public FuelType() {
    }

    public FuelType(String name) {
        notNull(name, "The fuel type attribute 'name' can't be null!");
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelType fuelType = (FuelType) o;
        return Objects.equals(name, fuelType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "FuelType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
