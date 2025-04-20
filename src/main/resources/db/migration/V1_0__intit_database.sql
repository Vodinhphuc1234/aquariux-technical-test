CREATE TABLE IF NOT EXISTS user_account (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    balance DECIMAL(18, 4) NOT NULL,
    created_date       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by         BIGINT,
    last_modified_by   BIGINT
);

CREATE TABLE IF NOT EXISTS wallet (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    symbol VARCHAR(10),
    quantity DECIMAL(18, 4) NOT NULL,
    UNIQUE (user_id, symbol),
    FOREIGN KEY (user_id) REFERENCES user_account(id),
    created_date       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by         BIGINT,
    last_modified_by   BIGINT
);

CREATE TABLE IF NOT EXISTS price (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    symbol VARCHAR(10) UNIQUE ,
    ask_price DECIMAL(18, 4) NOT NULL,
    bid_price DECIMAL(18, 4) NOT NULL,
    created_date       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by         BIGINT,
    last_modified_by   BIGINT
);

CREATE TABLE IF NOT EXISTS trading_transaction (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    symbol VARCHAR(10),
    trading_type VARCHAR(4) CHECK (trading_type IN ('BUY', 'SELL')),
    trading_price DECIMAL(18, 4),
    quantity DECIMAL(18, 4) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (user_id) REFERENCES user_account(id),
    created_date       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by         BIGINT,
    last_modified_by   BIGINT
);

CREATE TABLE IF NOT EXISTS pending_transaction (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    transaction_id INT,
    UNIQUE (user_id, transaction_id),
    FOREIGN KEY (user_id) REFERENCES user_account(id),
    FOREIGN KEY (transaction_id) REFERENCES trading_transaction(id),
    created_date       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by         BIGINT,
    last_modified_by   BIGINT
);