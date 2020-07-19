package by.galdovich.task_6.model.dao;

import by.galdovich.task_6.model.entity.Book;
import by.galdovich.task_6.model.exception.DaoException;

import java.util.List;

public interface BookListDao {

    void addBook(Book book) throws DaoException;
    void removeBook(Book book) throws DaoException;
    List<Book> findByTitle(String title);
    List<Book> findByTitleWords(String title);
    List<Book> findByYear(String maxYear, String minYear);
    List<Book> findByYear(String year);
    List<Book> findByPages(String maxPages, String minPages);
    List<Book> findByPages(String pages);
    List<Book> findByISBN(String isbn);
    List<Book> findByAuthor(String author);
    List<Book> findAll();
    List<Book> sortByTitle();
    List<Book> sortByYear();
    List<Book> sortByPages();
    List<Book> sortByAuthors();
    List<Book> sortByISBN();
}
