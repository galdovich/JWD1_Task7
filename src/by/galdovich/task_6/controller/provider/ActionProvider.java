package by.galdovich.task_6.controller.provider;

import by.galdovich.task_6.controller.command.ActionCommand;
import by.galdovich.task_6.controller.type.CommandType;
import by.galdovich.task_6.controller.exception.CommandException;

public class ActionProvider {
    public static ActionCommand defineCommand(String action) throws CommandException {
        ActionCommand command;
        if (action == null || action.isEmpty()){
            throw new CommandException("Invalid command values");
        }
        CommandType type = CommandType.valueOf(action.toUpperCase());
        command = type.getCommand();
        return command;
    }
}
