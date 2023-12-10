INSERT INTO users(id, age, email, full_name, password, username)
VALUES
    (1, 20, '111@12.bg', 'Admin Adminov', '6cf1643832d3199650e5b751fd97473490bc6dd4aa9343d9bfa23ba9ceb2e24d71360d42d571a2cabdbcec584bbda490', 'admin'),
    (2, 16, '12@11.bg', 'User Userov', '6cf1643832d3199650e5b751fd97473490bc6dd4aa9343d9bfa23ba9ceb2e24d71360d42d571a2cabdbcec584bbda490', 'user');


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
    (4, 'MODERNISM', 'John Grisham'),
    (5, 'REALISM', 'Frank Herbert'),
    (6, 'ROMANTICISM', 'Rebecca Yarros'),
    (7, 'MODERNISM', 'James McBride'),
    (8, 'REALISM', 'Stephen King'),
    (9, 'RENAISSANCE', 'Brothers Grimm');



INSERT INTO books(id, added_in_cart, audience_type, favourite, summary, title, author_id, price)
VALUES
    (1, false, 'KIDS', false, '"Cinderella", or "The Little Glass Slipper", is a folk tale with thousands of variants that are told throughout the world. The protagonist is a young girl living in forsaken circumstances that are suddenly changed to remarkable fortune, with her ascension to the throne via marriage.',
     'Cinderella', 1, 22.00),
    (2, false, 'ADULT', false, 'Hobbits are a fictional race of people in the novels of J. R. R. Tolkien. About half average human height, Tolkien presented Hobbits as a variety of humanity, or close relatives thereof.',
     'Hobbit', 2, 30.00),
    (3, false, 'TEENAGERS', false, 'An American feature film adaptation of the same name as the novel directed by Josh Boone and starring Shailene Woodley, Ansel Elgort, and Nat Wolff was released on June 6, 2014.',
     'The Fault in Our Stars', 3, 20.00),
    (4, false, 'ADULT', false, 'This one is from 3:3 in the Ecclesiastes, again part of the Old Testament. The anonymous author is a King of Jerusalem who relates and analyses events in his own life. This has resonated strongly with a lot of people.',
     'A Time to Kill', 4, 25.00),
    (5, false, 'ADULT', false, 'Dune is a 1965 epic science fiction novel book by American author Frank Herbert, originally published as two separate serials in Analog magazine. It tied with Roger Zelazny''s This Immortal for the Hugo Award in 1966 and it won the inaugural Nebula Award for Best Novel.',
    'Dune', 5, 25.00),
    (6, false, 'TEENAGERS', false, 'Twenty-year-old Violet Sorrengail was supposed to enter the Scribe Quadrant, living a quiet life among books and history. Now, the commanding general—also known as her tough-as-talons mother—has ordered Violet to join the hundreds of candidates striving to become the elite of Navarre: dragon riders.',
     'Fourth Wing', 6, 30.00),
    (7, false, 'TEENAGERS', false, 'In 1972, when workers in Pottstown, Pennsylvania, were digging the foundations for a new development, the last thing they expected to find was a skeleton at the bottom of a well. Who the skeleton was and how it got there were two of the long-held secrets kept by the residents of Chicken Hill.',
     'The Heaven & Earth Grocery Store', 7, 20.00),
    (8, false, 'ADULT', false, 'Holly is a 2023 crime novel[3] by American author Stephen King. It was published on September 5, 2023, by Scribner.',
     'Holly', 8, 18.00),
    (9, false, 'KIDS', false, '"Snow White" is a German fairy tale, first written down in the early 19th century. The Brothers Grimm published it in 1812 in the first edition of their collection Grimms'' Fairy Tales, numbered as Tale 53.',
     'Snow White', 9, 15.00);

INSERT INTO books_genres(book_id, genres_id)
VALUES
    (1, 1),
    (1, 6),
    (2, 4),
    (2, 2),
    (3, 1),
    (3, 5),
    (4, 5),
    (4, 7),
    (5, 2),
    (5, 7),
    (6, 4),
    (6, 1),
    (7, 8),
    (7, 1),
    (8, 3),
    (8, 5),
    (9, 1),
    (9, 4);


INSERT INTO comments(id, created, message, book_id, user_id, approved)
VALUES
    (1, NOW(), 'Like this comment', 1, 1, false),
    (2, NOW(), 'Love the book!', 1, 2, false);




