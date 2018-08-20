package com.fueltracker.driver.consumption.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.NEVER, readOnly = true)
@Service
public class ReportConsumptionServiceImpl implements ReportConsumptionService {

    @Autowired
    private ReportConsumptionRepository repository;
}
