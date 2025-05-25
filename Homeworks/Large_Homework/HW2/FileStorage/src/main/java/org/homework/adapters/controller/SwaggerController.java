package org.homework.adapters.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homework.domain.StorageFile;
import org.homework.adapters.dto.FileResponse;
import org.homework.adapters.dto.IdResponse;
import org.homework.application.service.CheckPlagiarismService;
import org.homework.application.service.ManagerFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/file_storage")
@RequiredArgsConstructor
public class SwaggerController {
    @Autowired
    private CheckPlagiarismService checkPlagiarismService;
    @Autowired
    private ManagerFileService managerFileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузите необходимый файл .txt в систему")
    public ResponseEntity<IdResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        String name = file.getOriginalFilename();
        if (Objects.isNull(name) || !name.toLowerCase().endsWith(".txt")) {
            return ResponseEntity.badRequest().build();
        }
        try {
            int tempId = checkPlagiarismService.isPlagiarism(file.getBytes());
            if (tempId != -1) {
                return ResponseEntity.ok(new IdResponse(tempId, true));
            } else {
                return ResponseEntity.ok(
                        new IdResponse(
                                managerFileService.uploadFile(file.getOriginalFilename(), file.getBytes()),
                                false));
            }
        } catch (IOException error) {
            return ResponseEntity.badRequest().build();
        }


    }

    @PostMapping("/get/{id}")
    @Operation(summary = "Введите Id чтобы получить файл из системы")
    public ResponseEntity<FileResponse> getFile(@PathVariable int id) {

        StorageFile file = managerFileService.getFile(id);
        if (Objects.isNull(file)) {
            return ResponseEntity.notFound().build();
        } else {
            try {
                String fileInner = Files.readString(Paths.get(file.getLocation()));
                return ResponseEntity.ok(new FileResponse(true,
                        file.getFilename(),
                        fileInner));
            } catch (IOException error) {
                return ResponseEntity.notFound().build();
            }

        }
    }

}
