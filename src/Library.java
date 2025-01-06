import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

interface Displayable {
    void displayInfo();
}

abstract class LibraryItem implements Displayable {
    private String title;
    private String id;

    public LibraryItem(String title, String id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", ID: " + id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LibraryItem that = (LibraryItem) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

class Library {
    private String name;
    private ArrayList<LibraryItem> items;
    private HashSet<LibraryUser> users;

    public Library(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.users = new HashSet<>();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    // Overloaded addItem method (Static Polymorphism)
    public void addItem(String title, String author, String isbn) {
        items.add(new Book(title, author, isbn));
    }

    public boolean addUser(LibraryUser user) {
        return users.add(user);
    }

    public List<LibraryItem> searchItems(String keyword) {
        return items.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<LibraryItem> sortItemsByTitle() {
        return items.stream()
                .sorted(Comparator.comparing(LibraryItem::getTitle))
                .collect(Collectors.toList());
    }

    public static Library createLibraryWithUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter library name: ");
        String libraryName = scanner.nextLine();
        Library library = new Library(libraryName);

        System.out.print("Enter number of books to add: ");
        int numBooks = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numBooks; i++) {
            System.out.println("Enter details for book " + (i + 1) + ":");
            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Author: ");
            String author = scanner.nextLine();
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();

            library.addItem(new Book(title, author, isbn));
        }

        System.out.print("Enter number of users to add: ");
        int numUsers = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numUsers; i++) {
            System.out.println("Enter details for user " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("User ID: ");
            String userId = scanner.nextLine();

            library.addUser(new LibraryUser(name, userId));
        }

        return library;
    }

    public void displayLibraryInfo() {
        System.out.println("Library Name: " + name);
        System.out.println("Library Items:");
        for (LibraryItem item : items) {
            item.displayInfo();
        }
        System.out.println("Library Users:");
        for (LibraryUser user : users) {
            user.displayUserInfo();
        }
    }
}

