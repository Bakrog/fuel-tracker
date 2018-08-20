package com.fueltracker.driver.consumption.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.NEVER, readOnly = true)
@Component
public class ReportConsumptionHandler {

    @Autowired
    private ReportConsumptionService service;
}
