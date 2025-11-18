-- +goose up
ALTER TABLE feeds ADD COLUMN last_fetched_at TIMESTAMP;

-- +goose down
ALTER TABLE feeds DROP COLUMN api_key;