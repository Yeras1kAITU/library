package persistence;

import core.Book;
import core.LibraryUser;
import java.util.List;

public interface UserRepository {
    void addUser(LibraryUser user);
    void deleteUserById(String userId);
    List<LibraryUser> getAllUsers();
    void assignBorrowedBook(String userId, String isbn);
    List<Book> getUserBorrowedBooks(String userId);
}