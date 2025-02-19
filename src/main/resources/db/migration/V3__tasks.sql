
CREATE TABLE treatment_task (
    id INTEGER PRIMARY KEY,
    action VARCHAR(50) NOT NULL,
    patient_id INTEGER NOT NULL,
    start_time TIMESTAMP WITH TIME ZONE NOT NULL,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_treatment_task_patient FOREIGN KEY (patient_id) REFERENCES patient (id) ON DELETE CASCADE
);
