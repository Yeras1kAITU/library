import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = Library.createLibraryWithUserInput();

        System.out.println("\nLibrary created successfully. Displaying library info:");
        library.displayLibraryInfo();

        System.out.print("\nDo you want to add a new user? (yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            LibraryUser newUser = LibraryUser.createUserWithInput();
            library.addUser(newUser);
            System.out.println("User added successfully. Updated library info:");
            library.displayLibraryInfo();
        }
    }
}