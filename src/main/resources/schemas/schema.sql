CREATE DATABASEIF NOT EXISTS git_sql_db;

CREATE TABLE IF NOT EXISTS git_repositories(
  id INT(20) NOT NULL UNIQUE,
  repo_name VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY (id,repo_name));
  
CREATE TABLE IF NOT EXISTS git_daily_commits(
  id INT(20) NOT NULL UNIQUE,
  repo_id INT(20),
  week_of DATE,
  commits INT(20),
  PRIMARY KEY (id),
  FOREIGN KEY (repo_id) REFERENCES git_repositories(id));