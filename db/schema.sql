-- 1. Create and select the database
CREATE DATABASE IF NOT EXISTS edusync_db;
USE edusync_db;

-- 2. Create the core Users table (Matches User.java)
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('STUDENT', 'FACULTY', 'ADMIN') NOT NULL
);

-- 3. Create the Students table (Matches Student.java)
-- Notice how user_id acts as BOTH the Primary Key and a Foreign Key linking to the users table!
CREATE TABLE students (
    user_id INT PRIMARY KEY,
    roll_number VARCHAR(20) UNIQUE NOT NULL,
    department VARCHAR(50),
    semester INT DEFAULT 1,
    cgpa DOUBLE DEFAULT 0.0,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 4. Create the Faculty table (Matches Faculty.java)
CREATE TABLE faculty (
    user_id INT PRIMARY KEY,
    employee_id VARCHAR(20) UNIQUE NOT NULL,
    department VARCHAR(50),
    designation VARCHAR(50),
    subject VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 5. Create the Courses table (Matches Course.java)
CREATE TABLE courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_code VARCHAR(20) UNIQUE NOT NULL,
    course_name VARCHAR(100) NOT NULL,
    credits INT NOT NULL,
    department VARCHAR(50),
    instructor_id INT, -- Connects to the faculty table
    FOREIGN KEY (instructor_id) REFERENCES faculty(user_id) ON DELETE SET NULL
);

--
CREATE TABLE enrollments (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    enrollment_date DATE,
    status VARCHAR(20),
    FOREIGN KEY (student_id) REFERENCES students(user_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

CREATE TABLE grades (
    grade_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    assessment_type VARCHAR(50),
    score_obtained DOUBLE,
    max_score DOUBLE,
    feedback TEXT,
    FOREIGN KEY (student_id) REFERENCES students(user_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

CREATE TABLE attendance (
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    attendance_date DATE,
    status VARCHAR(20),
    FOREIGN KEY (student_id) REFERENCES students(user_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

USE edusync_db;

-- 1. Create an Administrator
INSERT INTO users (name, email, password, role) 
VALUES ('Pranav Admin', 'admin@edusync.com', 'admin123', 'ADMIN');

-- 2. Create a Computer Science Student
INSERT INTO users (name, email, password, role) 
VALUES ('Test Student', 'student@edusync.com', 'student123', 'STUDENT');

-- Link the student details using the ID we just generated
INSERT INTO students (user_id, roll_number, department, semester, cgpa) 
VALUES (LAST_INSERT_ID(), 'CS-101', 'Computer Science', 4, 8.5);

-- 3. Create a Professor
INSERT INTO users (name, email, password, role) 
VALUES ('Prof. Java', 'faculty@edusync.com', 'faculty123', 'FACULTY');

-- Link the faculty details
INSERT INTO faculty (user_id, employee_id, department, designation, subject) 
VALUES (LAST_INSERT_ID(), 'EMP-99', 'Computer Science', 'Senior Lecturer', 'Java OOP');