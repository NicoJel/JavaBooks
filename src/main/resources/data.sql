-- noinspection SqlNoDataSourceInspectionForFile


INSERT INTO Author (id, first_name, last_name, nationality) VALUES (1, 'coco', 'lasticot', 'francais');
INSERT INTO Author (id, first_name, last_name, nationality) VALUES (2, 'pat', 'rick', 'siberien');
INSERT INTO Author (id, first_name, last_name, nationality) VALUES (3, 'georgette', 'lasablee', 'francais');

INSERT INTO Book (id, name, author_id, year, pages) VALUES (1, 'Livre I', 1, 2019, 12);
INSERT INTO Book (id, name, author_id, year, pages) VALUES (2, 'Livre II', 1, 2042, 1000);
INSERT INTO Book (id, name, author_id, year, pages) VALUES (3, 'Livre III', 2, 1990, 1000);
INSERT INTO Book (id, name, author_id, year, pages) VALUES (4, 'Livre IV', 3, 1990, 666);
INSERT INTO Book (id, name, author_id, year, pages) VALUES (5, 'Livre V', 3, 2042, 12);
