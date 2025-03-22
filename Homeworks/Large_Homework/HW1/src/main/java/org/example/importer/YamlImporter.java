package org.example.importer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class YamlImporter extends AbstractImporter {
    @Override
    public ArrayList<Map<String, String>> readFile(String filename) {
        try {
            YAMLMapper objectMapper = new YAMLMapper();
            return objectMapper.readValue(
                    new File(filename),
                    new TypeReference<ArrayList<Map<String, String>>>() {}
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
