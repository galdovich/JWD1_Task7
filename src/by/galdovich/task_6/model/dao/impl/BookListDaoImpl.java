package by.galdovich.task_6.model.dao.impl;

import by.galdovich.task_6.model.dao.BookListDao;
import by.galdovich.task_6.model.entity.Book;
import by.galdovich.task_6.model.entity.Library;
import by.galdovich.task_6.model.exception.DaoException;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class BookListDaoImpl implements BookListDao {
    private final Library library;
    private final List<Book> books;

    public BookListDaoImpl() {
        this.library = Library.getInstance();
        this.books = library.findAll();
    }

    @Override
    public void addBook(Book book) throws DaoException {
        if (findSameBook(book)){
            throw new DaoException("This book already exists");
        }
        if (library.isFull()){
            throw new DaoException("Library is full");
        }
        library.add(book);
    }

    @Override
    public void removeBook(Book book) throws DaoException {
        if (!findSameBook(book)){
            throw new DaoException("This book already exists");
        }
        library.remove(book);
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> resultList = new ArrayList<>();
        for (Book book: books){
            if (book.getTitle().equals(title)){
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findByTitleWords(String title) {
        List<Book> resultList = new ArrayList<>();
        for (Book book: books){
            if (book.getTitle().contains(title)){
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findByYear(String year) {
        List<Book> resultList = new ArrayList<>();
        for (Book book: books){
            if (String.valueOf(book.getYear()).equals(year)){
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findByYear(String maxYear, String minYear) {
        List<Book> resultList = new ArrayList<>();
        for (Book book: books){
            if (book.getYear() <= Integer.parseInt(maxYear) && book.getYear() >= Integer.parseInt(minYear)){
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findByPages(String pages) {
        List<Book> resultList = new ArrayList<>();
        for (Book book: books){
            if (String.valueOf(book.getPages()).equals(pages)){
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findByPages(String maxPages, String minPages) {
        List<Book> resultList = new ArrayList<>();
        for (Book book: books){
            if (book.getPages() <= Integer.parseInt(maxPages) && book.getPages() >= Integer.parseInt(minPages)){
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findByISBN(String isbn) {
        List<Book> resultList = new ArrayList<>();
        for (Book book: books){
            if (book.getISBN().equals(isbn)){
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> resultList = new ArrayList<>();
        for (Book book: books){
            if (book.getAuthor().contains(author)){
                resultList.add(book);
            }
        }
        return resultList;
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public List<Book> sortByTitle() {
        List<Book> resultList;
        resultList = books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
        return resultList;
    }

    @Override
    public List<Book> sortByYear() {
        List<Book> resultList;
        resultList = books.stream()
                .sorted(Comparator.comparing(Book::getYear))
                .collect(Collectors.toList());
        return resultList;
    }

    @Override
    public List<Book> sortByPages() {
        List<Book> resultList;
        resultList = books.stream()
                .sorted(Comparator.comparing(Book::getPages))
                .collect(Collectors.toList());
        return resultList;
    }

    @Override
    public List<Book> sortByAuthors() {
        List<Book> resultList;
        resultList = books.stream()
                .sorted(Comparator.comparing(Book::getFirstAuthor))
                .collect(Collectors.toList());
        return resultList;
    }

    @Override
    public List<Book> sortByISBN() {
        List<Book> resultList;
        resultList = books.stream()
                .sorted(Comparator.comparing(Book::getISBN))
                .collect(Collectors.toList());
        return resultList;
    }

    /**
     * Compares all library book fields with searching book fields.
     * Author field compares by first value.
     * @param book Searching book
     */
    private boolean findSameBook(Book book) {
        boolean result = false;
        for (Book libraryBook: books){
            if (libraryBook.getTitle().equals(book.getTitle()) && libraryBook.getYear() == book.getYear() &&
            libraryBook.getPages() == book.getPages() && libraryBook.getISBN().equals(book.getISBN()) &&
            libraryBook.getAuthor().get(0).equals(book.getAuthor().get(0))){
                result = true;
            }

        }
        return result;
    }
}
