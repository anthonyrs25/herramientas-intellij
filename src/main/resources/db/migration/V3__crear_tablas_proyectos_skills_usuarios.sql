CREATE TABLE skill (
                       id   BIGSERIAL    PRIMARY KEY,
                       name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE project (
                         id             BIGSERIAL      PRIMARY KEY,
                         title          VARCHAR(150)   NOT NULL,
                         description    VARCHAR(1000),
                         budget         NUMERIC(10, 2) NOT NULL CHECK (budget >= 0),
                         estimated_date DATE,
                         work_level     VARCHAR(20)    NOT NULL CHECK (work_level IN ('JUNIOR', 'SEMI_SENIOR', 'SENIOR')),
                         work_type      VARCHAR(20)    NOT NULL CHECK (work_type IN ('REMOTO', 'PRESENCIAL', 'HIBRIDO'))
);

CREATE TABLE app_user (
                          id        BIGSERIAL    PRIMARY KEY,
                          full_name VARCHAR(100) NOT NULL,
                          email     VARCHAR(150) NOT NULL UNIQUE,
                          contact   VARCHAR(15),
                          status    VARCHAR(20)  NOT NULL DEFAULT 'ACTIVO' CHECK (status IN ('ACTIVO', 'INACTIVO'))
);

CREATE TABLE project_skill (
                               project_id BIGINT NOT NULL REFERENCES project (id) ON DELETE CASCADE,
                               skill_id   BIGINT NOT NULL REFERENCES skill (id) ON DELETE CASCADE,
                               PRIMARY KEY (project_id, skill_id)
);

CREATE TABLE user_skill (
                            user_id  BIGINT NOT NULL REFERENCES app_user (id) ON DELETE CASCADE,
                            skill_id BIGINT NOT NULL REFERENCES skill (id) ON DELETE CASCADE,
                            PRIMARY KEY (user_id, skill_id)
);