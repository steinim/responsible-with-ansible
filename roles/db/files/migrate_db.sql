CREATE SCHEMA IF NOT EXISTS DEVOPS;
CREATE TABLE IF NOT EXISTS HELLO ( MESSAGE VARCHAR(256) );
DELETE FROM HELLO;
INSERT INTO HELLO VALUES ('Hello from Database');
COMMIT;
