package org.example.services;

import lombok.Setter;
import org.example.domains.BankAccount;
import org.example.factories.ReportExporterFactory;
import org.example.factories.BankAccountFactory;
import org.example.importer.AbstractImporter;
import org.example.interfaces.IConnection;
import org.example.interfaces.IManager;
import org.example.interfaces.ReportExporter;
import org.example.params.ReportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Writer;
import java.util.List;


@Component
public class BankAccountManager implements IManager {
    @Autowired
    private BankAccountFactory bankAccountFactory;
    @Autowired
    private ReportExporterFactory reportExporterFactory;
    @Setter
    private IConnection connection;

    public void importDate(AbstractImporter abstractImporter, String filename) {
        abstractImporter.importBankAccount(bankAccountFactory, connection.getBankAccount(), filename);
    }

    public int makeNewBankAccount(String name, float balance) {
       return connection.insertBankAccount(bankAccountFactory.create(name, balance));
    }

    public boolean deleteCategory(int id) {
        List<BankAccount> realInd = connection.getBankAccount()
                .stream()
                .filter(x -> x.getId() == id)
                .toList();
        if (realInd.size() != 1) {
            return false;
        }

        connection.getBankAccount().remove(realInd.get(0));

        return true;

    }

    public boolean refactorCategory(int id, String newName) {
        List<BankAccount> realInd = connection.getBankAccount()
                .stream()
                .filter(x -> x.getId() == id)
                .toList();
        if (realInd.size() != 1) {
            return false;
        }
        realInd.get(0).setName(newName);
        return true;
    }

    public List<BankAccount> getAllBankAccounts() {
        return connection.getBankAccount();
    }

    public void export(ReportType reportType, Writer writer) {
        try {

            ReportExporter exporter = reportExporterFactory.create(reportType);
            exporter.BankAccountExport(connection.getBankAccount(), writer);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public boolean saveAll(){
        return connection.saveAllBankAccounts();
    }

}
