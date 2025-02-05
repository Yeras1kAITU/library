import core.Book;
import core.Library;
import core.LibraryUser;
import persistence.ItemRepositoryImpl;
import persistence.UserRepositoryImpl;

import java.util.Scanner;

public class NewMain {
    public static void main(String[] args) {
        ItemRepositoryImpl itemRepository = new ItemRepositoryImpl();
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        Library library = new Library("My Library", itemRepository, userRepository);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add a Book");
            System.out.println("2. Add a User");
            System.out.println("3. Show All Books");
            System.out.println("4. Show All Users");
            System.out.println("5. Search Books by Title");
            System.out.println("6. Filter Books by Genre");
            System.out.println("7. Delete a User by ID");
            System.out.println("8. Delete a Book by ISBN");
            System.out.println("9. Show User borrowed books");
            System.out.println("10. Assign Book to User");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nEnter Book Details:");
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();

                    library.addBook(new Book(title, author, isbn, genre));
                    break;

                case 2:
                    System.out.println("\nEnter User Details:");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("User ID: ");
                    String userId = scanner.nextLine();

                    library.addUser(new LibraryUser(name, userId));
                    break;

                case 3:
                    System.out.println("\nBooks in the Library:");
                    library.getAllBooks().forEach(Book::displayInfo);
                    break;

                case 4:
                    System.out.println("\nUsers in the Library:");
                    library.getAllUsers();
                    break;

                case 5:
                    System.out.print("\nEnter keyword to search books by title: ");
                    String keyword = scanner.nextLine();
                    library.searchBooksByTitle(keyword).forEach(Book::displayInfo);
                    break;

                case 6:
                    System.out.print("\nEnter genre to filter books: ");
                    String filterGenre = scanner.nextLine();
                    library.filterBooksByGenre(filterGenre).forEach(Book::displayInfo);
                    break;

                case 7:
                    System.out.print("\nEnter User ID to delete: ");
                    String deleteUserId = scanner.nextLine();
                    library.deleteUserById(deleteUserId);
                    break;

                case 8:
                    System.out.print("\nEnter ISBN to delete book: ");
                    String deleteIsbn = scanner.nextLine();
                    library.deleteBookByIsbn(deleteIsbn);
                    break;

                case 9:
                    System.out.print("\nEnter User ID to see borrowed books: ");
                    String getUserBorrowedBooks = scanner.nextLine();
                    library.getUserBorrowedBooks(getUserBorrowedBooks).forEach(Book::displayInfo);
                    break;

                case 10:
                    System.out.print("Enter User ID: ");
                    String borrowerId = scanner.nextLine();
                    System.out.print("Enter ISBN of Book: ");
                    String bookIsbn = scanner.nextLine();
                    library.assignBorrowedBook(borrowerId, bookIsbn);
                    break;

                case 11:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}