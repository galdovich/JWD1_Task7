package test.by.galdovich.task_6.model.dao.impl;

import by.galdovich.task_6.model.dao.impl.BookListDaoImpl;
import by.galdovich.task_6.model.entity.Book;
import by.galdovich.task_6.model.entity.Library;
import java.util.List;
import java.util.ArrayList;
import by.galdovich.task_6.model.exception.DaoException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BookListDaoImplTest {
    private BookListDaoImpl bookListDao;
    private Library library = Library.getInstance();
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book newBook;

    @BeforeTest
    public void setUp() {
        bookListDao = new BookListDaoImpl();
        book1 = new Book("Java: A Beginner’s Guide (Sixth Edition)", 2014,619,
                "0071809252", "Herbert Schildt");
        book2 = new Book("Harry Potter and the Philosopher's Stone", 1994,52,
                "0-7475-3269-9", "Joanne Rowling");
        book3 = new Book("Astrophysics for People in a Hurry", 2017,244,
                "0393609391", "Neil de Grasse Tyson");
        book4 = new Book("The Naked Ape: A Zoologist's Study of the Human Animal", 1969,252,
                "0385334303", "Desmond Morris");
        newBook = new Book("Astrophysics", 1949,52,
                "0393609731", "Tyson");
        library.add(book1);
        library.add(book2);
        library.add(book3);
        library.add(book4);
    }

    @Test
    public void testAddBookPositive() {
        try {
            List<Book> expected = new ArrayList<>();
            expected.add(book1);
            expected.add(book2);
            expected.add(book3);
            expected.add(book4);
            expected.add(newBook);
            bookListDao.addBook(newBook);
            assertEquals(expected, library.findAll());
        }catch (DaoException exc){}
    }

    @Test
    public void testRemoveBook(){
        try {
            List<Book> expected = new ArrayList<>();
            expected.add(book1);
            expected.add(book2);
            expected.add(book3);
            expected.add(book4);
            bookListDao.removeBook(newBook);
            assertEquals(expected, library.findAll());
        }catch (DaoException exc){}
    }

    @Test
    public void testFindByTitlePositive() {
        List<Book> expected = new ArrayList<>();
        expected.add(book2);
        List<Book> actual = bookListDao.findByTitle("Harry Potter and the Philosopher's Stone");
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByTitleNegative() {
        List<Book> expected = new ArrayList<>();
        expected.add(book1);
        List<Book> actual = bookListDao.findByTitle("Harry Potter and the Philosopher's Stone");
        assertNotEquals(actual, expected);
    }

    @Test
    public void testFindByYearPositive() {
        List<Book> expected = new ArrayList<>();
        expected.add(book1);
        List<Book> actual = bookListDao.findByYear("2014");
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByYearNegative() {
        List<Book> expected = new ArrayList<>();
        expected.add(book2);
        List<Book> actual = bookListDao.findByYear("2014");
        assertNotEquals(actual, expected);
    }

    @Test
    public void testFindByYearRangePositive() {
        List<Book> expected = new ArrayList<>();
        expected.add(book1);
        expected.add(book3);
        List<Book> actual = bookListDao.findByYear("2020", "2000");
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByYearRangeNegative() {
        List<Book> expected = new ArrayList<>();
        expected.add(book1);
        List<Book> actual = bookListDao.findByYear("2020", "2000");
        assertNotEquals(actual, expected);
    }

    @Test
    public void testFindByPagesPositive() {
        List<Book> expected = new ArrayList<>();
        expected.add(book3);
        List<Book> actual = bookListDao.findByPages("244");
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByPagesNegative() {
        List<Book> expected = new ArrayList<>();
        expected.add(book1);
        List<Book> actual = bookListDao.findByPages("244");
        assertNotEquals(actual, expected);
    }

    @Test
    public void testFindByPagesRangePositive() {
        List<Book> expected = new ArrayList<>();
        expected.add(book1);
        expected.add(book4);
        List<Book> actual = bookListDao.findByPages("700", "250");
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByPagesRangeNegative() {
        List<Book> expected = new ArrayList<>();
        expected.add(book1);
        List<Book> actual = bookListDao.findByPages("700", "250");
        assertNotEquals(actual, expected);
    }

    @Test
    public void testFindByAuthorPositive() {
        List<Book> expected = new ArrayList<>();
        expected.add(book4);
        List<Book> actual = bookListDao.findByAuthor("Desmond Morris");
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByAuthorNegative() {
        List<Book> expected = new ArrayList<>();
        expected.add(book1);
        List<Book> actual = bookListDao.findByAuthor("Desmond Morris");
        assertNotEquals(actual, expected);
    }

    @Test
    public void testFindAllPositive() {
        try{
            List<Book> expected = new ArrayList<>();
            expected.add(book1);
            expected.add(book2);
            expected.add(book3);
            expected.add(book4);
            bookListDao.removeBook(newBook);
            List<Book> actual = bookListDao.findAll();
            assertEquals(actual, expected);
        }catch (DaoException exc){fail();}
    }

    @Test
    public void testFindAllNegative() {
        List<Book> expected = new ArrayList<>();
        expected.add(book1);
        List<Book> actual = bookListDao.findAll();
        assertNotEquals(actual, expected);
    }

    @Test
    public void testSortByTitlePositive() {
        List<Book> expected = new ArrayList<>();
        expected.add(book3);
        expected.add(book2);
        expected.add(book1);
        expected.add(book4);
        List<Book> actual = bookListDao.sortByTitle();
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByTitleNegative() {
        List<Book> expected = new ArrayList<>();
        expected.add(book1);
        expected.add(book2);
        expected.add(book3);
        expected.add(book4);
        List<Book> actual = bookListDao.sortByTitle();
        assertNotEquals(actual, expected);
    }

    @Test
    public void testSortByYearPositive() {
        List<Book> expected = new ArrayList<>();
        expected.add(book4);
        expected.add(book2);
        expected.add(book1);
        expected.add(book3);
        List<Book> actual = bookListDao.sortByYear();
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByYearNegative() {
        List<Book> expected = new ArrayList<>();
        expected.add(book1);
        expected.add(book2);
        expected.add(book3);
        expected.add(book4);
        List<Book> actual = bookListDao.sortByYear();
        assertNotEquals(actual, expected);
    }

    @Test
    public void testSortByPagesPositive() {
        List<Book> expected = new ArrayList<>();
        expected.add(book2);
        expected.add(book3);
        expected.add(book4);
        expected.add(book1);
        List<Book> actual = bookListDao.sortByPages();
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByPagesNegative() {
        List<Book> expected = new ArrayList<>();
        expected.add(book1);
        expected.add(book2);
        expected.add(book3);
        expected.add(book4);
        List<Book> actual = bookListDao.sortByPages();
        assertNotEquals(actual, expected);
    }

    @Test
    public void testSortByAuthorsPositive() {
        List<Book> expected = new ArrayList<>();
        expected.add(book4);
        expected.add(book1);
        expected.add(book2);
        expected.add(book3);
        List<Book> actual = bookListDao.sortByAuthors();
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByAuthorsNegative() {
        List<Book> expected = new ArrayList<>();
        expected.add(book1);
        expected.add(book2);
        expected.add(book3);
        expected.add(book4);
        List<Book> actual = bookListDao.sortByAuthors();
        assertNotEquals(actual, expected);
    }

    @Test
    public void testSortByISBNPositive() {
        List<Book> expected = new ArrayList<>();
        expected.add(book2);
        expected.add(book1);
        expected.add(book4);
        expected.add(book3);
        List<Book> actual = bookListDao.sortByISBN();
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByISBNNegative() {
        List<Book> expected = new ArrayList<>();
        expected.add(book1);
        expected.add(book2);
        expected.add(book3);
        expected.add(book4);
        List<Book> actual = bookListDao.sortByISBN();
        assertNotEquals(actual, expected);
    }
}