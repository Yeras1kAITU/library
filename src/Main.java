public class Main {
    public static void main(String[] args) {
        Library library = Library.createLibraryWithUserInput();
        library.addItem("Effective Java", "Joshua Bloch", "123456789", "Programming");
        library.addItem("Clean Code", "Robert C. Martin", "987654321", "Programming");

        LibraryUser user = new LibraryUser("Alice", "U001");
        user.borrowItem("Java Concurrency", "Brian Goetz", "1122334455", "Programming");

        System.out.println("\nDisplaying Library Info:");
    }
}

