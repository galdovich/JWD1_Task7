package by.galdovich.task_6.controller;

import by.galdovich.task_6.controller.exception.CommandException;
import by.galdovich.task_6.controller.provider.ActionProvider;
import by.galdovich.task_6.controller.command.ActionCommand;
import by.galdovich.task_6.model.entity.Book;

import java.util.List;
import java.util.Map;

public class Controller {
    private static Controller controllerInstance = new Controller();

    private Controller() {
    }

    public static Controller getInstance() {
        return controllerInstance;
    }

    public List<Book> processRequest(String action, Map<String, String> values) throws CommandException {
        ActionCommand command;
        try {
            command = ActionProvider.defineCommand(action);
        }catch (IllegalArgumentException exc){
            throw new CommandException("Invalid command");
        }
        List<Book> result = command.execute(values);
        return result;
    }
}
