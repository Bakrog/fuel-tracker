package com.fueltracker.driver.consumption.report;

import java.math.BigDecimal;
import java.util.Objects;

public class MoneySpendDTO {

    private String month;
    private BigDecimal totalSpent;

    public MoneySpendDTO() {
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneySpendDTO that = (MoneySpendDTO) o;
        return Objects.equals(month, that.month) &&
                Objects.equals(totalSpent, that.totalSpent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, totalSpent);
    }

    @Override
    public String toString() {
        return "MoneySpendDTO{" +
                "month='" + month + '\'' +
                ", totalSpent=" + totalSpent +
                '}';
    }
}
