import java.util.ArrayList;
import java.util.List;

class LibraryUser {
    private String name;
    private String userId;
    private List<LibraryItem> borrowedItems;

    public LibraryUser(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.borrowedItems = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public List<LibraryItem> getBorrowedItems() {
        return borrowedItems;
    }

    public void borrowItem(LibraryItem item) {
        borrowedItems.add(item);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User ID: ").append(userId).append(", Name: ").append(name).append("\n");
        sb.append("Borrowed Books:\n");
        if (borrowedItems.isEmpty()) {
            sb.append("  No books borrowed.\n");
        } else {
            for (LibraryItem item : borrowedItems) {
                sb.append("  - ").append(item.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}