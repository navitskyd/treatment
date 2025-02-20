
CREATE TABLE treatment_task (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    action VARCHAR(50) NOT NULL,
    patient_id INTEGER NOT NULL,
    start_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_treatment_task_patient FOREIGN KEY (patient_id) REFERENCES patient (id) ON DELETE CASCADE
);
