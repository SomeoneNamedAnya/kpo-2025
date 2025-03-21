package org.example.exporter;

import org.example.domains.BankAccount;
import org.example.domains.Category;
import org.example.domains.Operation;
import org.example.interfaces.ReportExporter;

import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class CsvReportExporter implements ReportExporter {

    @Override
    public void BankAccountExport(List<BankAccount> bankAccounts, Writer writer) throws IOException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        writer.write("id,name,balance\n");
        bankAccounts.forEach(
                bankAccount-> {
                    try {
                        writer.write(String.format("%d,%s,%s\n",
                                bankAccount.getId(),
                                bankAccount.getName(),
                                df.format(bankAccount.getBalance())));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public void OperationExport(List<Operation> operations, Writer writer) throws IOException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        writer.write("id,type,bankAccountId,categoryId,amount,date,description\n");
        operations.forEach(
                operation-> {
                    try {

                        writer.write(String.format("%d,%s,%d,%d,%s,%s,%s\n",
                                operation.getId(),
                                operation.getType(),
                                operation.getBankAccountId(),
                                operation.getCategoryId(),
                                df.format(operation.getAmount()),
                                operation.getDate().toString(),
                                operation.getDescription()));
                    } catch (IOException e) {

                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public void CategoryExport(List<Category> categories, Writer writer) throws IOException {
        writer.write("id,name,type\n");
        categories.forEach(
                category-> {
                    try {
                        writer.write(String.format("%d,%s,%s\n",
                                category.getId(),
                                category.getName(),
                                category.getType()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

    }
}
