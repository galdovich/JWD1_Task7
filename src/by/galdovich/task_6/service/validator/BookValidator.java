package by.galdovich.task_6.service.validator;

import by.galdovich.task_6.service.parser.BookParser;
import by.galdovich.task_6.service.type.TagType;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookValidator {
    private static final int MAX_BOOK_YEAR;
    private static final int MIN_BOOK_YEAR = 1700;
    private static final int MIN_BOOK_PAGES = 0;
    private static final int MAX_BOOK_PAGES = 1000;
    private static final String ISBN_REGEX = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})" +
            "[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";

    static {
        MAX_BOOK_YEAR = LocalDateTime.now().getYear();
    }

    public boolean validateAllBookTag(Map<String, String> values) {
        boolean result = false;
        if (isValidTitle(values.get(TagType.TITLE.getTagName())) &&
                isValidYear(values.get(TagType.YEAR.getTagName())) &&
                isValidPages(values.get(TagType.PAGES.getTagName())) &&
                isValidISBN(values.get(TagType.ISBN.getTagName())) &&
                isValidAuthors(values.get(TagType.AUTHORS.getTagName()))){
            result = true;
        }
        return result;
    }

    public boolean validateFindBookTag(Map<String, String> values) {
        BookParser bookParser = new BookParser();
        boolean result = false;
        TagType bookTag = bookParser.parseBookTag(values);
        switch (bookTag){
            case TITLE:
                result = isValidTitle(values.get(TagType.TITLE.getTagName()));
                return result;
            case YEAR:
                result = isValidYear(values.get(TagType.YEAR.getTagName()));
                return result;
            case PAGES:
                result = isValidPages(values.get(TagType.PAGES.getTagName()));
                return result;
            case ISBN:
                result = isValidISBN(values.get(TagType.ISBN.getTagName()));
                return result;
            case AUTHORS:
                result = isValidAuthors(values.get(TagType.AUTHORS.getTagName()));
                return result;
        }
        return result;
    }

    private boolean isValidTitle(String title) {
        boolean result = true;
        if (title.isEmpty() || title == null){
            result = false;
        }
        return result;
    }

    private boolean isValidYear(String intputYear) {
        boolean result = false;
        int year;
        if (intputYear == null || intputYear.isEmpty()){
            return false;
        }
        try{
            year = Integer.parseInt(intputYear);
        }catch (NumberFormatException exc){
            return false;
        }
        if (year <= MAX_BOOK_YEAR && year > MIN_BOOK_YEAR) {
            result = true;
        }
        return result;
    }

    private boolean isValidPages(String inputPages) {
        boolean result = false;
        int pages;
        if (inputPages == null || inputPages.isEmpty()){
            return false;
        }
        try {
            pages = Integer.parseInt(inputPages);
        }catch (NumberFormatException exc){
            return false;
        }
        if (pages <= MAX_BOOK_PAGES && pages > MIN_BOOK_PAGES){
            result = true;
        }
        return result;
    }

    private boolean isValidISBN(String isbn) {
        boolean result;
        if (isbn == null || isbn.isEmpty()){
            return false;
        }
        Pattern pattern = Pattern.compile(ISBN_REGEX);
        Matcher matcher = pattern.matcher(isbn);
        result = matcher.find();
        return result;
    }

    private boolean isValidAuthors(String author) {
        boolean result = true;
        if (author == null || author.isEmpty()){
            return false;
        }
        return result;
    }
}
