INSERT INTO USER_T (USERNAME_F, PASSWORD_F)
VALUES ('admin', '$2a$04$/87gxfQlNqMNRvI/ILyZ/.F8Bk2/t2RuWoZXE1upQHeUglbjTYIIa');
INSERT INTO ROLE_T (NAME_F) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE_T (NAME_F) VALUES ('ROLE_USER');
INSERT INTO USER_ROLE_T (USER_ID, ROLE_ID) VALUES ((SELECT USER_ID
                                                    FROM USER_T
                                                    WHERE USERNAME_F = 'admin'), (SELECT ROLE_ID
                                                                                  FROM ROLE_T
                                                                                  WHERE NAME_F = 'ROLE_ADMIN'));