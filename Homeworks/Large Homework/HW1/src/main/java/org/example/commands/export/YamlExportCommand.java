package org.example.commands.export;

import org.example.interfaces.ICommand;
import org.example.interfaces.IManager;
import org.example.params.ReportType;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

public class YamlExportCommand implements ICommand {
    private final IManager manager;
    String filename;
    Scanner scan;
    public YamlExportCommand(IManager manager, String filename) {
        scan = new Scanner(System.in);
        this.manager = manager;
        this.filename = filename;
    }
    @Override
    public void execute() {
        try {

            Writer writer = new FileWriter(filename);
            manager.export(ReportType.YAML, writer);
            writer.close();

        } catch (Exception e) {
            System.out.println("Что то пошло не так...");
        }
    }
}
