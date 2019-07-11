CREATE TABLE adminLoginRecord (
  id VARCHAR(50) PRIMARY KEY,
  user_name VARCHAR(255) NOT NULL,
  last_ip VARCHAR(255),
  current_ip VARCHAR(255) NOT NULL,
  last_login_time DATETIME,
  current_login_time DATETIME NOT NULL
);