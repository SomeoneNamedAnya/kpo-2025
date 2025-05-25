package org.homework.adapters.controllers;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homework.adapters.dto.FileResponse;
import org.homework.adapters.dto.GatewayResponse;
import org.homework.adapters.dto.IdResponse;
import org.homework.grpc.*;
import org.homework.infrastructure.grpc.AnalysisGrpcClient;
import org.homework.infrastructure.grpc.FileStorageClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Objects;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/gateway")
public class GatewayController {

    private static final String UPLOAD_DIR = "for_file";
    private final AnalysisGrpcClient analysisGrpcClient;
    private final FileStorageClient fileStorageClient;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузите файл формата .txt в систему анти плагиата")
    public ResponseEntity<IdResponse> upLoadFile(@RequestParam("file") MultipartFile file) {
        String name = file.getOriginalFilename();
        if (Objects.isNull(name) || !name.toLowerCase().endsWith(".txt")) {
            return ResponseEntity.badRequest().build();
        }
        try {
            FileStorageToGatewayResponseId answer = fileStorageClient.uploadFile(file);
            if (Objects.isNull(answer)) {
                return ResponseEntity.badRequest().build();
            } else {
                return ResponseEntity.ok(new IdResponse(answer.getId(), answer.getPlagiarism()));
            }
        } catch (StatusRuntimeException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }

    }

    @PostMapping("/get_file/{id}")
    @Operation(summary = "Введите Id чтобы получить файл из системы")
    public ResponseEntity<FileResponse> getFile(@PathVariable int id) {
        try {
            FileStorageToGatewayResponseFile answer = fileStorageClient.getFile(id);

            if (Objects.isNull(answer) || !answer.getIsExist()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(new FileResponse(
                        answer.getIsExist(),
                        answer.getFileName(),
                        answer.getFile()));
            }
        } catch (StatusRuntimeException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }

    @PostMapping("/make_analysis/{id}")
    @Operation(summary = "Введите Id чтобы получить анализ файла из системы")
    public ResponseEntity<GatewayResponse> makeAnalysis(@PathVariable int id) {
        try {
            GatewayResponseAnalysis answer = analysisGrpcClient.analysisFile(id);
            if (Objects.isNull(answer) || !answer.getIsExist()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(new GatewayResponse(
                        answer.getIsExist(),
                        answer.getCountParagraphs(),
                        answer.getCountWords(),
                        answer.getCountCharacters(),
                        answer.getLocation()
                ));
            }
        } catch (StatusRuntimeException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }

    @PostMapping(value ="/get_cloud", produces = MediaType.IMAGE_PNG_VALUE)
    @Operation(summary = "Введите location чтобы получить облако слов")
    public ResponseEntity<byte[]> getCloud(@RequestParam String location) {
        try {
            AnalysisToGatewayResponseCloud answer = analysisGrpcClient.getCloud(location);
            if (Objects.isNull(answer) || !answer.getIsExist()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body(answer.getCloud().toByteArray());
            }
        } catch (StatusRuntimeException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }
}