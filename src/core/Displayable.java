package core;

public interface Displayable {
    void displayInfo();

    default void displayShortInfo() {
        System.out.println("Displaying short info...");
    }

    static void printInfo(Displayable item) {
        item.displayInfo();
    }
}