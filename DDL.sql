CREATE TABLE users (
                       user_id BINARY(16) PRIMARY KEY,
                       email VARCHAR(255),
                       password VARCHAR(255),
                       userStatus ENUM('ACTIVE', 'UNACTIVE', 'BLOCKED')
);

CREATE TABLE shipment (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          orderId BIGINT,
                          shipmentStatus ENUM('READY', 'STARTED', 'ARRIVED')
);

CREATE TABLE seller (
                        id BINARY(16) PRIMARY KEY,
                        email VARCHAR(255),
                        password VARCHAR(255),
                        registrationNum VARCHAR(255),
                        sellerStatus ENUM('ACTIVE', 'UNACTIVE', 'BLOCKED')
);
CREATE TABLE product (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         price BIGINT,
                         productName VARCHAR(255),
                         createdAt DATE
);

CREATE TABLE product_inventory (
                                   productId BIGINT PRIMARY KEY,
                                   quantity BIGINT
);
CREATE TABLE payment (
                         id BIGINT PRIMARY KEY,
                         orderId VARCHAR(255) UNIQUE,
                         orderName VARCHAR(255),
                         tid VARCHAR(255) UNIQUE,
                         aid VARCHAR(255),
                         paymentType ENUM('MONEY', 'CARD'),
                         totalAmount BIGINT,
                         paymentStatus ENUM('READY_PAYMENT', 'SUCCESSED_PAYMENT', 'FAILED_PAYMENT', 'CANCELD_PAYMENT')
);

CREATE TABLE orders (
                        id VARCHAR(255) PRIMARY KEY,
                        order_status ENUM('NOT_ORDERED', 'ORDERED', 'CANCELLED'),
                        user_id BINARY(16),
                        addr VARCHAR(255),
                        name VARCHAR(255),
                        phone_num VARCHAR(255)
);

CREATE TABLE order_item (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            order_id VARCHAR(255),
                            product_id BIGINT,
                            total_quantity BIGINT,
                            total_amount BIGINT,
                            FOREIGN KEY (order_id) REFERENCES orders (id)
);


CREATE TABLE order_sequence (
                                id BIGINT PRIMARY KEY,
                                lastSequence BIGINT
);
