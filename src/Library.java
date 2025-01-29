import java.util.List;

class Library {
    private String name;
    private ItemRepository itemRepository;
    private UserRepository userRepository;

    public Library(String name, ItemRepository itemRepository, UserRepository userRepository) {
        this.name = name;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public void getAllUsers() {
        List<LibraryUser> users = userRepository.getAllUsers();
        for (LibraryUser user : users) {
            System.out.println(user);
            System.out.println("---------------------------------------");
        }
    }

    public void addBook(Book book) {
        itemRepository.addBook(book);
    }

    public void deleteBookByIsbn(String isbn) {
        itemRepository.deleteBookByIsbn(isbn);
    }

    public List<Book> getAllBooks() {
        return itemRepository.getAllBooks();
    }

    public List<Book> searchBooksByTitle(String keyword) {
        return itemRepository.searchBooksByTitle(keyword);
    }

    public List<Book> filterBooksByGenre(String genre) {
        return itemRepository.filterBooksByGenre(genre);
    }

    public void addUser(LibraryUser user) {
        userRepository.addUser(user);
    }

    public void deleteUserById(String userId) {
        userRepository.deleteUserById(userId);
    }

    public void assignBorrowedBook(String userId, String isbn) {
        userRepository.assignBorrowedBook(userId, isbn);
    }

    public List<Book> getUserBorrowedBooks(String userId) {
        return userRepository.getUserBorrowedBooks(userId);
    }
}
