package org.homework.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homework.domain.StorageFile;
import org.homework.infrastructure.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class CheckPlagiarismService {
    @Autowired
    private FileRepository fileRepository;

    public int isPlagiarism(byte[] file) {
        String strFile = new String(file, StandardCharsets.UTF_8);
        int hash = strFile.hashCode();

        List<StorageFile> allFiles = fileRepository.findAll().stream().filter(x -> x.getHash() == hash).toList();
        if (allFiles.isEmpty()) {
            return -1;
        } else {
            return allFiles.get(0).getId();
        }
    }
}
