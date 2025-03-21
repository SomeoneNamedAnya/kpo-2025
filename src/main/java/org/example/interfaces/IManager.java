package org.example.interfaces;

import org.example.params.ReportType;

import java.io.Writer;

public interface IManager {
    public void export(ReportType reportType, Writer writer);
}
