CREATE USER 'saveadmin'@'localhost' IDENTIFIED BY 'saveadmin';
CREATE USER 'saveadmin'@'%' IDENTIFIED BY 'saveadmin';
CREATE USER 'saveuser'@'localhost' IDENTIFIED BY 'saveuser';
CREATE USER 'saveuser'@'%' IDENTIFIED BY 'saveuser';

CREATE DATABASE savedb CHARACTER SET 'utf8';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, TRIGGER ON savedb.* TO 'saveadmin'@'localhost' IDENTIFIED BY 'saveadmin';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, TRIGGER ON savedb.* TO 'saveadmin'@'%' IDENTIFIED BY 'saveadmin';
GRANT SELECT, INSERT, UPDATE, DELETE ON savedb.* TO 'saveuser'@'localhost' IDENTIFIED BY 'saveuser';
GRANT SELECT, INSERT, UPDATE, DELETE ON savedb.* TO 'saveuser'@'%' IDENTIFIED BY 'saveuser';
