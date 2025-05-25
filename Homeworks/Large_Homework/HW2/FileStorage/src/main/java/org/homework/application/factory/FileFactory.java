package org.homework.application.factory;

import org.homework.domain.StorageFile;
import org.springframework.stereotype.Component;

@Component
public class FileFactory {
    public StorageFile create(String filename, String location, int hash) {
        return new StorageFile(filename, location, hash);
    }
}
