

import java.util.Stack;



public class CommandInvoker{
    Stack<Command> commands;

    public CommandInvoker(){
        commands = new Stack<Command>();
    }

    public void executeCmd(Command command){
        command.execute();
        commands.push(command);
    }
    public void undoCmd(){
        if(commands.empty()) return;
        Command command = commands.pop();
        command.undo();
    }
}
