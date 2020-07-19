package by.galdovich.task_6.controller.type;

import by.galdovich.task_6.controller.command.ActionCommand;
import by.galdovich.task_6.controller.command.impl.AddCommand;
import by.galdovich.task_6.controller.command.impl.FindCommand;
import by.galdovich.task_6.controller.command.impl.RemoveCommand;
import by.galdovich.task_6.controller.command.impl.SortCommand;

public enum CommandType {
    ADD{
        {
            this.command = new AddCommand();
        }
    },
    REMOVE{
        {
            this.command = new RemoveCommand();
        }
    },
    FIND{
        {
            this.command = new FindCommand();
        }
    },
    SORT{
        {
            this.command = new SortCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCommand(){
        return command;
    }
}
