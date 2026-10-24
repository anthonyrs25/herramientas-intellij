CREATE TABLE product (
                         id          BIGSERIAL      PRIMARY KEY,
                         description VARCHAR(100)   NOT NULL,
                         price       NUMERIC(10, 2) NOT NULL CHECK (price >= 0),
                         stock       INTEGER        NOT NULL DEFAULT 0 CHECK (stock >= 0)
);