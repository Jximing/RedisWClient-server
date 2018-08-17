CREATE TABLE IF NOT EXISTS "db_version" (
  "id"          INTEGER   NOT NULL,
  "create_time" TIMESTAMP NOT NULL,
  PRIMARY KEY ("id" ASC)
);

CREATE TABLE IF NOT EXISTS "user" (
  "id"          VARCHAR(45)     NOT NULL,
  "username"    VARCHAR(55)  NOT NULL,
  "password"    VARCHAR(255) NOT NULL,
  "salt"        VARCHAR(10)  NOT NULL,
  "create_time" TIMESTAMP    NOT NULL,
  PRIMARY KEY ("id"),
  UNIQUE ("username" ASC)
);


insert into db_version (create_time) values(strftime('%Y-%m-%d %H:%M:%S.000','now','localtime'));