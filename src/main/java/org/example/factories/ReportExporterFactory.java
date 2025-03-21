package org.example.factories;

import org.example.exporter.CsvReportExporter;
import org.example.exporter.JsonReportExporter;
import org.example.exporter.YamlReportExporter;
import org.example.interfaces.ReportExporter;
import org.example.params.ReportType;
import org.springframework.stereotype.Component;

@Component
public class ReportExporterFactory {
    public ReportExporter create(ReportType reportType) {
        return switch (reportType) {
            case CSV -> new CsvReportExporter();
            case YAML -> new YamlReportExporter();
            case JSON -> new JsonReportExporter();
            default -> throw new IllegalArgumentException("Unsupported format: " + reportType);
        };
    }
}
