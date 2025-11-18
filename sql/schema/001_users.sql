-- +goose Up

CREATE TABLE Users(
    id UUID PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    name VARCHAR(30) NOT NULL
);

-- +goose Down

DROP TABLE users;