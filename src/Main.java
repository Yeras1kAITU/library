import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Library library = Library.createLibraryWithUserInput();
        library.addItem("Effective Java", "Joshua Bloch", "123456789");
        library.addItem("Clean Code", "Robert C. Martin", "987654321");

        LibraryUser user = new LibraryUser("Alice", "U001");
        user.borrowItem("Java Concurrency", "Brian Goetz", "1122334455");

        System.out.println("\nDisplaying Library Info:");
        library.displayLibraryInfo();
    }
}

