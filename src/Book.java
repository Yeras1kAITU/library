class Book extends LibraryItem {
    private String author;
    private String isbn;

    public Book(String title, String author, String isbn, String genre) {
        super(title, "Book-" + isbn, genre);
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public void displayInfo() {
        System.out.println("Book Title: " + getTitle() + ", Author: " + author + ", ISBN: " + isbn + ", Genre: " + getGenre());
    }
}
