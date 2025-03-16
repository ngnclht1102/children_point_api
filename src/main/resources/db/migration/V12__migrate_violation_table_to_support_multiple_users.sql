ALTER TABLE violations
    ADD COLUMN user_id INT NULL;

ALTER TABLE violations
    ADD CONSTRAINT fk_user_violations
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE;


UPDATE violations
SET user_id = 3;


ALTER TABLE violations
    ALTER COLUMN user_id SET NOT NULL;