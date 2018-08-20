package com.fueltracker.driver.consumption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * {@inheritDoc}
 */
@Transactional
@Service
public class FuelConsumptionServiceImpl implements FuelConsumptionService {

    private final FuelConsumptionRepository repository;
    private final FuelTypeService fuelTypeService;

    @Autowired
    public FuelConsumptionServiceImpl(FuelConsumptionRepository repository, FuelTypeService fuelTypeService) {
        this.repository = repository;
        this.fuelTypeService = fuelTypeService;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.NEVER)
    @Override
    public boolean validateFuelConsumptionEntry(FuelConsumptionDTO fuelConsumptionDTO) {
        final boolean hasFuelType = fuelConsumptionDTO.getFuelType() != null && fuelConsumptionDTO.getFuelType().length() > 0;
        final boolean hasPricePerLiter = fuelConsumptionDTO.getPricePerLiter() != null;
        final boolean hasVolumeInLiters = fuelConsumptionDTO.getVolumeInLiters() != null;
        final boolean hasDate = fuelConsumptionDTO.getDate() != null;
        final boolean hasDriverId = fuelConsumptionDTO.getDriverId() != null;
        return hasFuelType && hasPricePerLiter && hasVolumeInLiters && hasDate && hasDriverId;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.NEVER)
    @Override
    public Flux<FuelConsumption> findAll() {
        return Flux.fromIterable(repository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Mono<FuelConsumption> save(FuelConsumptionDTO fuelConsumptionDTO) throws IllegalArgumentException {
        if (validateFuelConsumptionEntry(fuelConsumptionDTO)) {
            return fuelTypeService.save(fuelConsumptionDTO.getFuelType()).flatMap(fuelType -> Mono.just(repository.save(this.convertDTO(fuelConsumptionDTO, fuelType))));
        }
        throw new IllegalArgumentException("All arguments are required!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FuelConsumption convertDTO(FuelConsumptionDTO fuelConsumptionDTO, FuelType fuelType) {
        final FuelConsumption converted = new FuelConsumption(fuelType.getId(),
                fuelConsumptionDTO.getPricePerLiter(),
                fuelConsumptionDTO.getVolumeInLiters(),
                fuelConsumptionDTO.getDate(),
                fuelConsumptionDTO.getDriverId());
        converted.setFuelType(fuelType);
        return converted;
    }
}
