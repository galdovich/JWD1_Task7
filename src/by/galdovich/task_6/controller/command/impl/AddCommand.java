package by.galdovich.task_6.controller.command.impl;

import by.galdovich.task_6.controller.command.ActionCommand;
import by.galdovich.task_6.controller.exception.CommandException;
import by.galdovich.task_6.model.entity.Book;
import by.galdovich.task_6.service.BookService;
import by.galdovich.task_6.service.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddCommand implements ActionCommand {

    @Override
    public List<Book> execute(Map<String, String> values) throws CommandException{
        BookService bookService = new BookService();
        List<Book> result = new ArrayList();
        if(values == null || values.isEmpty()){
            return result;
        }
        try{
            result = bookService.addBook(values);
        }catch (ServiceException exc){
            throw new CommandException(exc.getMessage());
        }
        return result;
    }
}
