package by.galdovich.task_6.model.entity;
import java.util.List;
import java.util.ArrayList;

public class Library {
    private static Library libraryInstance = new Library();
    private List<Book> books = new ArrayList<>();
    private static final int LIBRARY_CAPACITY = 3000;

    private Library() {
    }

    public static Library getInstance() {
        return libraryInstance;
    }

    public void add(Book book) {
        books.add(book);
    }

    public void remove(Book book) {
        books.remove(book);
    }

    public List<Book> findAll() {
        return books;
    }

    public void clean(){
        books.clear();
    }

    public boolean isFull() {
        boolean result;
        if (books.size() < LIBRARY_CAPACITY) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Library library = (Library) o;

        return books != null ? books.equals(library.books) : library.books == null;
    }

    @Override
    public int hashCode() {
        return books != null ? books.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Library{");
        sb.append("books=").append(books);
        sb.append('}');
        return sb.toString();
    }
}
