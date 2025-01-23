import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

interface Displayable {
    void displayInfo();
}

abstract class LibraryItem implements Displayable {
    private String title;
    private String id;
    private String genre;

    public LibraryItem(String title, String id, String genre) {
        this.title = title;
        this.id = id;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", ID: " + id + ", Genre: " + genre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LibraryItem that = (LibraryItem) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

class Library {
    private String name;
    private ArrayList<LibraryItem> items;
    private HashSet<LibraryUser> users;

    public Library(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.users = new HashSet<>();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    // Overloaded addItem method (Static Polymorphism)
    public void addItem(String title, String author, String isbn, String genre) {
        items.add(new Book(title, author, isbn, genre));
    }

    public boolean addUser(LibraryUser user) {
        return users.add(user);
    }

    public void deleteBookByIsbn(String isbn) {
        String sql = "DELETE FROM books WHERE isbn = ?";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book with ISBN " + isbn + " deleted successfully.");
            } else {
                System.out.println("No book found with ISBN " + isbn + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<LibraryItem> sortItemsByTitle() {
        return items.stream()
                .sorted(Comparator.comparing(LibraryItem::getTitle))
                .collect(Collectors.toList());
    }

    public void searchBooksByTitle(String keyword) {
        String sql = "SELECT * FROM books WHERE LOWER(title) LIKE ?";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Title: " + rs.getString("title") +
                        ", Author: " + rs.getString("author") +
                        ", ISBN: " + rs.getString("isbn") +
                        ", Genre: " + rs.getString("genre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllUsers() {
        String sql = "SELECT users.user_id, users.name, books.title AS book_title, books.author AS book_author, books.isbn AS book_isbn, books.genre AS book_genre " +
                "FROM users " +
                "LEFT JOIN borrowed_books ON users.user_id = borrowed_books.user_id " +
                "LEFT JOIN books ON borrowed_books.book_id = books.id " +
                "ORDER BY users.user_id";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            String currentUserId = "";
            while (rs.next()) {
                String userId = rs.getString("user_id");
                String name = rs.getString("name");
                String bookTitle = rs.getString("book_title");
                String bookAuthor = rs.getString("book_author");
                String bookIsbn = rs.getString("book_isbn");
                String bookGenre = rs.getString("book_genre");

                if (!userId.equals(currentUserId)) {
                    if (!currentUserId.isEmpty()) {
                        System.out.println("---------------------------------------");
                    }
                    System.out.println("User ID: " + userId);
                    System.out.println("Name: " + name);
                    System.out.println("Borrowed Books:");
                    currentUserId = userId;
                }

                if (bookTitle != null) {
                    System.out.println("  - Title: " + bookTitle);
                    System.out.println("    Author: " + bookAuthor);
                    System.out.println("    ISBN: " + bookIsbn);
                    System.out.println("    Genre: " + bookGenre);
                } else {
                    System.out.println("  No books borrowed.");
                }
            }

            System.out.println("---------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void filterBooksByGenre(String genre) {
        String sql = "SELECT * FROM books WHERE LOWER(genre) = ?";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, genre.toLowerCase());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Title: " + rs.getString("title") +
                        ", Author: " + rs.getString("author") +
                        ", ISBN: " + rs.getString("isbn") +
                        ", Genre: " + rs.getString("genre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Library createLibraryWithUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter library name: ");
        String libraryName = scanner.nextLine();
        Library library = new Library(libraryName);

        System.out.print("Enter number of books to add: ");
        int numBooks = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numBooks; i++) {
            System.out.println("Enter details for book " + (i + 1) + ":");
            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Author: ");
            String author = scanner.nextLine();
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Genre: ");
            String genre = scanner.nextLine();

            library.addItem(new Book(title, author, isbn, genre));
        }

        System.out.print("Enter number of users to add: ");
        int numUsers = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numUsers; i++) {
            System.out.println("Enter details for user " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("User ID: ");
            String userId = scanner.nextLine();

            library.addUser(new LibraryUser(name, userId));
        }

        return library;
    }
}

class LibraryDatabase {
    public void addBook(String title, String author, String isbn, String genre) {
        String sql = "INSERT INTO books (title, author, isbn, genre) VALUES (?, ?, ?, ?)";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, isbn);
            stmt.setString(4, genre);
            stmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllBooks() {
        String sql = "SELECT * FROM books ORDER BY title";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Title: " + rs.getString("title") +
                        ", Author: " + rs.getString("author") +
                        ", ISBN: " + rs.getString("isbn") +
                        ", Genre: " + rs.getString("genre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


