import java.util.Scanner;


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
            boolean added = library.addUser(newUser);
            if (added) {
                System.out.println("User added successfully. Updated library info:");
                library.displayLibraryInfo();
            } else {
                System.out.println("User could not be added (duplicate ID).");
            }
        }
    }
}
