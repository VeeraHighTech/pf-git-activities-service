CREATE DATABASEIF NOT EXISTS repos_db;

CREATE TABLE IF NOT EXISTS repositories(
  id INT(20),
  repo_name VARCHAR(100) NOT NULL UNIQUE,
  created_at DATE,
  updated_at DATE,
  PRIMARY KEY (id,repo_name));
  

CREATE TABLE IF NOT EXISTS repo_daily_statistics(
  id INT(20) ,
  repo_id INT(20),
  day DATE,
  total_commits INT(20),
  PRIMARY KEY (id),
  FOREIGN KEY (repo_id) REFERENCES repositories(id));
  
  
 CREATE TABLE IF NOT EXISTS repo_weekly_statistics(
  id INT(20) NOT NULL UNIQUE,
  repo_id INT(20),
  week_of DATE,
  total_commits INT(20),
  PRIMARY KEY (id),
  FOREIGN KEY (repo_id) REFERENCES repositories(id));
  
  
  