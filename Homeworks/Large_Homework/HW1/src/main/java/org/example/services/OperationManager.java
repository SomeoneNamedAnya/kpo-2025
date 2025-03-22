package org.example.services;

import lombok.Setter;
import org.example.domains.Category;
import org.example.domains.Operation;
import org.example.factories.ReportExporterFactory;
import org.example.factories.OperationFactory;
import org.example.importer.AbstractImporter;
import org.example.interfaces.IConnection;
import org.example.interfaces.IManager;
import org.example.interfaces.ReportExporter;
import org.example.params.ReportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Writer;
import java.time.LocalDateTime;
import java.util.List;
@Component
public class OperationManager implements IManager {
    @Autowired
    private ReportExporterFactory reportExporterFactory;
    @Autowired
    private OperationFactory operationFactory;

    @Setter
    private IConnection connection;
    public void importDate(AbstractImporter abstractImporter, String filename) {
        abstractImporter.importOperation(operationFactory, connection.getOperation(), filename);
    }
    public int makeNewOperation(String type, int bankAccountId,
                               float amount, LocalDateTime date, int categoryId,
                               String description) {
        return connection.insertOperation(operationFactory.create(type, bankAccountId,
                                                                 amount, date, categoryId,
                                                                 description));
    }

    public boolean deleteOperation(int id) {
        List<Operation> realInd = connection.getOperation()
                .stream().filter(x -> x.getId() == id)
                .toList();
        if (realInd.size() != 1) {
            return false;
        }

        connection.getOperation().remove(realInd.get(0));

        return true;

    }
    public Operation getOperation(int id) {
        List<Operation> temp = connection.getOperation().stream().filter(x -> x.getId() == id).toList();
        if (temp.size() == 1) {
            return temp.get(0);
        }
        return null;
    }
    public boolean refactorOperation(int id, String description) {
        List<Operation> realInd = connection.getOperation()
                .stream().filter(x -> x.getId() == id)
                .toList();
        if (realInd.size() != 1) {
            return false;
        }

        realInd.get(0).setDescription(description);

        return true;
    }

    public List<Operation> getAllOperation() {
        return connection.getOperation();
    }

    public void export(ReportType reportType, Writer writer) {
        try {

            ReportExporter exporter = reportExporterFactory.create(reportType);
            exporter.OperationExport(connection.getOperation(), writer);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public boolean saveAll(){
        return connection.saveAllOperations();
    }

}
