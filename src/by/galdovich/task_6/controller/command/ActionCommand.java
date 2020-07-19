package by.galdovich.task_6.controller.command;
import by.galdovich.task_6.controller.exception.CommandException;
import by.galdovich.task_6.model.entity.Book;

import java.util.List;
import java.util.Map;

public interface ActionCommand {
    List<Book> execute(Map<String, String> values) throws CommandException;
}
