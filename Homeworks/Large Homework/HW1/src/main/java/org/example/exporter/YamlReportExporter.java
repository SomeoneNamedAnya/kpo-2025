package org.example.exporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.domains.BankAccount;
import org.example.domains.Category;
import org.example.domains.Operation;
import org.example.interfaces.ReportExporter;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class YamlReportExporter implements ReportExporter {

    private final YAMLMapper objectMapper = new YAMLMapper();
    @Override
    public void BankAccountExport(List<BankAccount> bankAccounts, Writer writer) throws IOException {
        objectMapper.writeValue(writer, bankAccounts);
    }

    @Override
    public void OperationExport(List<Operation> operations, Writer writer) throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.writeValue(writer, operations);
    }

    @Override
    public void CategoryExport(List<Category> categories, Writer writer) throws IOException {
        objectMapper.writeValue(writer, categories);
    }
}
