import java.util.ArrayList;
import java.util.Scanner;

class LibraryUser {
    private String name;
    private String userId;
    private ArrayList<Book> borrowedBooks;

    public LibraryUser(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void displayBorrowedBooks() {
        System.out.println("_________________Borrowed Books_________________");
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            for (Book book : borrowedBooks) {
                book.displayInfo();
                System.out.println();
            }
        }
        System.out.println("________________________________________________");
    }

    public void displayUserInfo() {
        System.out.println(this.toString());
        displayBorrowedBooks();
    }

    @Override
    public String toString() {
        return "User Name: " + name + ", User ID: " + userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LibraryUser user = (LibraryUser) obj;
        return userId.equals(user.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    public static LibraryUser createUserWithInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        return new LibraryUser(name, userId);
    }
}