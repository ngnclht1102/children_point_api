-- Add challenge_id column to points_history
ALTER TABLE points_history
    ADD COLUMN challenge_id INT,
    ADD CONSTRAINT fk_challenge
        FOREIGN KEY (challenge_id) REFERENCES challenges(id)
            ON DELETE SET NULL;
