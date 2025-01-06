import java.util.ArrayList;

class LibraryUser {
    private String name;
    private String userId;
    private ArrayList<LibraryItem> borrowedItems;

    public LibraryUser(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.borrowedItems = new ArrayList<>();
    }

    public void borrowItem(LibraryItem item) {
        borrowedItems.add(item);
    }

    public void borrowItem(String title, String author, String isbn) {
        borrowedItems.add(new Book(title, author, isbn));
    }

    public void displayBorrowedItems() {
        if (borrowedItems.isEmpty()) {
            System.out.println("No items borrowed.");
        } else {
            for (LibraryItem item : borrowedItems) {
                item.displayInfo();
            }
        }
    }

    public void displayUserInfo() {
        System.out.println("User Name: " + name + ", User ID: " + userId);
        displayBorrowedItems();
    }
}
