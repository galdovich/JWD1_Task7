package by.galdovich.task_6.service.type;

public enum TagType {
    TITLE("title"),
    YEAR("year"),
    PAGES("pages"),
    ISBN("isbn"),
    AUTHORS("authors");

    private final String tagName;

    TagType(String tagName){
        this.tagName = tagName;
    }

    public String getTagName(){
        return tagName;
    }
}
