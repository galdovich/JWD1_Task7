package by.galdovich.task_6.service.parser;

import by.galdovich.task_6.model.entity.Book;
import by.galdovich.task_6.service.type.TagType;

import java.util.Map;

public class BookParser {
    private static final String SEPARATOR = ",";

    public Book parseBook(Map<String, String> values){
        String title = values.get(TagType.TITLE.getTagName());
        int year = Integer.parseInt(values.get(TagType.YEAR.getTagName()));
        int pages = Integer.parseInt(values.get(TagType.PAGES.getTagName()));
        String isbn = values.get(TagType.ISBN.getTagName());
        String[] authors = values.get(TagType.AUTHORS.getTagName()).split(SEPARATOR);
        return new Book(title, year, pages, isbn, authors);
    }

    public TagType parseBookTag(Map<String, String> value) {
        String key = value.keySet().toString();
        int length = key.length();
        String tag = key.substring(1, length - 1);
        TagType tagType = TagType.valueOf(tag.toUpperCase());
        return tagType;
    }
}
