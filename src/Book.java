class Book extends LibraryItem {
    private String author;

    public Book(String title, String author, String isbn) {
        super(title, isbn);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void displayInfo() {
        System.out.println("Book Title: " + getTitle());
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + getId());
    }

    @Override
    public String toString() {
        return super.toString() + ", Author: " + author;
    }
}