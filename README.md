# fuel-tracker [![Build Status](https://travis-ci.com/Bakrog/fuel-tracker.svg?branch=develop)](https://travis-ci.com/Bakrog/fuel-tracker)
Track fuel consumption for small transportation company's

# Installation

To generate the jar you just need maven. Use the following command:
```
mvn clean install
```

Them just execute the jar generated at _target/fuel-tracker.jar_

# Endpoints

The month path param needs to be a integer at the range: 1 <= month <= 12

- GET "/" -> Simple welcome page
- POST JSON "/fuel-consumption" -> Save the consumption json on the database.
- POST MULTIPART/FORM-DATA "/fuel-consumption" -> Send a CSV file (fuel type;date;price per liter;volume in liters;driver id) and save the valid lines
- GET "/report/spended-by-month" -> Report spended money by month
- GET "/report/spended-by-month/{driverId}" -> Reports spended money by month and driver id
- GET "/report/fuel-consumption/{month}" -> Reports the fuel consumption at the month
- GET "/report/fuel-consumption/{month}/{driverId}" -> Reports the fuel consumption at the month of the driver
- GET "/report/fuel-consumption-by-fuel-type/{month}" -> Reports the total fuel consumption by fuel type at the month
- GET "/report/fuel-consumption-by-fuel-type/{month}/{driverId}" -> Reports the total fuel consumption by fuel type at the month by the driver

# Tests

You can use the postman collection at the root of the repository "Fuel Tracker.postman_collection.json"

# Roadmap

- ~~Save driver fuel consumption~~
- ~~Bulk save driver fuel consumption~~
- ~~Add report for spend amount of money by month~~
- ~~Add report for list of fuel consumption for specified month~~
- ~~Add report for statistics for fuel type by month~~
- ~~Add driver Id parameter for reports~~
- Save drivers before saving consumption?
- Authentication?
- Frontend with React?