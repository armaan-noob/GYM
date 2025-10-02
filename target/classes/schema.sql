-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS gym_management;
USE gym_management;

-- Members table
CREATE TABLE members (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    contact VARCHAR(20) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    membership_type VARCHAR(50) NOT NULL,
    join_date DATE NOT NULL,
    user_role ENUM('ADMIN', 'MEMBER') NOT NULL DEFAULT 'MEMBER',
    password VARCHAR(255) NOT NULL,
    CONSTRAINT chk_age CHECK (age >= 16 AND age <= 100)
);

-- Payments table
CREATE TABLE payments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_date DATE NOT NULL,
    payment_type VARCHAR(50) NOT NULL,
    status ENUM('PAID', 'PENDING', 'FAILED') NOT NULL DEFAULT 'PAID',
    FOREIGN KEY (member_id) REFERENCES members(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT chk_amount CHECK (amount > 0)
);

-- Attendance table
CREATE TABLE attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT NOT NULL,
    date DATE NOT NULL,
    check_in_time TIME NOT NULL,
    check_out_time TIME,
    FOREIGN KEY (member_id) REFERENCES members(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT unique_daily_attendance UNIQUE (member_id, date)
);

-- Insert default admin account
INSERT INTO members (name, age, contact, email, membership_type, join_date, user_role, password)
VALUES ('Admin', 30, '1234567890', 'admin@gym.com', 'LIFETIME', CURRENT_DATE, 'ADMIN', 'admin123');

-- Create indexes for better performance
CREATE INDEX idx_member_email ON members(email);
CREATE INDEX idx_payment_date ON payments(payment_date);
CREATE INDEX idx_attendance_date ON attendance(date);