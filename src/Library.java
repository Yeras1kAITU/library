import java.util.ArrayList;

public class Library {
    private String name;
    private ArrayList<Book> books;
    private ArrayList<LibraryUser> users;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<LibraryUser> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<LibraryUser> users) {
        this.users = users;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(LibraryUser user) {
        users.add(user);
    }

    public void displayLibraryInfo() {
        System.out.println("Library Name: " + name);
        System.out.println("Books in Library:");
        for (Book book : books) {
            book.displayInfo();
        }
        System.out.println("Library Users:");
        for (LibraryUser user : users) {
            user.displayUserInfo();
        }
    }
}
