import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


class LibraryUser {
    private String name;
    private String userId;
    private ArrayList<LibraryItem> borrowedItems;

    public LibraryUser(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.borrowedItems = new ArrayList<>();
    }

    public LibraryUser(String userId) {
        this.userId = userId;
        this.borrowedItems = new ArrayList<>();
    }

    public void borrowItem(LibraryItem item) {
        borrowedItems.add(item);
    }

    public void borrowItem(String title, String author, String isbn, String genre) {
        borrowedItems.add(new Book(title, author, isbn, genre));
    }

    public void borrowBook(String userId, int bookId) {
        String sql = "INSERT INTO borrowed_books (user_id, book_id) VALUES (?, ?)";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
            System.out.println("Book borrowed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String userId, String name) {
        String sql = "INSERT INTO users (user_id, name) VALUES (?, ?)";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setString(2, name);
            stmt.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getUserBorrowedBooks(String userId) {
        String sql = "SELECT books.title, books.author FROM borrowed_books " +
                "JOIN books ON borrowed_books.book_id = books.id " +
                "WHERE borrowed_books.user_id = ?";

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Borrowed Book: " + rs.getString("title") +
                        ", Author: " + rs.getString("author"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}





