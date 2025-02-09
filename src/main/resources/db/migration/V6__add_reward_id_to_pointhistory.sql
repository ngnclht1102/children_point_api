-- Add reward_id column to points_history
ALTER TABLE points_history
    ADD COLUMN reward_id INT,
    ADD CONSTRAINT fk_reward
        FOREIGN KEY (reward_id) REFERENCES rewards(id)
            ON DELETE SET NULL;
