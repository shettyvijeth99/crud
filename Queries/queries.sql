-- Creating the employee table
CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    position VARCHAR(255),
    salary DECIMAL NOT NULL,
    version INT NOT NULL DEFAULT 1
);

-- Inserting data into the employee table
INSERT INTO employee (name, position, salary) 
VALUES 
('John Doe', 'Developer', 80000.0),
('Jane Smith', 'Manager', 95000.0),
('Alice Brown', 'HR', 65000.0);

-- Updating data in the employee table
UPDATE employee 
SET position = 'Senior Developer' 
WHERE id = 1;

-- Selecting all data from the employee table
SELECT * FROM employee;

-- Deleting an employee by id
DELETE FROM employee WHERE id = 1;
