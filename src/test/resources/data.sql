-- Insert data into Retrospective table
INSERT INTO Retrospective (name, summary, entry_date)
VALUES
    ('Retrospective 1', 'Post release retrospective', '2022-07-27'),
    ('Retrospective 2', 'Sprint review meeting', '2022-08-10'),
    ('Retrospective 3', 'Quality improvement', '2022-09-05'),
    ('Retrospective 4', 'Release planning discussion', '2022-10-12'),
    ('Retrospective 5', 'Bug triage and resolution', '2022-11-02'),
    ('Retrospective 6', 'Team building activities', '2022-11-28'),
    ('Retrospective 7', 'Product roadmap review', '2022-12-15'),
    ('Retrospective 8', 'Process optimization', '2023-01-09'),
    ('Retrospective 9', 'Customer feedback analysis', '2023-02-06'),
    ('Retrospective 10', 'Feature prioritization', '2023-03-03'),
    ('Retrospective 11', 'Performance improvement', '2023-04-18'),
    ('Retrospective 12', 'User experience enhancement', '2023-05-09'),
    ('Retrospective 13', 'Security audit', '2023-06-07');

-- Insert data into retrospective_participants table for Retrospective 1
INSERT INTO retrospective_participants (participants, retrospective_id)
VALUES
    ('Viktor', 1),
    ('Gareth', 1),
    ('Mike', 1);

-- Insert data into retrospective_participants table for Retrospective 2
INSERT INTO retrospective_participants (participants, retrospective_id)
VALUES
    ('Alice', 2),
    ('Bob', 2),
    ('Charlie', 2);

-- Insert data into retrospective_participants table for Retrospective 3
INSERT INTO retrospective_participants (participants, retrospective_id)
VALUES
    ('David', 3),
    ('Eve', 3),
    ('Frank', 3);

-- Insert data into retrospective_participants table for Retrospective 4
INSERT INTO retrospective_participants (participants, retrospective_id)
VALUES
    ('Hannah', 4),
    ('Ivan', 4),
    ('Jack', 4);

-- Insert data into retrospective_participants table for Retrospective 5
INSERT INTO retrospective_participants (participants, retrospective_id)
VALUES
    ('Katherine', 5),
    ('Liam', 5),
    ('Mia', 5);

-- Insert data into retrospective_participants table for Retrospective 6
INSERT INTO retrospective_participants (participants, retrospective_id)
VALUES
    ('Noah', 6),
    ('Olivia', 6),
    ('Parker', 6);

-- Insert data into retrospective_participants table for Retrospective 7
INSERT INTO retrospective_participants (participants, retrospective_id)
VALUES
    ('Quinn', 7),
    ('Rachel', 7),
    ('Sam', 7);

-- Insert data into retrospective_participants table for Retrospective 8
INSERT INTO retrospective_participants (participants, retrospective_id)
VALUES
    ('Taylor', 8),
    ('Ursula', 8),
    ('Vincent', 8);

-- Insert data into retrospective_participants table for Retrospective 9
INSERT INTO retrospective_participants (participants, retrospective_id)
VALUES
    ('Wendy', 9),
    ('Xander', 9),
    ('Yasmine', 9);

-- Insert data into retrospective_participants table for Retrospective 10
INSERT INTO retrospective_participants (participants, retrospective_id)
VALUES
    ('Zane', 10),
    ('Alice', 10),
    ('Bob', 10);

-- Insert data into retrospective_participants table for Retrospective 11
INSERT INTO retrospective_participants (participants, retrospective_id)
VALUES
    ('Charlie', 11),
    ('David', 11),
    ('Ella', 11);

-- Insert data into retrospective_participants table for Retrospective 12
INSERT INTO retrospective_participants (participants, retrospective_id)
VALUES
    ('Frankie', 12),
    ('Grace', 12),
    ('Henry', 12);

-- Insert data into retrospective_participants table for Retrospective 13
INSERT INTO retrospective_participants (participants, retrospective_id)
VALUES
    ('Isabella', 13),
    ('Jacob', 13),
    ('Kylie', 13);

-- Insert data into FeedbackItem table for Retrospective 4
INSERT INTO Feedback_Item (retrospective_id, name, body, feedback_type)
VALUES
    (4, 'Hannah', 'Smooth release process', 'Positive'),
    (4, 'Ivan', 'Minor UI bugs need attention', 'Negative'),
    (4, 'Jack', 'Explore new deployment strategies', 'Idea');

-- Insert data into FeedbackItem table for Retrospective 5
INSERT INTO Feedback_Item (retrospective_id, name, body, feedback_type)
VALUES
    (5, 'Katherine', 'Resolved critical bugs efficiently', 'Positive'),
    (5, 'Liam', 'Documentation needs improvement', 'Negative'),
    (5, 'Mia', 'Consider automated testing', 'Idea');

-- Insert data into FeedbackItem table for Retrospective 6
INSERT INTO Feedback_Item (retrospective_id, name, body, feedback_type)
VALUES
    (6, 'Noah', 'Team bonding activities were fun', 'Praise'),
    (6, 'Olivia', 'Discuss team roles for clarity', 'Idea'),
    (6, 'Parker', 'Improved communication needed', 'Negative');

-- Insert data into FeedbackItem table for Retrospective 7
INSERT INTO Feedback_Item (retrospective_id, name, body, feedback_type)
VALUES
    (7, 'Quinn', 'Product roadmap aligns with goals', 'Positive'),
    (7, 'Rachel', 'Prioritize user-focused features', 'Idea'),
    (7, 'Sam', 'Address performance bottlenecks', 'Negative');

-- Insert data into FeedbackItem table for Retrospective 8
INSERT INTO Feedback_Item (retrospective_id, name, body, feedback_type)
VALUES
    (8, 'Taylor', 'Optimize workflow processes', 'Idea'),
    (8, 'Ursula', 'Positive work environment', 'Praise'),
    (8, 'Vincent', 'Improve documentation', 'Negative');

-- Insert data into FeedbackItem table for Retrospective 9
INSERT INTO Feedback_Item (retrospective_id, name, body, feedback_type)
VALUES
    (9, 'Wendy', 'Valuable customer insights', 'Positive'),
    (9, 'Xander', 'Address communication gaps', 'Negative'),
    (9, 'Yasmine', 'Consider user interface enhancements', 'Idea');

-- Insert data into FeedbackItem table for Retrospective 10
INSERT INTO Feedback_Item (retrospective_id, name, body, feedback_type)
VALUES
    (10, 'Zane', 'Feature prioritization process', 'Positive'),
    (10, 'Alice', 'Performance issues in testing', 'Negative'),
    (10, 'Bob', 'Explore new technology stack', 'Idea');

-- Insert data into FeedbackItem table for Retrospective 11
INSERT INTO Feedback_Item (retrospective_id, name, body, feedback_type)
VALUES
    (11, 'Charlie', 'Improved code quality', 'Positive'),
    (11, 'David', 'Communication gaps with stakeholders', 'Negative'),
    (11, 'Ella', 'Consider performance optimization', 'Idea');

-- Insert data into FeedbackItem table for Retrospective 12
INSERT INTO Feedback_Item (retrospective_id, name, body, feedback_type)
VALUES
    (12, 'Frankie', 'Positive user experiences', 'Praise'),
    (12, 'Grace', 'Usability improvements needed', 'Negative'),
    (12, 'Henry', 'Explore UX design enhancements', 'Idea');

-- Insert data into FeedbackItem table for Retrospective 13
INSERT INTO Feedback_Item (retrospective_id, name, body, feedback_type)
VALUES
    (13, 'Isabella', 'Security audit passed successfully', 'Positive'),
    (13, 'Jacob', 'Security vulnerabilities identified', 'Negative'),
    (13, 'Kylie', 'Implement additional security measures', 'Idea');


-- Insert data into FeedbackItem table for Retrospective 1
INSERT INTO Feedback_Item (retrospective_id, name, body, feedback_type)
VALUES
    (1, 'Gareth', 'Sprint objective met', 'Positive'),
    (1, 'Viktor', 'Too many items piled up in the awaiting QA', 'Negative'),
    (1, 'Mike', 'We should be looking to start using VS2015', 'Idea');

-- Insert data into FeedbackItem table for Retrospective 2
INSERT INTO Feedback_Item (retrospective_id, name, body, feedback_type)
VALUES
    (2, 'Alice', 'Great teamwork', 'Praise'),
    (2, 'Bob', 'Some issues with the testing process', 'Negative'),
    (2, 'Charlie', 'New design ideas discussed', 'Idea');

-- Insert data into FeedbackItem table for Retrospective 3
INSERT INTO Feedback_Item (retrospective_id, name, body, feedback_type)
VALUES
    (3, 'David', 'Improved code quality', 'Positive'),
    (3, 'Eve', 'Communication issues with the client', 'Negative'),
    (3, 'Frank', 'Suggested using a different testing framework', 'Idea');

