CREATE USER 'swkroaadmin'@'localhost' IDENTIFIED BY 'cagst23824';
CREATE USER 'swkroaadmin'@'%' IDENTIFIED BY 'cagst23824';
CREATE USER 'swkroauser'@'localhost' IDENTIFIED BY 'cagst23824';
CREATE USER 'swkroauser'@'%' IDENTIFIED BY 'cagst23824';

CREATE DATABASE swkroadb CHARACTER SET 'utf8';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, ALTER, INDEX, TRIGGER ON swkroadb.* TO 'swkroaadmin'@'localhost' IDENTIFIED BY 'cagst23824';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, ALTER, INDEX, TRIGGER ON swkroadb.* TO 'swkroaadmin'@'%' IDENTIFIED BY 'cagst23824';
GRANT SELECT, INSERT, UPDATE, DELETE ON swkroadb.* TO 'swkroauser'@'localhost' IDENTIFIED BY 'cagst23824';
GRANT SELECT, INSERT, UPDATE, DELETE ON swkroadb.* TO 'swkroauser'@'%' IDENTIFIED BY 'cagst23824';
