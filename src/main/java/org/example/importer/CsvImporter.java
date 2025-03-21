package org.example.importer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CsvImporter extends AbstractImporter{
    @Override
    public ArrayList<Map<String, String>> readFile(String filename) {
        try {
            ArrayList<Map<String, String>> date = new ArrayList<>();
            Reader file = new FileReader(filename);

            CSVParser csvDate = CSVFormat.DEFAULT.parse(file);
            String[] header = null;
            boolean isFirst = true;
            for (CSVRecord csvLine : csvDate) {
                if (isFirst) {
                    header = csvLine.values();
                    isFirst = false;
                    continue;
                }
                Map<String, String> item = new HashMap<>();
                for (int i = 0; i < header.length; i++) {

                    item.put(header[i], csvLine.values()[i]);
                }

                date.add(item);
            }
            return date;
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}
