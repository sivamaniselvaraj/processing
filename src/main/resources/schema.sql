CREATE TABLE IF NOT EXISTS Jobs (
                      job_id          BIGINT PRIMARY KEY,
                      order_id        BIGINT NOT NULL,
                      job_type        VARCHAR(50),
                      job_status      VARCHAR(50),
                      created_at      TIMESTAMP NOT NULL,
                      started_at      TIMESTAMP,
                      completed_at    TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ProcessingStatus (
                                  status_id       BIGINT PRIMARY KEY,
                                  job_id          BIGINT NOT NULL,
                                  status          VARCHAR(50),
                                  message         TEXT,
                                  created_at      TIMESTAMP NOT NULL,
                                  FOREIGN KEY (job_id) REFERENCES Jobs(job_id)
);

CREATE INDEX IF NOT EXISTS idx_jobs_order ON Jobs(order_id);

CREATE TABLE IF NOT EXISTS OutboxEvents (
                              event_id        BIGINT PRIMARY KEY,
                              aggregate_type  VARCHAR(100),
                              aggregate_id    BIGINT,
                              event_type      VARCHAR(100),
                              payload         JSON,
                              status          VARCHAR(50),
                              created_at      TIMESTAMP,
                              processed_at    TIMESTAMP
);
