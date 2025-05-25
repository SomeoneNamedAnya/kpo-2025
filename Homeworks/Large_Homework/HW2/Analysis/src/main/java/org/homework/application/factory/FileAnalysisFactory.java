package org.homework.application.factory;

import org.homework.domain.FileAnalysis;
import org.springframework.stereotype.Component;

@Component
public class FileAnalysisFactory {

    public FileAnalysis create(int idFile, int paragraphs, int words, int letters, String location) {
        return new FileAnalysis(idFile, paragraphs, words, letters, location);
    }
}
