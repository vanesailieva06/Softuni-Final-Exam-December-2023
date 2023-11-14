INSERT INTO users(id, age, email, full_name, password, username)
VALUES
    (1, 20, '111@12.bg', 'Admin Adminov', '6cf1643832d3199650e5b751fd97473490bc6dd4aa9343d9bfa23ba9ceb2e24d71360d42d571a2cabdbcec584bbda490', 'Admincho'),
    (2, 16, '12@11.bg', 'User Userov', '6cf1643832d3199650e5b751fd97473490bc6dd4aa9343d9bfa23ba9ceb2e24d71360d42d571a2cabdbcec584bbda490', 'Usercho');


INSERT INTO roles(id, role_type)
VALUES
    (1, 'USER'),
    (2, 'ADMIN');

INSERT INTO users_roles(user_id, roles_id)
VALUES
    (2, 1),
    (1, 2);




INSERT INTO genres(id, genre_type)
VALUES
    (1, 'ROMANCE'),
    (2, 'SCIENCE_FICTION'),
    (3, 'HORROR'),
    (4, 'FANTASY'),
    (5, 'THRILLER'),
    (6, 'HISTORY'),
    (7, 'ACTION'),
    (8, 'COMEDY');

INSERT INTO authors(id, era_of_writing, name)
VALUES
    (1, 'ROMANTICISM', 'Giambattista Basile'),
    (2, 'REALISM', 'John Ronald Reuel Tolkien'),
    (3, 'MODERNISM', 'John Green'),
    (4, 'MODERNISM', 'John Grisham');


INSERT INTO books(id, added_in_cart, audience_type, favourite, summary, title, author_id)
VALUES
    (1, false, 'KIDS', false, '"Cinderella", or "The Little Glass Slipper", is a folk tale with thousands of variants that are told throughout the world. The protagonist is a young girl living in forsaken circumstances that are suddenly changed to remarkable fortune, with her ascension to the throne via marriage.',
     'Cinderella', 1),
    (2, false, 'ADULT', false, 'Hobbits are a fictional race of people in the novels of J. R. R. Tolkien. About half average human height, Tolkien presented Hobbits as a variety of humanity, or close relatives thereof.',
     'Hobbit', 2),
    (3, false, 'TEENAGERS', false, 'An American feature film adaptation of the same name as the novel directed by Josh Boone and starring Shailene Woodley, Ansel Elgort, and Nat Wolff was released on June 6, 2014.',
     'The Fault in Our Stars', 3),
    (4, false, 'ADULT', false, 'This one is from 3:3 in the Ecclesiastes, again part of the Old Testament. The anonymous author is a King of Jerusalem who relates and analyses events in his own life. This has resonated strongly with a lot of people.',
     'A Time to Kill', 4);

INSERT INTO books_genres(book_id, genres_id)
VALUES
    (1, 1),
    (1, 6),
    (2, 4),
    (2, 2),
    (3, 1),
    (3, 5),
    (4, 5),
    (4, 7);


INSERT INTO offers(id, price, book_id)
VALUES
    (1, 20.00, 1),
    (2, 22.00, 3),
    (3, 35.00, 2);






