import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

abstract class LibraryItem {
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

    public abstract void displayInfo();

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
    private ArrayList<LibraryUser> users;

    public Library(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<LibraryItem> getItems() {
        return items;
    }

    public ArrayList<LibraryUser> getUsers() {
        return users;
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void addUser(LibraryUser user) {
        users.add(user);
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

    public void displayLibraryInfo() {
        System.out.println("Library Name: " + name);
        System.out.println("\n________________Library Items________________");
        for (LibraryItem item : items) {
            item.displayInfo();
            System.out.println();
        }

        System.out.println("\n________________Library Users________________");
        for (LibraryUser user : users) {
            user.displayUserInfo();
        }
    }
}