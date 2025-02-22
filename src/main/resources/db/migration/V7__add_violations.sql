CREATE TABLE violations (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    deducted_points INT NOT NULL CHECK (deducted_points > 0),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);
