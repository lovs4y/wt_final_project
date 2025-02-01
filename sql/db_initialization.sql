CREATE TABLE users
(
    id    BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    name  VARCHAR,
    email VARCHAR
);

CREATE TABLE notebooks
(
    id      BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    name    VARCHAR,
    created TIMESTAMP,
    user_id BIGINT REFERENCES users (id)
);

CREATE TABLE notes
(
    id          BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    title       VARCHAR,
    created     TIMESTAMP,
    modified    TIMESTAMP,
    content     TEXT,
    notebook_id BIGINT REFERENCES notebooks (id)
)