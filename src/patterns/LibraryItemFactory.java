package patterns;

import core.Book;
import core.LibraryItem;

public class LibraryItemFactory {
    public static LibraryItem createItem(String type, String title, String id, String genre, String author, String isbn) {
        if (type.equalsIgnoreCase("Book")) {
            return new Book(title, author, isbn, genre);
        }
        // Add more types if needed
        return null;
    }
}