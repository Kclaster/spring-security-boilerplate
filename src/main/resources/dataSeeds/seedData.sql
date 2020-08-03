INSERT INTO books (id, title, description) VALUES
(1, 'Lord of the Rings', 'Nine men go on an adventure to return another mans jewelry.'),
(2, 'Starwars', 'The world is black and white. Its simpler that way. Its better that way.'),
(3, 'Harry Potter and the Socerer Stone', 'Should be renamed to Hermione and her useless friends.');

select setval('books_id_seq',COALESCE((select max(id) + 1 from books), 1));
