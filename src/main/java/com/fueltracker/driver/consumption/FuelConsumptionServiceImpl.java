package com.fueltracker.driver.consumption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * {@inheritDoc}
 */
@Transactional
@Service
public class FuelConsumptionServiceImpl implements FuelConsumptionService {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

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
            return fuelTypeService.save(fuelConsumptionDTO.getFuelType()).flatMap(fuelType -> Mono.just(repository.save(this.convert(fuelConsumptionDTO, fuelType))));
        }
        throw new IllegalArgumentException("All arguments are required!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flux<FuelConsumption> saveBulk(Path filePath) throws IllegalArgumentException {
        try {
            return Flux.fromStream(Files.lines(filePath) )
                    .map(this::convertToDTO)
                    .flatMap(this::save);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file to bulk save", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FuelConsumption convert(FuelConsumptionDTO fuelConsumptionDTO, FuelType fuelType) {
        final FuelConsumption converted = new FuelConsumption(fuelType.getId(),
                fuelConsumptionDTO.getPricePerLiter(),
                fuelConsumptionDTO.getVolumeInLiters(),
                fuelConsumptionDTO.getDate(),
                fuelConsumptionDTO.getDriverId());
        converted.setFuelType(fuelType);
        return converted;
    }

    @Override
    public FuelConsumptionDTO convertToDTO(String line) {
        final String[] attributes = line.split(";");
        if(attributes.length < 5){
            throw new RuntimeException("Invalid line for conversion. The line needs to have 5 positions: fuel type;date;price per liter;volume in liters;driver id");
        }
        final String fuelTypeName = attributes[0];
        final LocalDateTime date = LocalDateTime.from(dtf.parse(attributes[1]));
        final BigDecimal pricePerLiter = new BigDecimal(attributes[2]);
        final BigDecimal volumeInLiters = new BigDecimal(attributes[3]);
        final Long driverId = Long.valueOf(attributes[4]);
        return new FuelConsumptionDTO(fuelTypeName, date, pricePerLiter, volumeInLiters, driverId);
    }
}
