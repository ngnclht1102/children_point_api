-- Create the points table
CREATE TABLE points (
                        id SERIAL PRIMARY KEY,
                        total_points INT NOT NULL DEFAULT 0,
                        created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create the rewards table
CREATE TABLE rewards (
                         id SERIAL PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         required_points INT NOT NULL,
                         created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create the points_history table
CREATE TABLE points_history (
                                id SERIAL PRIMARY KEY,
                                type VARCHAR(10) NOT NULL,  -- "add" or "deduct"
                                points INT NOT NULL,
                                note TEXT,
                                created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);