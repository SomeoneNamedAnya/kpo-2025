package org.homework.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.homework.domain.FileAnalysis;
import org.homework.domain.RequestToCloud;
import org.homework.application.factory.FileAnalysisFactory;
import org.homework.infrastructure.repository.FileAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class AnalysisService {
    private static final String UPLOAD_DIR = "Cloud_Storage";
    private int name = 1;

    @Autowired
    private FileAnalysisFactory fileAnalysisFactory;
    @Autowired
    private FileAnalysisRepository fileAnalysisRepository;

    public FileAnalysis makeAnalysis(int id, String file) {

        String[] paragraphs = file.split("\\R");
        String[] word = file.trim().split("\\s+");
        int cntChar = 0;
        for (String c : word) {
            cntChar += c.length();
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ObjectMapper mapper = new ObjectMapper();
            String jsonBody = mapper.writeValueAsString(new RequestToCloud(file));
            HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<byte[]> response = restTemplate.exchange(
                    "https://quickchart.io/wordcloud",
                    HttpMethod.POST,
                    request,
                    byte[].class
            );
            if (response.getStatusCode() == HttpStatus.OK) {
                Files.createDirectories(Paths.get(UPLOAD_DIR));
                Path path = Paths.get(UPLOAD_DIR,String.valueOf(name) + ".png");
                name += 1;
                System.out.println(name);
                Files.write(path, response.getBody());
                return fileAnalysisRepository.save(fileAnalysisFactory.create(
                        id,
                        paragraphs.length,
                        word.length,
                        cntChar,
                        path.toString()));
            } else {
                return fileAnalysisRepository.save(fileAnalysisFactory.create(
                        id,
                        paragraphs.length,
                        word.length,
                        cntChar,
                        null));
            }

        } catch (IOException error) {
            return null;
        }


    }

}
