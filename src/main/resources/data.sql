-- ============================================================
-- DATA.SQL — Sample data for Campus Skill Tracker
-- Runs automatically on startup (H2 and MySQL compatible)
-- Uses standard SQL INSERT (no MySQL-specific IGNORE keyword)
-- ============================================================

-- 10 Sample Students
INSERT INTO students (full_name, email, department, year_of_study, phone_number) VALUES
('John Doe',          'john.doe@campus.edu',      'Computer Science', 3, '555-0101'),
('Jane Smith',        'jane.smith@campus.edu',     'Mathematics',      2, '555-0102'),
('Michael Johnson',   'michael.j@campus.edu',      'Physics',          4, '555-0103'),
('Emily Davis',       'emily.d@campus.edu',         'Biology',          1, '555-0104'),
('William Brown',     'william.b@campus.edu',       'Engineering',      3, '555-0105'),
('Olivia Wilson',     'olivia.w@campus.edu',        'Arts',             2, '555-0106'),
('James Moore',       'james.m@campus.edu',         'Computer Science', 4, '555-0107'),
('Sophia Taylor',     'sophia.t@campus.edu',        'Chemistry',        1, '555-0108'),
('Benjamin Anderson', 'benjamin.a@campus.edu',      'Business',         3, '555-0109'),
('Isabella Thomas',   'isabella.t@campus.edu',      'Economics',        2, '555-0110');

-- 10 Sample Skills (linked to above students by row insertion order: IDs 1–10)
INSERT INTO skills (skill_name, skill_level, certificate_provider, student_id) VALUES
('Java Programming',  'Advanced',     'Oracle',          1),
('Spring Boot',       'Intermediate', 'Coursera',        1),
('Python',            'Advanced',     'DataCamp',        2),
('Data Analysis',     'Intermediate', 'Google',          2),
('Machine Learning',  'Beginner',     'Coursera',        3),
('React.js',          'Intermediate', 'Udemy',           5),
('Docker',            'Beginner',     'Linux Foundation', 7),
('MySQL',             'Advanced',     'Oracle',          7),
('Project Management','Intermediate', 'PMI',             9),
('Data Visualization','Intermediate', 'Tableau',         10);
