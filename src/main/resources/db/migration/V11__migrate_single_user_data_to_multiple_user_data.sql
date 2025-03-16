ALTER TABLE points
    ADD COLUMN user_id INT NULL;

ALTER TABLE points_history
    ADD COLUMN user_id INT NULL;

ALTER TABLE rewards
    ADD COLUMN user_id INT NULL;

ALTER TABLE challenges
    ADD COLUMN user_id INT NULL;

ALTER TABLE points
    ADD CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE;

ALTER TABLE points_history
    ADD CONSTRAINT fk_user_history
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE;

ALTER TABLE rewards
    ADD CONSTRAINT fk_user_rewards
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE;

ALTER TABLE challenges
    ADD CONSTRAINT fk_user_challenges
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE;

UPDATE points
SET user_id = 3;

UPDATE points_history
SET user_id = 3;

UPDATE rewards
SET user_id = 3;

UPDATE challenges
SET user_id = 3;

ALTER TABLE points
    ALTER COLUMN user_id SET NOT NULL;

ALTER TABLE points_history
    ALTER COLUMN user_id SET NOT NULL;

ALTER TABLE rewards
    ALTER COLUMN user_id SET NOT NULL;

ALTER TABLE challenges
    ALTER COLUMN user_id SET NOT NULL;
