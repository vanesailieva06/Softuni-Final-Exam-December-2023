const bookList = document.getElementById('book-section')
const searchBar = document.getElementById('search-bar')

const allBooks = [];

fetch("http://localhost:8080/books/api").
then(response => response.json()).
then(data => {
    for (let book of data) {
        allBooks.push(book);
    }
    console.log(allBooks);
})


searchBar.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar.value.toLowerCase();
    let filteredBooks = allBooks.filter(book => {
        return book.title.toLowerCase().includes(searchingCharacters)
            || book.author.toLowerCase().includes(searchingCharacters);
    });
    displayBooks(filteredBooks);
})


const displayBooks = (books) => {
    bookList.innerHTML = books
        .map((b) => {
          return `
                    <div class="book" id = "book-list">
                    <div class="book-info" id="book-info">
                        <h2 class="title-heading" id="title-heading" >${b.title}</h2>
                    <h3 class="author-heading" id="author-heading" >${b.author}</h3>
                    <h4 class="genre-heading" id="genre-heading">${b.genres}</h4>
                    <p class="summary-book" id="summary">
                      ${b.summary}
                    </p>
                      <a class="btn btn-dark btn-lg buy-btn" href="/books/favourites/add/${b.id}">Add to favourites</a>
                    </div>
                    <div class="img-book" id="img-div">
                        <img src="/images/default.avif" alt="img-book">
                    </div>
                    </div>
                  `
        })
        .join('');
}

function createElement(type, parent, content, classes, id, attributes) {
    let element = document.createElement(type);
    if (content && type !== "input") {
        element.textContent = content;
    }
    if (content && type === "input") {
        element.value = content;
    }
    if (parent) {
        parent.appendChild(element);
    }
    if (id) {
        element.id = id;
    }
    if (classes) {
        element.classList.add(...classes);
    }
    if (attributes) {
        for (const key in attributes) {
            element.setAttribute(key, attributes[key]);
        }
    }
    return element;
}
