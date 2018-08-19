create TABLE tb_fuel_type (
    fuel_type_id  IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE tb_consumption
(
    consumption_id IDENTITY PRIMARY KEY NOT NULL,
    fuel_type_id BIGINT NOT NULL,
    price_per_liter NUMERIC(18,2) NOT NULL,
    volume_in_liters NUMERIC(18,2) NOT NULL,
    consumption_date TIMESTAMP NOT NULL,
    driver_id BIGINT NOT NULL,
    CONSTRAINT fk_consumption__fuel_type FOREIGN KEY (fuel_type_id) REFERENCES tb_fuel_type (fuel_type_id)
);