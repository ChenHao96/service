DROP TABLE QPManagerPlatformDB.dbo.users;
CREATE TABLE QPManagerPlatformDB.dbo.users (
  id          INT identity(1, 1) PRIMARY KEY,
  userName    VARCHAR(16) UNIQUE NOT NULL,
  nikeName    VARCHAR(50)        NOT NULL DEFAULT (''),
  password    VARCHAR(255)       NOT NULL,
  photo       VARCHAR(255)       NOT NULL DEFAULT (''),
  phoneNumber VARCHAR(20),
  updateAt    INT                NOT NULL,
  createAt    INT                NOT NULL,
  roleName_id INT                NOT NULL
);

--  ----------------------------
DROP TABLE QPManagerPlatformDB.dbo.roleName;
CREATE TABLE QPManagerPlatformDB.dbo.roleName (
  id       INT identity(1, 1) PRIMARY KEY,
  roleName VARCHAR(255) UNIQUE NOT NULL,
  updateAt INT                 NOT NULL,
  createAt INT                 NOT NULL
);

--  ----------------------------
DROP TABLE QPManagerPlatformDB.dbo.roleUrl;
CREATE TABLE QPManagerPlatformDB.dbo.roleUrl (
  id       INT identity(1, 1) PRIMARY KEY,
  urlName  VARCHAR(255) UNIQUE NOT NULL,
  url      VARCHAR(255)        NOT NULL,
  updateAt INT                 NOT NULL,
  createAt INT                 NOT NULL
);

--  ----------------------------
DROP TABLE QPManagerPlatformDB.dbo.rolePermission;
CREATE TABLE QPManagerPlatformDB.dbo.rolePermission (
  id          INT identity(1, 1) PRIMARY KEY,
  roleName_id INT NOT NULL,
  roleUrl_id  INT NOT NULL,
  updateAt    INT NOT NULL,
  createAt    INT NOT NULL
);