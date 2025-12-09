CREATE TABLE _user (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE project_idea (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(MAX),
    tags VARCHAR(255),
    user_id BIGINT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES _user(id)
);
