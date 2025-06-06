use training_center;

-- ------------------------------------------- ACCOUNT -----------------------------------------------------
insert into account(account_id, role, username, password)
	values
    ('1', 'manager', 'manager', 1),
    ('2', 'instructor', 'instructor', 1),
    ('3', 'instructor', 'instructor1', 1),
    ('4', 'instructor', 'instructor2', 1),
    ('5', 'instructor', 'instructor3', 1),
    ('6', 'instructor', 'instructor4', 1),
    ('7', 'instructor', 'instructor5', 1),
    ('8', 'instructor', 'instructor6', 1),
    ('9', 'instructor', 'instructor7', 1),
    ('10', 'instructor', 'instructor8', 1),
    ('11', 'student', 'student', 1),
	('12', 'student', 'student1', 1),
    ('13', 'student', 'student2', 1),
    ('14', 'student', 'student3', 1),
    ('15', 'student', 'student4', 1),
    ('16', 'student', 'student5', 1),
    ('17', 'student', 'student6', 1),
    ('18', 'student', 'student7', 1),
    ('19', 'student', 'student8', 1),
    ('20', 'student', 'student9', 1);



-- ------------------------------------------- MANAGER -----------------------------------------------------
insert into manager(manager_id, account_id, first_name, middle_name, last_name, email, phone) 
	values
	(1, 1, 'Nguyen', 'Van', 'Admin', 'trainingcentermanagementsystem@gmail.com', '0912345678');



-- ------------------------------------------- STUDENT -----------------------------------------------------
-- There are 10 students in total, 
-- but there are only first 5 students who participates at least 1 course
insert into student(student_id, account_id, first_name, middle_name, last_name, email, phone, isBlocked) 
	values
	(1, 11, 'Ta', 'Duc', 'Duy', 'taduy.test1@gmail.com', '0900000001', 0),
    (2, 12, 'Truong', 'Thanh', 'Hung', 'truongthanhhung@gmail.com', '0900000002', 0),
    (3, 13, 'Nguyen', 'Cong', 'Duy', 'nguyencongduy@gmail.com', '0900000003', 0),
    (4, 14, 'Nguyen', 'Quang', 'Son', 'nguyenquangson@gmail.com', '0900000004', 0),
    (5, 15, 'Dao', 'Nguyen', 'Khoi', 'daonguyenkhoi@gmail.com', '0900000005', 0),
    (6, 16, 'Nguyen', 'Le', 'Hoang', 'nguyenlehoang@gmail.com', '0900000006', 0),
	(7, 17, 'Nguyen', 'Hoai', 'Anh', 'nguyenhoaianh@gmail.com', '0900000007', 0),
    (8, 18, 'Tran', 'Khanh', 'Duy', 'trankhanhduy@gmail.com', '0900000008', 0),
    (9, 19, 'Nguyen', 'Xuan', 'Bach', 'nguyenxuanbach@gmail.com', '0900000009', 0),
    (10, 20, 'Nguyen', 'Trung', 'Son', 'nguyentrungson@gmail.com', '0900000010', 0);
    
    
    
-- ------------------------------------------- INSTRUCTOR -----------------------------------------------------
-- There are 9 instructors in total, 
-- but there are only first 6 instructors who teach at least 1 course
insert into instructor(instructor_id, account_id, first_name, middle_name, last_name, level, email, phone, isBlocked) 
	values
	(1, 2, 'Nguyen', 'Dinh', 'Van', 4, 'nguyendinhvan@gmail.com', '0910000001', 0),
    (2, 3, 'Ngo', 'Lam', 'Trung', 3, 'ngolamtrung@gmail.com', '0910000002', 0),
    (3, 4, 'Cao', 'Tuan', 'Dung', 2, 'caotuandung@gmail.com', '0910000003', 0),
    (4, 5, 'Ho', 'Minh', 'Toan', 1, 'hominhtoan@gmail.com', '0910000004', 0),
    (5, 6, 'Vu', 'The', 'Khoi', 4, 'vuthekhoi@gmail.com', '0910000005', 0),
    (6, 7, 'Nguyen', 'Tai', 'Vuong', 3, 'nguyentaivuong@gmail.com', '0910000006', 0),
	(7, 8, 'Nguyen', 'Kieu', 'Anh', 2, 'nguyenkieuanh@gmail.com', '0910000007', 0),
	(8, 9, 'Nguyen', 'Vu', 'Long', 1, 'nguyenvulong@gmail.com', '0910000008', 0),
    (9, 10, 'Nguyen', 'Chi', 'Ngoc', 5, 'nguyenchingoc@gmail.com', '0910000009', 0);
    
    
    
--  -------------------------------------------	COURSE -----------------------------------------------------
-- Every course has at least one registerd student  
insert into course(course_id, name, description, rate, capacity)
	values
    ('CS4420', 'Database Management System', 'Description for CS4420', 400.0, 40),
	('CS3360', 'Object Oriented Programming', 'Description for CS3360', 300.0, 35),
    ('CS3365', 'Computer Architecture', 'Description for CS3365', 300.0, 35),
    ('CS2250', 'Computer Science I', 'Description for CS2250', 200.0, 35),
    ('CS2255', 'Computer Science II', 'Description for CS2255', 200.0, 35),
    ('MTH1125', 'Calculus I', 'Description for MTH1125', 100.0, 50),
    ('MTH1126', 'Calculus II', 'Description for MTH1126', 100.0, 50),
    ('MTH2215', 'Applied Discrete Mathematics', 'Description for MTH2215', 200.0, 50),
    ('ECO2251', 'Princile Of Macroeconomics', 'Description for ECO2251', 200.0, 50),
    ('ECO2252', 'Princile Of Microeconomics', 'Description for ECO2252', 200.0, 50);
    
    
    
-- ------------------------------------------- SESSION -----------------------------------------------------
-- Every couse has exactly 5 sessions
insert into session(session_id, course_id, title, content)
	values
    (1, 'CS4420', 'Lesson 1', 'Content 1'),
    (2, 'CS4420', 'Lesson 2', 'Content 2'),
    (3, 'CS4420', 'Lesson 3', 'Content 3'),
    (4, 'CS4420', 'Lesson 4', 'Content 4'),
    (5, 'CS4420', 'Lesson 5', 'Content 5'),
    (6, 'CS3360', 'Lesson 1', 'Content 1'),
    (7, 'CS3360', 'Lesson 2', 'Content 2'),
    (8, 'CS3360', 'Lesson 3', 'Content 3'),
    (9, 'CS3360', 'Lesson 4', 'Content 4'),
    (10, 'CS3360', 'Lesson 5', 'Content 5'),
    (11, 'CS3365', 'Lesson 1', 'Content 1'),
    (12, 'CS3365', 'Lesson 2', 'Content 2'),
    (13, 'CS3365', 'Lesson 3', 'Content 3'),
    (14, 'CS3365', 'Lesson 4', 'Content 4'),
    (15, 'CS3365', 'Lesson 5', 'Content 5'),
    (16, 'CS2250', 'Lesson 1', 'Content 1'),
	(17, 'CS2250', 'Lesson 2', 'Content 2'),
	(18, 'CS2250', 'Lesson 3', 'Content 3'),
	(19, 'CS2250', 'Lesson 4', 'Content 4'),
	(20, 'CS2250', 'Lesson 5', 'Content 5'),
	(21, 'CS2255', 'Lesson 1', 'Content 1'),
	(22, 'CS2255', 'Lesson 2', 'Content 2'),
	(23, 'CS2255', 'Lesson 3', 'Content 3'),
	(24, 'CS2255', 'Lesson 4', 'Content 4'),
	(25, 'CS2255', 'Lesson 5', 'Content 5'),
	(26, 'MTH1125', 'Lesson 1', 'Content 1'),
	(27, 'MTH1125', 'Lesson 2', 'Content 2'),
	(28, 'MTH1125', 'Lesson 3', 'Content 3'),
	(29, 'MTH1125', 'Lesson 4', 'Content 4'),
	(30, 'MTH1125', 'Lesson 5', 'Content 5'),
	(31, 'MTH1126', 'Lesson 1', 'Content 1'),
	(32, 'MTH1126', 'Lesson 2', 'Content 2'),
	(33, 'MTH1126', 'Lesson 3', 'Content 3'),
	(34, 'MTH1126', 'Lesson 4', 'Content 4'),
	(35, 'MTH1126', 'Lesson 5', 'Content 5'),
	(36, 'MTH2215', 'Lesson 1', 'Content 1'),
	(37, 'MTH2215', 'Lesson 2', 'Content 2'),
	(38, 'MTH2215', 'Lesson 3', 'Content 3'),
	(39, 'MTH2215', 'Lesson 4', 'Content 4'),
	(40, 'MTH2215', 'Lesson 5', 'Content 5'),
	(41, 'ECO2251', 'Lesson 1', 'Content 1'),
	(42, 'ECO2251', 'Lesson 2', 'Content 2'),
	(43, 'ECO2251', 'Lesson 3', 'Content 3'),
	(44, 'ECO2251', 'Lesson 4', 'Content 4'),
	(45, 'ECO2251', 'Lesson 5', 'Content 5'),
	(46, 'ECO2252', 'Lesson 1', 'Content 1'),
	(47, 'ECO2252', 'Lesson 2', 'Content 2'),
	(48, 'ECO2252', 'Lesson 3', 'Content 3'),
	(49, 'ECO2252', 'Lesson 4', 'Content 4'),
	(50, 'ECO2252', 'Lesson 5', 'Content 5');
    
    
    
-- 	-------------------------------------------	ASSIGNMENT -----------------------------------------------------
-- Each session has exactly 2 assignments
INSERT INTO assignment(assignment_id, session_id, description)
VALUES
	-- For CS4420
	(1, 1, 'description 1'),
	(2, 1, 'description 2'),
	(3, 2, 'description 1'),
	(4, 2, 'description 2'),
    (5, 3, 'description 1'),
	(6, 3, 'description 2'),
    (7, 4, 'description 1'),
	(8, 4, 'description 2'),
    (9, 5, 'description 1'),
	(10, 5, 'description 2'),

	-- For CS3360
	(11, 6, 'description 1'),
	(12, 6, 'description 2'),
	(13, 7, 'description 1'),
	(14, 7, 'description 2'),
    (15, 8, 'description 1'),
	(16, 8, 'description 2'),
    (17, 9, 'description 1'),
	(18, 9, 'description 2'),
    (19, 10, 'description 1'),
	(20, 10, 'description 2'),

    -- For CS3365
    (21, 11, 'description 1'),
    (22, 11, 'description 2'),
    (23, 12, 'description 1'),
    (24, 12, 'description 2'),
    (25, 13, 'description 1'),
    (26, 13, 'description 2'),
    (27, 14, 'description 1'),
    (28, 14, 'description 2'),
    (29, 15, 'description 1'),
    (30, 15, 'description 2'),

    -- For CS2250
    (31, 16, 'description 1'),
    (32, 16, 'description 2'),
    (33, 17, 'description 1'),
    (34, 17, 'description 2'),
    (35, 18, 'description 1'),
    (36, 18, 'description 2'),
    (37, 19, 'description 1'),
    (38, 19, 'description 2'),
    (39, 20, 'description 1'),
    (40, 20, 'description 2'),

    -- For CS2255
    (41, 21, 'description 1'),
    (42, 21, 'description 2'),
    (43, 22, 'description 1'),
    (44, 22, 'description 2'),
    (45, 23, 'description 1'),
    (46, 23, 'description 2'),
    (47, 24, 'description 1'),
    (48, 24, 'description 2'),
    (49, 25, 'description 1'),
    (50, 25, 'description 2'),

    -- For MTH1125
    (51, 26, 'description 1'),
    (52, 26, 'description 2'),
    (53, 27, 'description 1'),
    (54, 27, 'description 2'),
    (55, 28, 'description 1'),
    (56, 28, 'description 2'),
    (57, 29, 'description 1'),
    (58, 29, 'description 2'),
    (59, 30, 'description 1'),
    (60, 30, 'description 2'),

    -- For MTH1126
    (61, 31, 'description 1'),
    (62, 31, 'description 2'),
    (63, 32, 'description 1'),
    (64, 32, 'description 2'),
    (65, 33, 'description 1'),
    (66, 33, 'description 2'),
    (67, 34, 'description 1'),
    (68, 34, 'description 2'),
    (69, 35, 'description 1'),
    (70, 35, 'description 2'),

    -- For MTH2215
    (71, 36, 'description 1'),
    (72, 36, 'description 2'),
    (73, 37, 'description 1'),
    (74, 37, 'description 2'),
    (75, 38, 'description 1'),
    (76, 38, 'description 2'),
    (77, 39, 'description 1'),
    (78, 39, 'description 2'),
    (79, 40, 'description 1'),
    (80, 40, 'description 2'),

    -- For ECO2251
    (81, 41, 'description 1'),
    (82, 41, 'description 2'),
    (83, 42, 'description 1'),
    (84, 42, 'description 2'),
    (85, 43, 'description 1'),
    (86, 43, 'description 2'),
    (87, 44, 'description 1'),
    (88, 44, 'description 2'),
    (89, 45, 'description 1'),
    (90, 45, 'description 2'),

    -- For ECO2252
    (91, 46, 'description 1'),
    (92, 46, 'description 2'),
    (93, 47, 'description 1'),
    (94, 47, 'description 2'),
    (95, 48, 'description 1'),
    (96, 48, 'description 2'),
    (97, 49, 'description 1'),
    (98, 49, 'description 2'),
    (99, 50, 'description 1'),
    (100, 50, 'description 2');

    
    
-- 	-------------------------------------------	TEACHES -----------------------------------------------------
-- 	There are 9 instructors in total, 
--  but there are only first 6 instructors who teach at least 1 course	
insert into teaches(instructor_id, course_id)
	values
    (1, 'CS4420'),
    (1, 'CS3360'),
    (2, 'CS3365'),
    (3, 'CS2250'),
    (3, 'CS2255'),
    (4, 'MTH1125'),
    (4, 'MTH1126'),
    (4, 'MTH2215'),
    (5, 'MTH1125'),
    (5, 'MTH1126'),
    (5, 'MTH2215'),
    (6, 'ECO2251'),
    (6, 'ECO2252');



-- 	-------------------------------------------	INS_ATTEND -----------------------------------------------------
-- 	Record ATTENDENCE of Instructors
insert into ins_attend(session_id, instructor_id, check_attend)
	values
    -- For Nguyen Dinh Van (id = 1)
    (1, 1, true),
    (2, 1, false),
    (3, 1, true),
    (4, 1, false),
    (5, 1, true),
    
    (6, 1, true),
    (7, 1, false),
    (8, 1, true),
    (9, 1, false),
    (10, 1, true),
    
    -- For Ngo Lam Trung (id = 2)
    (11, 2, true),
    (12, 2, false),
    (13, 2, true),
    (14, 2, false),
    (15, 2, true),
    
    -- For Cao Tuan Dung (id = 3)
    (16, 3, true),
    (17, 3, false),
    (18, 3, true),
    (19, 3, false),
    (20, 3, true),
    
    (21, 3, true),
    (22, 3, false),
    (23, 3, true),
    (24, 3, false),
    (25, 3, true),
    
    -- For Ho Minh Toan (id = 4)
    (26, 4, true),
    (27, 4, false),
    (28, 4, true),
    (29, 4, false),
    (30, 4, true),
    
    (31, 4, true),
    (32, 4, false),
    (33, 4, true),
    (34, 4, false),
    (35, 4, true),
    
    (36, 4, true),
    (37, 4, false),
    (38, 4, true),
    (39, 4, false),
    (40, 4, true),
    
    -- For Vu The Khoi (id = 5)
    (26, 5, true),
    (27, 5, false),
    (28, 5, true),
    (29, 5, false),
    (30, 5, true),
    
    (31, 5, true),
    (32, 5, false),
    (33, 5, true),
    (34, 5, false),
    (35, 5, true),
    
    (36, 5, true),
    (37, 5, false),
    (38, 5, true),
    (39, 5, false),
    (40, 5, true),
    
    -- For Nguyen Tai Vuong (id = 6)
    (41, 6, true),
    (42, 6, false),
    (43, 6, true),
    (44, 6, false),
    (45, 6, true),
    
    (46, 6, true),
    (47, 6, false),
    (48, 6, true),
    (49, 6, false),
    (50, 6, true);
    
   
   
-- ------------------------------------------- REGISTERS -----------------------------------------------------
-- Only first 5 students (id = 1,2,3,4,5) registers all course. 
-- 5 remaining students register no course at all
insert into registers(course_id, student_id)
	values
    ('CS4420', 1),
    ('CS4420', 2),
    ('CS4420', 3),
    ('CS4420', 4),
    ('CS4420', 5),
    ('CS3360', 1),
    ('CS3360', 2),
    ('CS3360', 3),
    ('CS3360', 4),
    ('CS3360', 5),
    ('CS3365', 1),
    ('CS3365', 2),
    ('CS3365', 3),
    ('CS3365', 4),
    ('CS3365', 5),
    ('CS2250', 1),
    ('CS2250', 2),
    ('CS2250', 3),
    ('CS2250', 4),
    ('CS2250', 5),
    ('CS2255', 1),
    ('CS2255', 2),
    ('CS2255', 3),
    ('CS2255', 4),
    ('CS2255', 5),
    ('MTH1125', 1),
    ('MTH1125', 2),
    ('MTH1125', 3),
    ('MTH1125', 4),
    ('MTH1125', 5),
    ('MTH1126', 1),
    ('MTH1126', 2),
    ('MTH1126', 3),
    ('MTH1126', 4),
    ('MTH1126', 5),
    ('MTH2215', 1),
    ('MTH2215', 2),
    ('MTH2215', 3),
    ('MTH2215', 4),
    ('MTH2215', 5),
    ('ECO2251', 1),
    ('ECO2251', 2),
    ('ECO2251', 3),
    ('ECO2251', 4),
    ('ECO2251', 5),
    ('ECO2252', 1),
    ('ECO2252', 2),
    ('ECO2252', 3),
    ('ECO2252', 4),
    ('ECO2252', 5);  
   
   
   
-- ------------------------------------------- STU_ATTEND -----------------------------------------------------
-- For each session, there are exactly 3 students (id = 1,2,3) attending 
insert into stu_attend(session_id, student_id, check_attend)
	values
    -- For 5 sessions belonging CS4420
    (1, 1, true),
    (1, 2, true),
    (1, 3, true),
    (1, 4, false),
    (1, 5, false),
    
    (2, 1, true),
    (2, 2, true),
    (2, 3, true),
    (2, 4, false),
    (2, 5, false),
    
    (3, 1, true),
    (3, 2, true),
    (3, 3, true),
    (3, 4, false),
    (3, 5, false),
    
    (4, 1, true),
    (4, 2, true),
    (4, 3, true),
    (4, 4, false),
    (4, 5, false),
    
    (5, 1, true),
    (5, 2, true),
    (5, 3, true),
    (5, 4, false),
    (5, 5, false),
    
    -- For 5 sessions belonging CS3360
    (6, 1, true),
    (6, 2, true),
    (6, 3, true),
    (6, 4, false),
    (6, 5, false),
    
    (7, 1, true),
    (7, 2, true),
    (7, 3, true),
    (7, 4, false),
    (7, 5, false),
    
    (8, 1, true),
    (8, 2, true),
    (8, 3, true),
    (8, 4, false),
    (8, 5, false),
    
    (9, 1, true),
    (9, 2, true),
    (9, 3, true),
    (9, 4, false),
    (9, 5, false),
    
    (10, 1, true),
    (10, 2, true),
    (10, 3, true),
    (10, 4, false),
    (10, 5, false),
    
    -- For 5 sessions belonging CS3365
    (11, 1, true),
    (11, 2, true),
    (11, 3, true),
    (11, 4, false),
    (11, 5, false),

    (12, 1, true),
    (12, 2, true),
    (12, 3, true),
    (12, 4, false),
    (12, 5, false),

    (13, 1, true),
    (13, 2, true),
    (13, 3, true),
    (13, 4, false),
    (13, 5, false),

    (14, 1, true),
    (14, 2, true),
    (14, 3, true),
    (14, 4, false),
    (14, 5, false),

    (15, 1, true),
    (15, 2, true),
    (15, 3, true),
    (15, 4, false),
    (15, 5, false),

    -- For 5 sessions belonging CS2250
    (16, 1, true),
    (16, 2, true),
    (16, 3, true),
    (16, 4, false),
    (16, 5, false),

    (17, 1, true),
    (17, 2, true),
    (17, 3, true),
    (17, 4, false),
    (17, 5, false),

    (18, 1, true),
    (18, 2, true),
    (18, 3, true),
    (18, 4, false),
    (18, 5, false),

    (19, 1, true),
    (19, 2, true),
    (19, 3, true),
    (19, 4, false),
    (19, 5, false),

    (20, 1, true),
    (20, 2, true),
    (20, 3, true),
    (20, 4, false),
    (20, 5, false),

    -- For 5 sessions belonging CS2255
    (21, 1, true),
    (21, 2, true),
    (21, 3, true),
    (21, 4, false),
    (21, 5, false),

    (22, 1, true),
    (22, 2, true),
    (22, 3, true),
    (22, 4, false),
    (22, 5, false),

    (23, 1, true),
    (23, 2, true),
    (23, 3, true),
    (23, 4, false),
    (23, 5, false),

    (24, 1, true),
    (24, 2, true),
    (24, 3, true),
    (24, 4, false),
    (24, 5, false),

    (25, 1, true),
    (25, 2, true),
    (25, 3, true),
    (25, 4, false),
    (25, 5, false),

    -- For 5 sessions belonging MTH1125
    (26, 1, true),
    (26, 2, true),
    (26, 3, true),
    (26, 4, false),
    (26, 5, false),

    (27, 1, true),
    (27, 2, true),
    (27, 3, true),
    (27, 4, false),
    (27, 5, false),

    (28, 1, true),
    (28, 2, true),
    (28, 3, true),
    (28, 4, false),
    (28, 5, false),

    (29, 1, true),
    (29, 2, true),
    (29, 3, true),
    (29, 4, false),
    (29, 5, false),

    (30, 1, true),
    (30, 2, true),
    (30, 3, true),
    (30, 4, false),
    (30, 5, false),

    -- For 5 sessions belonging MTH1126
    (31, 1, true),
    (31, 2, true),
    (31, 3, true),
    (31, 4, false),
    (31, 5, false),

    (32, 1, true),
    (32, 2, true),
    (32, 3, true),
    (32, 4, false),
    (32, 5, false),

    (33, 1, true),
    (33, 2, true),
    (33, 3, true),
    (33, 4, false),
    (33, 5, false),

    (34, 1, true),
    (34, 2, true),
    (34, 3, true),
    (34, 4, false),
    (34, 5, false),

    (35, 1, true),
    (35, 2, true),
    (35, 3, true),
    (35, 4, false),
    (35, 5, false),

    -- For 5 sessions belonging MTH2215
    (36, 1, true),
    (36, 2, true),
    (36, 3, true),
    (36, 4, false),
    (36, 5, false),

    (37, 1, true),
    (37, 2, true),
    (37, 3, true),
    (37, 4, false),
    (37, 5, false),

    (38, 1, true),
    (38, 2, true),
    (38, 3, true),
    (38, 4, false),
    (38, 5, false),

    (39, 1, true),
    (39, 2, true),
    (39, 3, true),
    (39, 4, false),
    (39, 5, false),

    (40, 1, true),
    (40, 2, true),
    (40, 3, true),
    (40, 4, false),
    (40, 5, false),

    -- For 5 sessions belonging ECO2251
    (41, 1, true),
    (41, 2, true),
    (41, 3, true),
    (41, 4, false),
    (41, 5, false),

    (42, 1, true),
    (42, 2, true),
    (42, 3, true),
    (42, 4, false),
    (42, 5, false),

    (43, 1, true),
    (43, 2, true),
    (43, 3, true),
    (43, 4, false),
    (43, 5, false),

    (44, 1, true),
    (44, 2, true),
    (44, 3, true),
    (44, 4, false),
    (44, 5, false),

    (45, 1, true),
    (45, 2, true),
    (45, 3, true),
    (45, 4, false),
    (45, 5, false),

    -- For 5 sessions belonging ECO2252
    (46, 1, true),
    (46, 2, true),
    (46, 3, true),
    (46, 4, false),
    (46, 5, false),

    (47, 1, true),
    (47, 2, true),
    (47, 3, true),
    (47, 4, false),
    (47, 5, false),

    (48, 1, true),
    (48, 2, true),
    (48, 3, true),
    (48, 4, false),
    (48, 5, false),

    (49, 1, true),
    (49, 2, true),
    (49, 3, true),
    (49, 4, false),
    (49, 5, false),

    (50, 1, true),
    (50, 2, true),
    (50, 3, true),
    (50, 4, false),
    (50, 5, false);

    

-- 	-------------------------------------------	SUBMITS -----------------------------------------------------
-- There is only 1 student (id = 1) 
-- who submits fullly assignments for every session.
-- The other students miss all assignments.
insert into submits(assignment_id, student_id, solution, score, feedback)
	values
    -- ------------------------- student id = 1 -----------------------------
    -- For CS4420
	(1, 1, 'solution 1', 9.0, 'feedback 1'),
	(2, 1, 'solution 2', 9.0, 'feedback 2'),
	(3, 1, 'solution 1', 9.0, 'feedback 1'),
	(4, 1, 'solution 2', 9.0, 'feedback 2'),
    (5, 1, 'solution 1', 9.0, 'feedback 1'),
	(6, 1, 'solution 2', 9.0, 'feedback 2'),
    (7, 1, 'solution 1', 9.0, 'feedback 1'),
	(8, 1, 'solution 2', 9.0, 'feedback 2'),
    (9, 1, 'solution 1', 9.0, 'feedback 1'),
	(10, 1, 'solution 2', 9.0, 'feedback 2'),
    
    -- For CS3360
    (11, 1, 'solution 1', 9.0, 'feedback 1'),
    (12, 1, 'solution 2', 9.0, 'feedback 2'),
    (13, 1, 'solution 1', 9.0, 'feedback 1'),
    (14, 1, 'solution 2', 9.0, 'feedback 2'),
    (15, 1, 'solution 1', 9.0, 'feedback 1'),
    (16, 1, 'solution 2', 9.0, 'feedback 2'),
    (17, 1, 'solution 1', 9.0, 'feedback 1'),
    (18, 1, 'solution 2', 9.0, 'feedback 2'),
    (19, 1, 'solution 1', 9.0, 'feedback 1'),
    (20, 1, 'solution 2', 9.0, 'feedback 2'),

    -- For CS3365
    (21, 1, 'solution 1', 9.0, 'feedback 1'),
    (22, 1, 'solution 2', 9.0, 'feedback 2'),
    (23, 1, 'solution 1', 9.0, 'feedback 1'),
    (24, 1, 'solution 2', 9.0, 'feedback 2'),
    (25, 1, 'solution 1', 9.0, 'feedback 1'),
    (26, 1, 'solution 2', 9.0, 'feedback 2'),
    (27, 1, 'solution 1', 9.0, 'feedback 1'),
    (28, 1, 'solution 2', 9.0, 'feedback 2'),
    (29, 1, 'solution 1', 9.0, 'feedback 1'),
    (30, 1, 'solution 2', 9.0, 'feedback 2'),

    -- For CS2250
    (31, 1, 'solution 1', 9.0, 'feedback 1'),
    (32, 1, 'solution 2', 9.0, 'feedback 2'),
    (33, 1, 'solution 1', 9.0, 'feedback 1'),
    (34, 1, 'solution 2', 9.0, 'feedback 2'),
    (35, 1, 'solution 1', 9.0, 'feedback 1'),
    (36, 1, 'solution 2', 9.0, 'feedback 2'),
    (37, 1, 'solution 1', 9.0, 'feedback 1'),
    (38, 1, 'solution 2', 9.0, 'feedback 2'),
    (39, 1, 'solution 1', 9.0, 'feedback 1'),
    (40, 1, 'solution 2', 9.0, 'feedback 2'),

    -- For CS2255
    (41, 1, 'solution 1', 9.0, 'feedback 1'),
    (42, 1, 'solution 2', 9.0, 'feedback 2'),
    (43, 1, 'solution 1', 9.0, 'feedback 1'),
    (44, 1, 'solution 2', 9.0, 'feedback 2'),
    (45, 1, 'solution 1', 9.0, 'feedback 1'),
    (46, 1, 'solution 2', 9.0, 'feedback 2'),
    (47, 1, 'solution 1', 9.0, 'feedback 1'),
    (48, 1, 'solution 2', 9.0, 'feedback 2'),
    (49, 1, 'solution 1', 9.0, 'feedback 1'),
    (50, 1, 'solution 2', 9.0, 'feedback 2'),

    -- For MTH1125
    (51, 1, 'solution 1', 9.0, 'feedback 1'),
    (52, 1, 'solution 2', 9.0, 'feedback 2'),
    (53, 1, 'solution 1', 9.0, 'feedback 1'),
    (54, 1, 'solution 2', 9.0, 'feedback 2'),
    (55, 1, 'solution 1', 9.0, 'feedback 1'),
    (56, 1, 'solution 2', 9.0, 'feedback 2'),
    (57, 1, 'solution 1', 9.0, 'feedback 1'),
    (58, 1, 'solution 2', 9.0, 'feedback 2'),
    (59, 1, 'solution 1', 9.0, 'feedback 1'),
    (60, 1, 'solution 2', 9.0, 'feedback 2'),

    -- For MTH1126
    (61, 1, 'solution 1', 9.0, 'feedback 1'),
    (62, 1, 'solution 2', 9.0, 'feedback 2'),
    (63, 1, 'solution 1', 9.0, 'feedback 1'),
    (64, 1, 'solution 2', 9.0, 'feedback 2'),
    (65, 1, 'solution 1', 9.0, 'feedback 1'),
    (66, 1, 'solution 2', 9.0, 'feedback 2'),
    (67, 1, 'solution 1', 9.0, 'feedback 1'),
    (68, 1, 'solution 2', 9.0, 'feedback 2'),
    (69, 1, 'solution 1', 9.0, 'feedback 1'),
    (70, 1, 'solution 2', 9.0, 'feedback 2'),

    -- For MTH2215
    (71, 1, 'solution 1', 9.0, 'feedback 1'),
    (72, 1, 'solution 2', 9.0, 'feedback 2'),
    (73, 1, 'solution 1', 9.0, 'feedback 1'),
    (74, 1, 'solution 2', 9.0, 'feedback 2'),
    (75, 1, 'solution 1', 9.0, 'feedback 1'),
    (76, 1, 'solution 2', 9.0, 'feedback 2'),
    (77, 1, 'solution 1', 9.0, 'feedback 1'),
    (78, 1, 'solution 2', 9.0, 'feedback 2'),
    (79, 1, 'solution 1', 9.0, 'feedback 1'),
    (80, 1, 'solution 2', 9.0, 'feedback 2'),

    -- For ECO2251
    (81, 1, 'solution 1', 9.0, 'feedback 1'),
    (82, 1, 'solution 2', 9.0, 'feedback 2'),
    (83, 1, 'solution 1', 9.0, 'feedback 1'),
    (84, 1, 'solution 2', 9.0, 'feedback 2'),
    (85, 1, 'solution 1', 9.0, 'feedback 1'),
    (86, 1, 'solution 2', 9.0, 'feedback 2'),
    (87, 1, 'solution 1', 9.0, 'feedback 1'),
    (88, 1, 'solution 2', 9.0, 'feedback 2'),
    (89, 1, 'solution 1', 9.0, 'feedback 1'),
    (90, 1, 'solution 2', 9.0, 'feedback 2'),

    -- For ECO2252
    (91, 1, 'solution 1', 9.0, 'feedback 1'),
    (92, 1, 'solution 2', 9.0, 'feedback 2'),
    (93, 1, 'solution 1', 9.0, 'feedback 1'),
    (94, 1, 'solution 2', 9.0, 'feedback 2'),
    (95, 1, 'solution 1', 9.0, 'feedback 1'),
    (96, 1, 'solution 2', 9.0, 'feedback 2'),
    (97, 1, 'solution 1', 9.0, 'feedback 1'),
    (98, 1, 'solution 2', 9.0, 'feedback 2'),
    (99, 1, 'solution 1', 9.0, 'feedback 1'),
    (100, 1, 'solution 2', 9.0, 'feedback 2'),
    
    -- ------------------------- student id = 2 -----------------------------
    -- For CS4420
    (1, 2, NULL, 0.0, NULL),
    (2, 2, NULL, 0.0, NULL),
    (3, 2, NULL, 0.0, NULL),
    (4, 2, NULL, 0.0, NULL),
    (5, 2, NULL, 0.0, NULL),
    (6, 2, NULL, 0.0, NULL),
    (7, 2, NULL, 0.0, NULL),
    (8, 2, NULL, 0.0, NULL),
    (9, 2, NULL, 0.0, NULL),
    (10, 2, NULL, 0.0, NULL),

    -- For CS3360
    (11, 2, NULL, 0.0, NULL),
    (12, 2, NULL, 0.0, NULL),
    (13, 2, NULL, 0.0, NULL),
    (14, 2, NULL, 0.0, NULL),
    (15, 2, NULL, 0.0, NULL),
    (16, 2, NULL, 0.0, NULL),
    (17, 2, NULL, 0.0, NULL),
    (18, 2, NULL, 0.0, NULL),
    (19, 2, NULL, 0.0, NULL),
    (20, 2, NULL, 0.0, NULL),

    -- For CS3365
    (21, 2, NULL, 0.0, NULL),
    (22, 2, NULL, 0.0, NULL),
    (23, 2, NULL, 0.0, NULL),
    (24, 2, NULL, 0.0, NULL),
    (25, 2, NULL, 0.0, NULL),
    (26, 2, NULL, 0.0, NULL),
    (27, 2, NULL, 0.0, NULL),
    (28, 2, NULL, 0.0, NULL),
    (29, 2, NULL, 0.0, NULL),
    (30, 2, NULL, 0.0, NULL),

    -- For CS2250
    (31, 2, NULL, 0.0, NULL),
    (32, 2, NULL, 0.0, NULL),
    (33, 2, NULL, 0.0, NULL),
    (34, 2, NULL, 0.0, NULL),
    (35, 2, NULL, 0.0, NULL),
    (36, 2, NULL, 0.0, NULL),
    (37, 2, NULL, 0.0, NULL),
    (38, 2, NULL, 0.0, NULL),
    (39, 2, NULL, 0.0, NULL),
    (40, 2, NULL, 0.0, NULL),

    -- For CS2255
    (41, 2, NULL, 0.0, NULL),
    (42, 2, NULL, 0.0, NULL),
    (43, 2, NULL, 0.0, NULL),
    (44, 2, NULL, 0.0, NULL),
    (45, 2, NULL, 0.0, NULL),
    (46, 2, NULL, 0.0, NULL),
    (47, 2, NULL, 0.0, NULL),
    (48, 2, NULL, 0.0, NULL),
    (49, 2, NULL, 0.0, NULL),
    (50, 2, NULL, 0.0, NULL),

    -- For MTH1125
    (51, 2, NULL, 0.0, NULL),
    (52, 2, NULL, 0.0, NULL),
    (53, 2, NULL, 0.0, NULL),
    (54, 2, NULL, 0.0, NULL),
    (55, 2, NULL, 0.0, NULL),
    (56, 2, NULL, 0.0, NULL),
    (57, 2, NULL, 0.0, NULL),
    (58, 2, NULL, 0.0, NULL),
    (59, 2, NULL, 0.0, NULL),
    (60, 2, NULL, 0.0, NULL),

    -- For MTH1126
    (61, 2, NULL, 0.0, NULL),
    (62, 2, NULL, 0.0, NULL),
    (63, 2, NULL, 0.0, NULL),
    (64, 2, NULL, 0.0, NULL),
    (65, 2, NULL, 0.0, NULL),
    (66, 2, NULL, 0.0, NULL),
    (67, 2, NULL, 0.0, NULL),
    (68, 2, NULL, 0.0, NULL),
    (69, 2, NULL, 0.0, NULL),
    (70, 2, NULL, 0.0, NULL),

    -- For MTH2215
    (71, 2, NULL, 0.0, NULL),
    (72, 2, NULL, 0.0, NULL),
    (73, 2, NULL, 0.0, NULL),
    (74, 2, NULL, 0.0, NULL),
    (75, 2, NULL, 0.0, NULL),
    (76, 2, NULL, 0.0, NULL),
    (77, 2, NULL, 0.0, NULL),
    (78, 2, NULL, 0.0, NULL),
    (79, 2, NULL, 0.0, NULL),
    (80, 2, NULL, 0.0, NULL),

    -- For ECO2251
    (81, 2, NULL, 0.0, NULL),
    (82, 2, NULL, 0.0, NULL),
    (83, 2, NULL, 0.0, NULL),
    (84, 2, NULL, 0.0, NULL),
    (85, 2, NULL, 0.0, NULL),
    (86, 2, NULL, 0.0, NULL),
    (87, 2, NULL, 0.0, NULL),
    (88, 2, NULL, 0.0, NULL),
    (89, 2, NULL, 0.0, NULL),
    (90, 2, NULL, 0.0, NULL),

    -- For ECO2252
    (91, 2, NULL, 0.0, NULL),
    (92, 2, NULL, 0.0, NULL),
    (93, 2, NULL, 0.0, NULL),
    (94, 2, NULL, 0.0, NULL),
    (95, 2, NULL, 0.0, NULL),
    (96, 2, NULL, 0.0, NULL),
    (97, 2, NULL, 0.0, NULL),
    (98, 2, NULL, 0.0, NULL),
    (99, 2, NULL, 0.0, NULL),
    (100, 2, NULL, 0.0, NULL),

	-- ------------------------- student id = 3 -----------------------------
    -- For CS4420
    (1, 3, NULL, 0.0, NULL),
    (2, 3, NULL, 0.0, NULL),
    (3, 3, NULL, 0.0, NULL),
    (4, 3, NULL, 0.0, NULL),
    (5, 3, NULL, 0.0, NULL),
    (6, 3, NULL, 0.0, NULL),
    (7, 3, NULL, 0.0, NULL),
    (8, 3, NULL, 0.0, NULL),
    (9, 3, NULL, 0.0, NULL),
    (10, 3, NULL, 0.0, NULL),

    -- For CS3360
    (11, 3, NULL, 0.0, NULL),
    (12, 3, NULL, 0.0, NULL),
    (13, 3, NULL, 0.0, NULL),
    (14, 3, NULL, 0.0, NULL),
    (15, 3, NULL, 0.0, NULL),
    (16, 3, NULL, 0.0, NULL),
    (17, 3, NULL, 0.0, NULL),
    (18, 3, NULL, 0.0, NULL),
    (19, 3, NULL, 0.0, NULL),
    (20, 3, NULL, 0.0, NULL),

    -- For CS3365
    (21, 3, NULL, 0.0, NULL),
    (22, 3, NULL, 0.0, NULL),
    (23, 3, NULL, 0.0, NULL),
    (24, 3, NULL, 0.0, NULL),
    (25, 3, NULL, 0.0, NULL),
    (26, 3, NULL, 0.0, NULL),
    (27, 3, NULL, 0.0, NULL),
    (28, 3, NULL, 0.0, NULL),
    (29, 3, NULL, 0.0, NULL),
    (30, 3, NULL, 0.0, NULL),

    -- For CS2250
    (31, 3, NULL, 0.0, NULL),
    (32, 3, NULL, 0.0, NULL),
    (33, 3, NULL, 0.0, NULL),
    (34, 3, NULL, 0.0, NULL),
    (35, 3, NULL, 0.0, NULL),
    (36, 3, NULL, 0.0, NULL),
    (37, 3, NULL, 0.0, NULL),
    (38, 3, NULL, 0.0, NULL),
    (39, 3, NULL, 0.0, NULL),
    (40, 3, NULL, 0.0, NULL),

    -- For CS2255
    (41, 3, NULL, 0.0, NULL),
    (42, 3, NULL, 0.0, NULL),
    (43, 3, NULL, 0.0, NULL),
    (44, 3, NULL, 0.0, NULL),
    (45, 3, NULL, 0.0, NULL),
    (46, 3, NULL, 0.0, NULL),
    (47, 3, NULL, 0.0, NULL),
    (48, 3, NULL, 0.0, NULL),
    (49, 3, NULL, 0.0, NULL),
    (50, 3, NULL, 0.0, NULL),

    -- For MTH1125
    (51, 3, NULL, 0.0, NULL),
    (52, 3, NULL, 0.0, NULL),
    (53, 3, NULL, 0.0, NULL),
    (54, 3, NULL, 0.0, NULL),
    (55, 3, NULL, 0.0, NULL),
    (56, 3, NULL, 0.0, NULL),
    (57, 3, NULL, 0.0, NULL),
    (58, 3, NULL, 0.0, NULL),
    (59, 3, NULL, 0.0, NULL),
    (60, 3, NULL, 0.0, NULL),

    -- For MTH1126
    (61, 3, NULL, 0.0, NULL),
    (62, 3, NULL, 0.0, NULL),
    (63, 3, NULL, 0.0, NULL),
    (64, 3, NULL, 0.0, NULL),
    (65, 3, NULL, 0.0, NULL),
    (66, 3, NULL, 0.0, NULL),
    (67, 3, NULL, 0.0, NULL),
    (68, 3, NULL, 0.0, NULL),
    (69, 3, NULL, 0.0, NULL),
    (70, 3, NULL, 0.0, NULL),

    -- For MTH2215
    (71, 3, NULL, 0.0, NULL),
    (72, 3, NULL, 0.0, NULL),
    (73, 3, NULL, 0.0, NULL),
    (74, 3, NULL, 0.0, NULL),
    (75, 3, NULL, 0.0, NULL),
    (76, 3, NULL, 0.0, NULL),
    (77, 3, NULL, 0.0, NULL),
    (78, 3, NULL, 0.0, NULL),
    (79, 3, NULL, 0.0, NULL),
    (80, 3, NULL, 0.0, NULL),

    -- For ECO2251
    (81, 3, NULL, 0.0, NULL),
    (82, 3, NULL, 0.0, NULL),
    (83, 3, NULL, 0.0, NULL),
    (84, 3, NULL, 0.0, NULL),
    (85, 3, NULL, 0.0, NULL),
    (86, 3, NULL, 0.0, NULL),
    (87, 3, NULL, 0.0, NULL),
    (88, 3, NULL, 0.0, NULL),
    (89, 3, NULL, 0.0, NULL),
    (90, 3, NULL, 0.0, NULL),

    -- For ECO2252
    (91, 3, NULL, 0.0, NULL),
    (92, 3, NULL, 0.0, NULL),
    (93, 3, NULL, 0.0, NULL),
    (94, 3, NULL, 0.0, NULL),
    (95, 3, NULL, 0.0, NULL),
    (96, 3, NULL, 0.0, NULL),
    (97, 3, NULL, 0.0, NULL),
    (98, 3, NULL, 0.0, NULL),
    (99, 3, NULL, 0.0, NULL),
    (100, 3, NULL, 0.0, NULL),

	-- ------------------------- student id = 4 -----------------------------
    -- For CS4420
    (1, 4, NULL, 0.0, NULL),
    (2, 4, NULL, 0.0, NULL),
    (3, 4, NULL, 0.0, NULL),
    (4, 4, NULL, 0.0, NULL),
    (5, 4, NULL, 0.0, NULL),
    (6, 4, NULL, 0.0, NULL),
    (7, 4, NULL, 0.0, NULL),
    (8, 4, NULL, 0.0, NULL),
    (9, 4, NULL, 0.0, NULL),
    (10, 4, NULL, 0.0, NULL),

    -- For CS3360
    (11, 4, NULL, 0.0, NULL),
    (12, 4, NULL, 0.0, NULL),
    (13, 4, NULL, 0.0, NULL),
    (14, 4, NULL, 0.0, NULL),
    (15, 4, NULL, 0.0, NULL),
    (16, 4, NULL, 0.0, NULL),
    (17, 4, NULL, 0.0, NULL),
    (18, 4, NULL, 0.0, NULL),
    (19, 4, NULL, 0.0, NULL),
    (20, 4, NULL, 0.0, NULL),

    -- For CS3365
    (21, 4, NULL, 0.0, NULL),
    (22, 4, NULL, 0.0, NULL),
    (23, 4, NULL, 0.0, NULL),
    (24, 4, NULL, 0.0, NULL),
    (25, 4, NULL, 0.0, NULL),
    (26, 4, NULL, 0.0, NULL),
    (27, 4, NULL, 0.0, NULL),
    (28, 4, NULL, 0.0, NULL),
    (29, 4, NULL, 0.0, NULL),
    (30, 4, NULL, 0.0, NULL),

    -- For CS2250
    (31, 4, NULL, 0.0, NULL),
    (32, 4, NULL, 0.0, NULL),
    (33, 4, NULL, 0.0, NULL),
    (34, 4, NULL, 0.0, NULL),
    (35, 4, NULL, 0.0, NULL),
    (36, 4, NULL, 0.0, NULL),
    (37, 4, NULL, 0.0, NULL),
    (38, 4, NULL, 0.0, NULL),
    (39, 4, NULL, 0.0, NULL),
    (40, 4, NULL, 0.0, NULL),

    -- For CS2255
    (41, 4, NULL, 0.0, NULL),
    (42, 4, NULL, 0.0, NULL),
    (43, 4, NULL, 0.0, NULL),
    (44, 4, NULL, 0.0, NULL),
    (45, 4, NULL, 0.0, NULL),
    (46, 4, NULL, 0.0, NULL),
    (47, 4, NULL, 0.0, NULL),
    (48, 4, NULL, 0.0, NULL),
    (49, 4, NULL, 0.0, NULL),
    (50, 4, NULL, 0.0, NULL),

    -- For MTH1125
    (51, 4, NULL, 0.0, NULL),
    (52, 4, NULL, 0.0, NULL),
    (53, 4, NULL, 0.0, NULL),
    (54, 4, NULL, 0.0, NULL),
    (55, 4, NULL, 0.0, NULL),
    (56, 4, NULL, 0.0, NULL),
    (57, 4, NULL, 0.0, NULL),
    (58, 4, NULL, 0.0, NULL),
    (59, 4, NULL, 0.0, NULL),
    (60, 4, NULL, 0.0, NULL),

    -- For MTH1126
    (61, 4, NULL, 0.0, NULL),
    (62, 4, NULL, 0.0, NULL),
    (63, 4, NULL, 0.0, NULL),
    (64, 4, NULL, 0.0, NULL),
    (65, 4, NULL, 0.0, NULL),
    (66, 4, NULL, 0.0, NULL),
    (67, 4, NULL, 0.0, NULL),
    (68, 4, NULL, 0.0, NULL),
    (69, 4, NULL, 0.0, NULL),
    (70, 4, NULL, 0.0, NULL),

    -- For MTH2215
    (71, 4, NULL, 0.0, NULL),
    (72, 4, NULL, 0.0, NULL),
    (73, 4, NULL, 0.0, NULL),
    (74, 4, NULL, 0.0, NULL),
    (75, 4, NULL, 0.0, NULL),
    (76, 4, NULL, 0.0, NULL),
    (77, 4, NULL, 0.0, NULL),
    (78, 4, NULL, 0.0, NULL),
    (79, 4, NULL, 0.0, NULL),
    (80, 4, NULL, 0.0, NULL),

    -- For ECO2251
    (81, 4, NULL, 0.0, NULL),
    (82, 4, NULL, 0.0, NULL),
    (83, 4, NULL, 0.0, NULL),
    (84, 4, NULL, 0.0, NULL),
    (85, 4, NULL, 0.0, NULL),
    (86, 4, NULL, 0.0, NULL),
    (87, 4, NULL, 0.0, NULL),
    (88, 4, NULL, 0.0, NULL),
    (89, 4, NULL, 0.0, NULL),
    (90, 4, NULL, 0.0, NULL),

    -- For ECO2252
    (91, 4, NULL, 0.0, NULL),
    (92, 4, NULL, 0.0, NULL),
    (93, 4, NULL, 0.0, NULL),
    (94, 4, NULL, 0.0, NULL),
    (95, 4, NULL, 0.0, NULL),
    (96, 4, NULL, 0.0, NULL),
    (97, 4, NULL, 0.0, NULL),
    (98, 4, NULL, 0.0, NULL),
    (99, 4, NULL, 0.0, NULL),
    (100, 4, NULL, 0.0, NULL),
    
    -- ------------------------- student id = 5 -----------------------------
    -- For CS4420
    (1, 5, NULL, 0.0, NULL),
    (2, 5, NULL, 0.0, NULL),
    (3, 5, NULL, 0.0, NULL),
    (4, 5, NULL, 0.0, NULL),
    (5, 5, NULL, 0.0, NULL),
    (6, 5, NULL, 0.0, NULL),
    (7, 5, NULL, 0.0, NULL),
    (8, 5, NULL, 0.0, NULL),
    (9, 5, NULL, 0.0, NULL),
    (10, 5, NULL, 0.0, NULL),

    -- For CS3360
    (11, 5, NULL, 0.0, NULL),
    (12, 5, NULL, 0.0, NULL),
    (13, 5, NULL, 0.0, NULL),
    (14, 5, NULL, 0.0, NULL),
    (15, 5, NULL, 0.0, NULL),
    (16, 5, NULL, 0.0, NULL),
    (17, 5, NULL, 0.0, NULL),
    (18, 5, NULL, 0.0, NULL),
    (19, 5, NULL, 0.0, NULL),
    (20, 5, NULL, 0.0, NULL),

    -- For CS3365
    (21, 5, NULL, 0.0, NULL),
    (22, 5, NULL, 0.0, NULL),
    (23, 5, NULL, 0.0, NULL),
    (24, 5, NULL, 0.0, NULL),
    (25, 5, NULL, 0.0, NULL),
    (26, 5, NULL, 0.0, NULL),
    (27, 5, NULL, 0.0, NULL),
    (28, 5, NULL, 0.0, NULL),
    (29, 5, NULL, 0.0, NULL),
    (30, 5, NULL, 0.0, NULL),

    -- For CS2250
    (31, 5, NULL, 0.0, NULL),
    (32, 5, NULL, 0.0, NULL),
    (33, 5, NULL, 0.0, NULL),
    (34, 5, NULL, 0.0, NULL),
    (35, 5, NULL, 0.0, NULL),
    (36, 5, NULL, 0.0, NULL),
    (37, 5, NULL, 0.0, NULL),
    (38, 5, NULL, 0.0, NULL),
    (39, 5, NULL, 0.0, NULL),
    (40, 5, NULL, 0.0, NULL),

    -- For CS2255
    (41, 5, NULL, 0.0, NULL),
    (42, 5, NULL, 0.0, NULL),
    (43, 5, NULL, 0.0, NULL),
    (44, 5, NULL, 0.0, NULL),
    (45, 5, NULL, 0.0, NULL),
    (46, 5, NULL, 0.0, NULL),
    (47, 5, NULL, 0.0, NULL),
    (48, 5, NULL, 0.0, NULL),
    (49, 5, NULL, 0.0, NULL),
    (50, 5, NULL, 0.0, NULL),

    -- For MTH1125
    (51, 5, NULL, 0.0, NULL),
    (52, 5, NULL, 0.0, NULL),
    (53, 5, NULL, 0.0, NULL),
    (54, 5, NULL, 0.0, NULL),
    (55, 5, NULL, 0.0, NULL),
    (56, 5, NULL, 0.0, NULL),
    (57, 5, NULL, 0.0, NULL),
    (58, 5, NULL, 0.0, NULL),
    (59, 5, NULL, 0.0, NULL),
    (60, 5, NULL, 0.0, NULL),

    -- For MTH1126
    (61, 5, NULL, 0.0, NULL),
    (62, 5, NULL, 0.0, NULL),
    (63, 5, NULL, 0.0, NULL),
    (64, 5, NULL, 0.0, NULL),
    (65, 5, NULL, 0.0, NULL),
    (66, 5, NULL, 0.0, NULL),
    (67, 5, NULL, 0.0, NULL),
    (68, 5, NULL, 0.0, NULL),
    (69, 5, NULL, 0.0, NULL),
    (70, 5, NULL, 0.0, NULL),

    -- For MTH2215
    (71, 5, NULL, 0.0, NULL),
    (72, 5, NULL, 0.0, NULL),
    (73, 5, NULL, 0.0, NULL),
    (74, 5, NULL, 0.0, NULL),
    (75, 5, NULL, 0.0, NULL),
    (76, 5, NULL, 0.0, NULL),
    (77, 5, NULL, 0.0, NULL),
    (78, 5, NULL, 0.0, NULL),
    (79, 5, NULL, 0.0, NULL),
    (80, 5, NULL, 0.0, NULL),

    -- For ECO2251
    (81, 5, NULL, 0.0, NULL),
    (82, 5, NULL, 0.0, NULL),
    (83, 5, NULL, 0.0, NULL),
    (84, 5, NULL, 0.0, NULL),
    (85, 5, NULL, 0.0, NULL),
    (86, 5, NULL, 0.0, NULL),
    (87, 5, NULL, 0.0, NULL),
    (88, 5, NULL, 0.0, NULL),
    (89, 5, NULL, 0.0, NULL),
    (90, 5, NULL, 0.0, NULL),

    -- For ECO2252
    (91, 5, NULL, 0.0, NULL),
    (92, 5, NULL, 0.0, NULL),
    (93, 5, NULL, 0.0, NULL),
    (94, 5, NULL, 0.0, NULL),
    (95, 5, NULL, 0.0, NULL),
    (96, 5, NULL, 0.0, NULL),
    (97, 5, NULL, 0.0, NULL),
    (98, 5, NULL, 0.0, NULL),
    (99, 5, NULL, 0.0, NULL),
    (100, 5, NULL, 0.0, NULL);