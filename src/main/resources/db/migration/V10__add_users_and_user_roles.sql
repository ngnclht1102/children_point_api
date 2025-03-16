-- V1.0__Create_users_roles.sql

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       full_name VARCHAR(255) NOT NULL,
                       created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE user_roles (
                            user_id INT REFERENCES users(id) ON DELETE CASCADE,
                            role_id INT REFERENCES roles(id) ON DELETE CASCADE,
                            PRIMARY KEY (user_id, role_id)
);

CREATE TABLE parent_children (
                               parent_id INT REFERENCES users(id) ON DELETE CASCADE,
                               child_id INT REFERENCES users(id) ON DELETE CASCADE,
                               PRIMARY KEY (parent_id, child_id)
);

CREATE TABLE teacher_students (
                                  teacher_id INT REFERENCES users(id) ON DELETE CASCADE,
                                  student_id INT REFERENCES users(id) ON DELETE CASCADE,
                                  PRIMARY KEY (teacher_id, student_id)
);

CREATE TABLE center_students (
                                 center_id INT REFERENCES users(id) ON DELETE CASCADE,
                                 student_id INT REFERENCES users(id) ON DELETE CASCADE,
                                 PRIMARY KEY (center_id, student_id)
);

-- Insert default roles
INSERT INTO roles (name) VALUES ('PARENT'), ('CHILD'), ('TEACHER'), ('EDUCATION_CENTER');

-- Insert default users (Password: "200718" hashed with BCrypt)
INSERT INTO users (username, password, full_name) VALUES
                                                      ('banam', '$2a$10$0P4F8MwMBTXBC9zHtTqcnO0c4mTuClG6J8jD5yGLhjlXo3VlbG8J2', 'Nguyễn Giang Nam'),
                                                      ('mehan', '$2a$10$0P4F8MwMBTXBC9zHtTqcnO0c4mTuClG6J8jD5yGLhjlXo3VlbG8J2', 'Huỳnh Thị Ngọc Hân'),
                                                      ('binz',  '$2a$10$0P4F8MwMBTXBC9zHtTqcnO0c4mTuClG6J8jD5yGLhjlXo3VlbG8J2', 'Nguyễn Đức Dương'),
                                                      ('cothuong','$2a$10$0P4F8MwMBTXBC9zHtTqcnO0c4mTuClG6J8jD5yGLhjlXo3VlbG8J2', 'Nguyễn Thị Thuỳ Thương'),
                                                      ('dolin', '$2a$10$0P4F8MwMBTXBC9zHtTqcnO0c4mTuClG6J8jD5yGLhjlXo3VlbG8J2', 'Trung tâm Dolin'),
                                                        ('tamnguyen', '$2a$10$0P4F8MwMBTXBC9zHtTqcnO0c4mTuClG6J8jD5yGLhjlXo3VlbG8J2', 'Nguyễn Thị Tâm Anh'),
                                                        ('binhthan', '$2a$10$0P4F8MwMBTXBC9zHtTqcnO0c4mTuClG6J8jD5yGLhjlXo3VlbG8J2', 'Thân Thanh Bình'),
                                                        ('tuyetmai',  '$2a$10$0P4F8MwMBTXBC9zHtTqcnO0c4mTuClG6J8jD5yGLhjlXo3VlbG8J2', 'Thân Thị Tuyết Mai');

-- Assign roles
INSERT INTO user_roles (user_id, role_id) VALUES
                                              (1, 1), -- Parent
                                              (2, 1), -- Parent
                                              (3, 2), -- Child
                                              (3, 3), -- Teacher
                                              (4, 4), -- Education Center
                                                (5, 1), -- Parent
                                                (6, 1), -- Parent
                                                (7, 2); -- Child

-- Assign relationships
INSERT INTO parent_children (parent_id, child_id) VALUES
                                      (1, 3),
                                      (2, 3),
                                      (5, 7),
                                      (6, 7);
INSERT INTO teacher_students (teacher_id, student_id) VALUES
                                     (4, 3);

INSERT INTO center_students (center_id, student_id) VALUES
                                    (5, 3),
                                    (5, 7);
