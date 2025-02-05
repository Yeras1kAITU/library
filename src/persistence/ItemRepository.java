package persistence;

import core.Book;
import java.util.List;

public interface ItemRepository {
    void addBook(Book book);
    void deleteBookByIsbn(String isbn);
    List<Book> getAllBooks();
    List<Book> searchBooksByTitle(String keyword);
    List<Book> filterBooksByGenre(String genre);
}