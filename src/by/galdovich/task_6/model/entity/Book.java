package by.galdovich.task_6.model.entity;

import by.galdovich.task_6.util.generator.IdGenerator;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Book {
    private UUID bookId;
    private String title;
    private int year;
    private int pages;
    private String ISBN;
    private List<String> authors;

    public Book() {
        this.title = "";
        this.authors = new ArrayList<>();
        this.ISBN = "";
        this.bookId = IdGenerator.getId();
    }

    public Book(String title, int year, int pages, String ISBN, String... authors) {
        this.title = title;
        this.year = year;
        this.pages = pages;
        this.ISBN = ISBN;
        this.bookId = IdGenerator.getId();
        this.authors = new ArrayList<>();
        this.authors.addAll(Arrays.asList(authors));
    }

    public UUID getId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthor() {
        return authors;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getFirstAuthor() {
        return authors.get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;}

        Book book = (Book) o;

        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (year != book.year) return false;
        if (pages != book.pages) return false;
        if (authors != null ? !authors.equals(book.authors) : book.authors != null) return false;
        return ISBN != null ? ISBN.equals(book.ISBN) : book.ISBN == null;
    }

    @Override
    public int hashCode() {
        int result = bookId != null ? bookId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + pages;
        result = 31 * result + (ISBN != null ? ISBN.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book: ");
        sb.append("title = '").append(title).append('\'');
        sb.append(", authors = ").append(authors);
        sb.append(", year = ").append(year);
        sb.append(", pages = ").append(pages);
        sb.append(", ISBN = '").append(ISBN).append('\'');
        return sb.toString();
    }
}
