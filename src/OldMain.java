import java.util.List;

public class OldMain {
    public static void main(String[] args) {
        Book book1 = new Book("Java Programming", "Almas Alkhan", "8956421");
        Book book2 = new Book("Python Basics", "Aizat Nurtay", "67890");
        Book book3 = new Book("C++ Programming", "Alisher Akhmet", "12345");

        LibraryUser user1 = new LibraryUser("Yernar", "U001");
        LibraryUser user2 = new LibraryUser("Roberto", "U002");

        Library library = new Library("Astana's Central Library");
        library.addItem(book1);
        library.addItem(book2);
        library.addItem(book3);
        library.addUser(user1);
        library.addUser(user2);

        user1.borrowBook(book1);
        user1.borrowBook(book2);

        library.displayLibraryInfo();

        System.out.println("\nSearching for 'Java':");
        List<LibraryItem> searchResults = library.searchItems("Java");
        searchResults.forEach(System.out::println);

        System.out.println("\nSorting items by title:");
        List<LibraryItem> sortedItems = library.sortItemsByTitle();
        sortedItems.forEach(System.out::println);

        System.out.println("\nDisplaying borrowed books for User 1:");
        user1.displayBorrowedBooks();
    }
}