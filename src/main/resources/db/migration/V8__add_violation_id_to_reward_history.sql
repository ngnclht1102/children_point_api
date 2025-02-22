-- Add violation_id column to reward_history table
ALTER TABLE points_history
    ADD COLUMN violation_id INT,
    ADD CONSTRAINT fk_violation
        FOREIGN KEY (violation_id) REFERENCES violations(id)
            ON DELETE SET NULL;
