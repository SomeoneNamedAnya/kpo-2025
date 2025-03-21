package org.example.commands.export;

import org.example.interfaces.ICommand;
import org.example.interfaces.IManager;
import org.example.params.ReportType;
import org.example.services.BalanceService;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

public class CsvExportCommand implements ICommand {
    private final IManager manager;
    String filename;
    Scanner scan;
    public CsvExportCommand(IManager manager, String filename) {
        scan = new Scanner(System.in);
        this.manager = manager;
        this.filename = filename;
    }
    @Override
    public void execute() {
        try {

            Writer writer = new FileWriter(filename);
            manager.export(ReportType.CSV, writer);
            writer.close();

        } catch (Exception e) {
            System.out.println("Что то пошло не так...");
        }
    }
}
