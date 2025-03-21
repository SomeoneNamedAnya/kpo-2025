package org.example.interfaces;

import org.example.domains.BankAccount;
import org.example.domains.Category;
import org.example.domains.Operation;

import java.io.IOException;
import java.io.Writer;
import java.util.List;


public interface ReportExporter {
    public void BankAccountExport(List<BankAccount> bankAccounts, Writer writer) throws IOException;
    public void OperationExport(List<Operation> operations, Writer writer) throws IOException;
    public void CategoryExport(List<Category> categories, Writer writer) throws IOException;

}
