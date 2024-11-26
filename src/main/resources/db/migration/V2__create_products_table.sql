CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    code VARCHAR(17) NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    currency ENUM('USD', 'EUR', 'PEN') NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT chk_quantity CHECK (quantity >= 0),
    CONSTRAINT chk_code CHECK (code REGEXP '^[0-9]{3}-[0-9]{2}-[0-9]{3}-[0-9]{4}-[0-9]{1}$')
);
CREATE INDEX idx_code ON products (code);
CREATE INDEX idx_currency ON products (currency);