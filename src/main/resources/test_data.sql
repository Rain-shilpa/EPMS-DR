-- Inserting into Department table
INSERT INTO Department (id, name, budget)
VALUES
(1, 'Engineering', 500000),
(2, 'Marketing', 300000),
(3, 'HR', 150000);

-- Inserting into Employee table
INSERT INTO Employee (id, name, email, department_id, date_of_joining, salary, manager_id)
VALUES
(1, 'Alice Johnson', 'alice@example.com', 1, '2021-01-15', 75000, NULL),
(2, 'Bob Smith', 'bob@example.com', 2, '2020-03-10', 80000, 1),
(3, 'Charlie Brown', 'charlie@example.com', 1, '2019-07-22', 65000, 1),
(4, 'David Wilson', 'david@example.com', 2, '2018-10-01', 95000, 2),
(5, 'Eva Green', 'eva@example.com', 1, '2022-05-15', 70000, 1),
(6, 'Frank Miller', 'frank@example.com', 2, '2017-09-12', 88000, 2),
(7, 'Grace Lee', 'grace@example.com', 3, '2023-01-10', 60000, NULL),
(8, 'Helen Adams', 'helen@example.com', 3, '2023-02-01', 67000, 5);

-- Inserting into PerformanceReview table
INSERT INTO Performance_Review (id, employee_id, review_date, score, review_comments)
VALUES
(1, 1, '2023-06-01', 4.5, 'Excellent performance, great leadership skills.'),
(2, 2, '2023-06-01', 4.0, 'Good work but needs improvement in communication.'),
(3, 3, '2023-06-01', 3.5, 'Satisfactory performance, needs to improve technical skills.'),
(4, 4, '2023-06-01', 4.8, 'Exceptional performance, consistently exceeds expectations.'),
(5, 5, '2023-06-01', 4.2, 'Solid performance, showing good progress in projects.'),
(6, 6, '2023-06-01', 3.8, 'Good performance, but needs to work on time management.'),
(7, 7, '2023-06-01', 3.6, 'Needs improvement in HR policies and employee engagement.'),
(8, 8, '2023-06-01', 4.1, 'Excellent communication, but needs to improve team collaboration.');

-- Inserting into Project table
INSERT INTO Project (id, name, start_date, end_date, department_id)
VALUES
(1, 'Project Alpha', '2023-01-01', '2023-12-31', 1),
(2, 'Project Beta', '2023-03-01', '2023-09-30', 2),
(3, 'Project Gamma', '2023-02-01', '2023-10-31', 3),
(4, 'Project Delta', '2023-04-15', '2023-11-15', 1),
(5, 'Project Epsilon', '2023-06-01', '2023-12-01', 2);

-- Inserting into Employee_Project table (Many-to-Many)
INSERT INTO Employee_Project (employee_id, project_id, assigned_date, role)
VALUES
(1, 1, '2023-01-15', 'Lead Engineer'),
(2, 2, '2023-03-10', 'Marketing Specialist'),
(3, 1, '2023-07-22', 'Software Developer'),
(4, 3, '2023-05-10', 'HR Specialist'),
(5, 1, '2023-06-01', 'Junior Engineer'),
(6, 4, '2023-05-15', 'Project Manager'),
(7, 3, '2023-03-01', 'HR Analyst'),
(8, 5, '2023-06-05', 'Content Strategist'),
(1, 4, '2023-02-10', 'Senior Developer'),
(2, 5, '2023-04-20', 'Social Media Manager');
