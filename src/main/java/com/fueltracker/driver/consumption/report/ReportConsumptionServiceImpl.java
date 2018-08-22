package com.fueltracker.driver.consumption.report;

import com.fueltracker.driver.consumption.FuelConsumptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

/**
 * {@inheritDoc}
 */
@Transactional(propagation = Propagation.NEVER, readOnly = true)
@Service
public class ReportConsumptionServiceImpl implements ReportConsumptionService {

    private final ReportConsumptionRepository repository;

    @Autowired
    public ReportConsumptionServiceImpl(ReportConsumptionRepository repository) {
        this.repository = repository;
    }

    private boolean isValidMonth(int month){
        return month > 0 && month < 13;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flux<MoneySpendDTO> searchMoneySpendByMonth() {
        return Flux.fromIterable( repository.searchMoneySpendByMonth() );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flux<MoneySpendDTO> searchMoneySpendByMonthAndDriverId(Long driverId) {
        return Flux.fromIterable( repository.searchMoneySpendByMonthAndDriverId( driverId ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flux<FuelConsumptionDTO> searchFuelConsumptionByMonth(int month) {
        if(isValidMonth(month)){
            return Flux.fromIterable( repository.searchFuelConsumptionByMonth( month ) );
        }
        throw new RuntimeException(String.format("The month %s provided isnt a valid month: 1 <= month <= 12", month));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flux<FuelConsumptionDTO> searchFuelConsumptionByMonthAndDriverId(int month, Long driverId) {
        if(driverId != null && isValidMonth(month)){
            return Flux.fromIterable( repository.searchFuelConsumptionByMonthAndDriverId( month, driverId ) );
        } else if (driverId == null){
            throw new RuntimeException("The driver id is required!");
        }
        throw new RuntimeException(String.format("The month %s provided isnt a valid month: 1 <= month <= 12", month));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flux<FuelConsumptionByFuelTypeDTO> searchFuelConsumptionByMonthGroupedByFuelType(int month) {
        if(isValidMonth(month)){
            return Flux.fromIterable( repository.searchFuelConsumptionByMonthGroupedByFuelType( month ) );
        }
        throw new RuntimeException(String.format("The month %s provided isnt a valid month: 1 <= month <= 12", month));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flux<FuelConsumptionByFuelTypeDTO> searchFuelConsumptionByMonthAndDriverIdGroupedByFuelType(int month, Long driverId) {
        if(driverId != null && isValidMonth(month)){
            return Flux.fromIterable( repository.searchFuelConsumptionByMonthAndDriverIdGroupedByFuelType( month, driverId ) );
        } else if (driverId == null){
            throw new RuntimeException("The driver id is required!");
        }
        throw new RuntimeException(String.format("The month %s provided isnt a valid month: 1 <= month <= 12", month));
    }
}
