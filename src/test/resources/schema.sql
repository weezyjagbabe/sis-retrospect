  CREATE TABLE Retrospective (
      id INT PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(255) NOT NULL UNIQUE,
      summary TEXT,
      entry_date DATE NOT NULL,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  );

  CREATE TABLE Feedback_Item (
      id INT PRIMARY KEY AUTO_INCREMENT,
      retrospective_id INT NOT NULL,
      name VARCHAR(255) NOT NULL,
      body TEXT NOT NULL,
      feedback_type ENUM('Positive', 'Negative', 'Idea', 'Praise') NOT NULL,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      FOREIGN KEY (retrospective_id) REFERENCES Retrospective(id)
  );

  CREATE TABLE retrospective_participants (
    id INT PRIMARY KEY AUTO_INCREMENT,
    participants VARCHAR(255) NOT NULL,
    retrospective_id INT,
    FOREIGN KEY (retrospective_id) REFERENCES Retrospective(id)
);

