package com.fueltracker.driver.consumption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * {@inheritDoc}
 */
@Transactional
@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    private final FuelTypeRepository repository;

    @Autowired
    public FuelTypeServiceImpl(final FuelTypeRepository repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Mono<FuelType> save(final String name) {
        Optional<FuelType> optionalFuelType = repository.findByName(name);
        return optionalFuelType.isPresent() ? Mono.justOrEmpty( optionalFuelType ) : this.save( new FuelType(name) );
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Mono<FuelType> save(FuelType fuelType) {
        return Mono.just( repository.save(fuelType) );
    }
}
