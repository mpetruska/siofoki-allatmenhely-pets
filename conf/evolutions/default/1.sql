-- !Ups

CREATE TABLE pets_user(
  id         VARBINARY(16) NOT NULL,
  username   TEXT NOT NULL,
  full_name  TEXT,
  password   TEXT,
  is_admin   BOOLEAN NOT NULL,
  is_enabled BOOLEAN NOT NULL,

  PRIMARY KEY (id),
  INDEX ix_user_username(username(200))
);

INSERT INTO pets_user
  VALUES (UUID_TO_BIN(UUID()), 'root', 'root user', '$2a$10$gBcS2EngaWq1mRUOCowa9uZZ5AA4n2hFvWCmFx7CvrmzvZxCozhEW', TRUE, TRUE);

CREATE TABLE pets_session(
  id                VARBINARY(16) NOT NULL,
  user_id           VARBINARY(16) NOT NULL,
  created_utc       DATETIME      NOT NULL,
  last_accessed_utc DATETIME      NOT NULL,
  is_permanent      BOOLEAN       NOT NULL DEFAULT 0,

  PRIMARY KEY (id),
  INDEX ix_session_last_accessed(is_permanent, last_accessed_utc)
);

-- !Downs

DROP TABLE pets_user;
DROP TABLE pets_session;
