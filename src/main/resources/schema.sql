CREATE TABLE IF NOT EXISTS photo(
    id IDENTITY PRIMARY KEY,
    file_name VARCHAR(255),
    content_type VARCHAR(255),
    data VARBINARY(MAX)
);