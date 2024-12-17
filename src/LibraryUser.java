public class LibraryUser {
    private String name;
    private String userId;

    public LibraryUser(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void displayUserInfo() {
        System.out.println("User Name: " + name);
        System.out.println("User ID: " + userId);
    }
}
