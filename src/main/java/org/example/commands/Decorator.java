package org.example.commands;

import org.example.interfaces.ICommand;

public class Decorator implements ICommand {
    private final ICommand command;
    public Decorator(ICommand command) {
        this.command = command;
    }
    @Override
    public void execute() {
        long startTime = System.currentTimeMillis();
        command.execute();
        long endTime = System.currentTimeMillis();
        System.out.println("------------------------------------------------------");
        System.out.println("Команда выполнялась: " + (endTime - startTime) + " ms");
    }
}
