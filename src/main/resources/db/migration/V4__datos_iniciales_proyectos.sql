INSERT INTO skill (name) VALUES ('Kotlin'), ('Spring Boot'), ('PostgreSQL'), ('React');

INSERT INTO project (title, description, budget, estimated_date, work_level, work_type) VALUES
                                                                                            ('API de inventario', 'Backend para controlar el inventario de un taller', 1500.00, '2026-12-15', 'SEMI_SENIOR', 'REMOTO'),
                                                                                            ('Panel de ventas', 'Frontend con reportes de ventas mensuales', 900.00, '2026-11-30', 'JUNIOR', 'HIBRIDO');

INSERT INTO app_user (full_name, email, contact) VALUES
                                                     ('Ana Torres', 'ana@correo.com', '0991234567'),
                                                     ('Luis Pérez', 'luis@correo.com', '0987654321');

INSERT INTO project_skill (project_id, skill_id) VALUES (1, 1), (1, 2), (1, 3), (2, 4);
INSERT INTO user_skill (user_id, skill_id) VALUES (1, 1), (1, 2), (2, 3), (2, 4);