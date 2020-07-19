package test.by.galdovich.task_6.service;

import by.galdovich.task_6.model.entity.Book;
import by.galdovich.task_6.model.entity.Library;
import by.galdovich.task_6.service.BookService;
import by.galdovich.task_6.service.exception.ServiceException;
import org.testng.annotations.*;

import java.util.Map;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class BookServiceTest {
    private BookService bookService = new BookService();
    private Library library = Library.getInstance();
    private Map<String, String> map1;
    private Map<String, String> map2;
    private Map<String, String> map3;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeTest
    public void setUp() {
        map1 = new HashMap<>();
        map1.put("title", "Java: A Beginner’s Guide (Sixth Edition)");
        map1.put("year", "2014");
        map1.put("pages", "619");
        map1.put("isbn", "0071809252");
        map1.put("authors", "Herbert Schildt");
        map2 = new HashMap<>();
        map2.put("title", "Astrophysics for People in a Hurry");
        map2.put("year", "2017");
        map2.put("pages", "244");
        map2.put("isbn", "0393609391");
        map2.put("authors", "Neil de Grasse Tyson");
        map3 = new HashMap<>();
        map3.put("title", "Hurry");
        map3.put("year", "-124");
        map3.put("pages", "244");
        map3.put("isbn", "0393609391");
        map3.put("authors", "Neil de Grasse Tyson");

        book1 = new Book("Java: A Beginner’s Guide (Sixth Edition)", 2014,619,
           "0071809252", "Herbert Schildt");
        book2 = new Book("Astrophysics for People in a Hurry", 2017,244,
           "0393609391", "Neil de Grasse Tyson");
        book3 = new Book("The Naked Ape: A Zoologist's Study of the Human Animal", 1969,252,
                "0385334303", "Desmond Morris");
    }

    @BeforeClass
    @BeforeMethod
    private void setUpMethod() {
        library.clean();
    }

    @Test
    public void testAddBookPositive() {
        try {
            List<Book> expected = new ArrayList<>();
            expected.add(book1);
            bookService.addBook(map1);
            List<Book> actual = library.findAll();
            assertEquals(expected, actual);
        }catch (ServiceException exc){fail();}
    }

    @Test
    public void testAddBookNegative() {
        try {
            List<Book> expected = new ArrayList<>();
            expected.add(book2);
            List<Book> actual = bookService.addBook(map1);
            assertNotEquals(expected, actual);
        }catch (ServiceException exc){fail();}
    }

    @Test(expectedExceptions = ServiceException.class, expectedExceptionsMessageRegExp = "Invalid book parameters")
    public void testAddBookException() throws ServiceException{
        bookService.addBook(map3);
    }

    @Test
    public void testRemoveBookPositive() {
        try {
            List<Book> expected = new ArrayList<>();
            expected.add(book1);
            bookService.addBook(map1);
            bookService.addBook(map2);
            bookService.removeBook(map2);
            List<Book> actual = library.findAll();
            assertEquals(expected, actual);
        }catch (ServiceException exc){fail();}
    }

    @Test
    public void testRemoveBookNegative() {
        try {
            List<Book> expected = new ArrayList<>();
            expected.add(book2);
            bookService.addBook(map1);
            bookService.addBook(map2);
            bookService.removeBook(map2);
            List<Book> actual = library.findAll();
            assertNotEquals(expected, actual);
        }catch (ServiceException exc){fail();}
    }

    @Test(expectedExceptions = ServiceException.class, expectedExceptionsMessageRegExp = "Invalid book parameters")
    public void testRemoveBookException() throws ServiceException{
        bookService.removeBook(map3);
    }

    @Test
    public void testFindBookByTagTitle() {
        try {
            List<Book> expected = new ArrayList<>();
            expected.add(book1);
            bookService.addBook(map1);
            Map<String, String> title = new HashMap<>();
            title.put("title", "Java: A Beginner’s Guide (Sixth Edition)");
            List<Book> actual = bookService.findBookByTag(title);
            assertEquals(expected, actual);
        }catch (ServiceException exc){fail();}
    }

    @Test
    public void testFindBookByTagYear() {
        try {
            List<Book> expected = new ArrayList<>();
            expected.add(book1);
            bookService.addBook(map1);
            Map<String, String> title = new HashMap<>();
            title.put("year", "2014");
            List<Book> actual = bookService.findBookByTag(title);
            assertEquals(expected, actual);
        }catch (ServiceException exc){fail();}
    }

    @Test
    public void testFindBookByTagPages() {
        try {
            List<Book> expected = new ArrayList<>();
            expected.add(book1);
            bookService.addBook(map1);
            Map<String, String> title = new HashMap<>();
            title.put("pages", "619");
            List<Book> actual = bookService.findBookByTag(title);
            assertEquals(expected, actual);
        }catch (ServiceException exc){fail();}
    }

    @Test
    public void testFindBookByTagISBN() {
        try {
            List<Book> expected = new ArrayList<>();
            expected.add(book1);
            bookService.addBook(map1);
            Map<String, String> title = new HashMap<>();
            title.put("isbn", "0071809252");
            List<Book> actual = bookService.findBookByTag(title);
            assertEquals(expected, actual);
        }catch (ServiceException exc){fail();}
    }

    @Test
    public void testFindBookByTagAuthors() {
        try {
            List<Book> expected = new ArrayList<>();
            expected.add(book1);
            bookService.addBook(map1);
            Map<String, String> title = new HashMap<>();
            title.put("authors", "Herbert Schildt");
            List<Book> actual = bookService.findBookByTag(title);
            assertEquals(expected, actual);
        }catch (ServiceException exc){fail();}
    }

    @DataProvider(name = "testSortBookByTagTitle")
    @Test
    public Object[][] testSortBookByTagTitle() {
        List<Book> sortedList = new ArrayList<>();
        sortedList.add(book2);
        sortedList.add(book1);
        sortedList.add(book3);
        Map<String, String> map = new HashMap<>();
        map.put("title", "");
        return new Object[][]{
                {sortedList, map}
        };
    }

    @Test(dataProvider = "testSortBookByTagTitle")
    public void testSortBookByTagTitle(List<Book> sortedList, Map<String, String> map) {
        try {
            Library.getInstance().add(book1);
            Library.getInstance().add(book2);
            Library.getInstance().add(book3);
            List<Book> actual = bookService.sortBookByTag(map);
            assertEquals(sortedList, actual);
        }catch (ServiceException exc){fail();}
    }

    @DataProvider(name = "testSortBookByTagYear")
    @Test
    public Object[][] testSortBookByTagYear() {
        List<Book> sortedList = new ArrayList<>();
        sortedList.add(book3);
        sortedList.add(book1);
        sortedList.add(book2);
        Map<String, String> map = new HashMap<>();
        map.put("year", " ");
        return new Object[][]{
                {sortedList, map}
        };
    }

    @Test(dataProvider = "testSortBookByTagYear")
    public void testSortBookByTagYear(List<Book> sortedList, Map<String, String> map) {
        try {
            Library.getInstance().add(book1);
            Library.getInstance().add(book2);
            Library.getInstance().add(book3);
            List<Book> actual =  bookService.sortBookByTag(map);;
            assertEquals(sortedList, actual);
        }catch (ServiceException exc){fail();}
    }

    @DataProvider(name = "testSortBookByTagPages")
    @Test
    public Object[][] testSortBookByTagPages() {
        List<Book> sortedList = new ArrayList<>();
        sortedList.add(book2);
        sortedList.add(book3);
        sortedList.add(book1);
        Map<String, String> map = new HashMap<>();
        map.put("pages", " ");
        return new Object[][]{
                {sortedList, map}
        };
    }

    @Test(dataProvider = "testSortBookByTagPages")
    public void testSortBookByTagPages(List<Book> sortedList, Map<String, String> map) {
        try {
            Library.getInstance().add(book1);
            Library.getInstance().add(book2);
            Library.getInstance().add(book3);
            List<Book> actual =  bookService.sortBookByTag(map);;
            assertEquals(sortedList, actual);
        }catch (ServiceException exc){fail();}
    }

    @DataProvider(name = "testSortBookByTagAuthors")
    @Test
    public Object[][] testSortBookByTagAuthors() {
        List<Book> sortedList = new ArrayList<>();
        sortedList.add(book3);
        sortedList.add(book1);
        sortedList.add(book2);
        Map<String, String> map = new HashMap<>();
        map.put("authors", " ");
        return new Object[][]{
                {sortedList, map}
        };
    }

    @Test(dataProvider = "testSortBookByTagAuthors")
    public void testSortBookByTagAuthors(List<Book> sortedList, Map<String, String> map) {
        try {
            Library.getInstance().add(book1);
            Library.getInstance().add(book2);
            Library.getInstance().add(book3);
            List<Book> actual =  bookService.sortBookByTag(map);;
            assertEquals(sortedList, actual);
        }catch (ServiceException exc){fail();}
    }
}