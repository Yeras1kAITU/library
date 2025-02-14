package persistence;

import core.Book;
import core.LibraryUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public void addUser(LibraryUser user) {
        String sql = "INSERT INTO users (user_id, name) VALUES (?, ?)";
        try (Connection conn = DB_Connection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserById(String userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection conn = DB_Connection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void assignBorrowedBook(String userId, String isbn) {
        String sql = "INSERT INTO borrowed_books (user_id, book_id) SELECT ?, isbn FROM books WHERE isbn = ?";
        try (Connection conn = DB_Connection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setString(2, isbn);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getUserBorrowedBooks(String userId) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT books.title, books.author FROM borrowed_books " +
                "JOIN books ON borrowed_books.book_id = books.isbn " +
                "WHERE borrowed_books.user_id = ?";
        try (Connection conn = DB_Connection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        "", ""
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<LibraryUser> getAllUsers() {
        List<LibraryUser> users = new ArrayList<>();
        String sql = "SELECT users.user_id, users.name, books.title AS book_title, books.author AS book_author, books.isbn AS book_isbn, books.genre AS book_genre " +
                "FROM users " +
                "LEFT JOIN borrowed_books ON users.user_id = borrowed_books.user_id " +
                "LEFT JOIN books ON borrowed_books.book_id = books.isbn " +
                "ORDER BY users.user_id";

        try (Connection conn = DB_Connection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            String currentUserId = "";
            LibraryUser currentUser = null;

            while (rs.next()) {
                String userId = rs.getString("user_id");
                String name = rs.getString("name");
                String bookTitle = rs.getString("book_title");
                String bookAuthor = rs.getString("book_author");
                String bookIsbn = rs.getString("book_isbn");
                String bookGenre = rs.getString("book_genre");

                if (!userId.equals(currentUserId)) {
                    if (currentUser != null) {
                        users.add(currentUser);
                    }
                    currentUser = new LibraryUser(name, userId);
                    currentUserId = userId;
                }

                if (bookTitle != null) {
                    Book borrowedBook = new Book(bookTitle, bookAuthor, bookIsbn, bookGenre);
                    currentUser.borrowItem(borrowedBook);
                }
            }

            if (currentUser != null) {
                users.add(currentUser);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}