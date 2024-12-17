public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("Java Programming", "Almas Alkhan", "8956421");
        Book book2 = new Book("Python Basics", "Aizat Nurtay", "67890");
        Book book3 = new Book("Java Programming", "Alisher Akhmet", "12345"); // Duplicate ISBN to test comparison

        LibraryUser user1 = new LibraryUser("Yernar", "U001");
        LibraryUser user2 = new LibraryUser("Roberto", "U002");

        Library library = new Library("Astana's Central Library");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addUser(user1);
        library.addUser(user2);

        library.displayLibraryInfo();

        System.out.println("\nComparing Book 1 and Book 3: ");
        if (book1.equals(book3)) {
            System.out.println("The books are the same.");
        } else {
            System.out.println("The books are different.");
        }
    }
}
