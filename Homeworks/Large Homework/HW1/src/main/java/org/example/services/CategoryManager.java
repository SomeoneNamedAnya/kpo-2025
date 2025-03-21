package org.example.services;

import lombok.Setter;
import org.example.domains.Category;
import org.example.factories.ReportExporterFactory;
import org.example.factories.CategoryFactory;
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
public class CategoryManager implements IManager {
    @Autowired
    private ReportExporterFactory reportExporterFactory;
    @Autowired
    private CategoryFactory categoryFactory;

    @Setter
    private IConnection connection;

    public void importDate(AbstractImporter abstractImporter, String filename) {
        abstractImporter.importCategory(categoryFactory, connection.getCategory(), filename);
    }
    public int makeNewCategory(String name, String type) {
        return connection.insertCategory(categoryFactory.create(type, name));
    }

    public boolean deleteCategory(int id) {
        List<Category> realInd = connection.getCategory()
                                .stream().filter(x -> x.getId() == id)
                                .toList();
        if (realInd.size() != 1) {
            return false;
        }

        connection.getCategory().remove(realInd.get(0));

        return true;

    }

    public boolean refactorCategory(int id, String newName) {
        List<Category> realInd = connection.getCategory()
                .stream().filter(x -> x.getId() == id)
                .toList();
        if (realInd.size() != 1) {
            return false;
        }

        realInd.get(0).setName(newName);

        return true;
    }

    public List<Category> getAllCategory() {
        return connection.getCategory();
    }

    public void export(ReportType reportType, Writer writer) {
        try {

            ReportExporter exporter = reportExporterFactory.create(reportType);
            exporter.CategoryExport(connection.getCategory(), writer);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public boolean saveAll(){
        return connection.saveAllCategories();
    }

}
