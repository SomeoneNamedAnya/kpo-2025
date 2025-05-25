package org.homework.application.service;

import org.homework.domain.StorageFile;
import org.homework.application.factory.FileFactory;
import org.homework.infrastructure.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
@Component
public class ManagerFileService {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileFactory fileFactory;
    private static final String UPLOAD_DIR = "File_Storage";

    public int uploadFile(String fileName, byte[] file){
        try {

            Files.createDirectories(Paths.get(UPLOAD_DIR));
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            Files.write(filePath, file);
            String strFile = new String(file, StandardCharsets.UTF_8);

            StorageFile temp = fileRepository.save(fileFactory.create(fileName,
                    filePath.toString(),
                    strFile.hashCode()));
            return temp.getId();
        } catch (IOException error) {
            return -1;
        }

    }

    public StorageFile getFile(int id) {
        Optional<StorageFile> file = fileRepository.findById(id);
        return file.orElse(null);
    }
}
