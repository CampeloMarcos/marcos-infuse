CREATE Table TB_CLI_CLIENT (
    cli_id BIGINT NOT NULL AUTO_INCREMENT,
    cli_name VARCHAR(300) NOT NULL,
    PRIMARY KEY (cli_id)
);

CREATE Table TB_ORD_ORDER (
    ord_id BIGINT NOT NULL AUTO_INCREMENT,
    ord_number_control VARCHAR(50) NOT NULL,
    ord_date_register DATE NOT NULL,
    ord_product_name VARCHAR(300) NOT NULL,
    ord_price DECIMAL(9,2) NOT NULL,
    ord_total_order_price DECIMAL(9,2) NOT NULL,
    ord_amount INT NOT NULL,
    cli_id BIGINT NOT NULL,
    PRIMARY KEY (ord_id),
    Foreign Key (cli_id) REFERENCES TB_CLI_CLIENT(cli_id)
);