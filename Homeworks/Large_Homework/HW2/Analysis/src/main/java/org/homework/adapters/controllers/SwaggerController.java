package org.homework.adapters.controllers;

import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homework.domain.FileAnalysis;
import org.homework.adapters.dto.GatewayResponse;
import org.homework.infrastructure.grpc.FileStorageGrpcClient;
import org.homework.grpc.FileStorageToGatewayResponseFile;
import org.homework.infrastructure.repository.FileAnalysisRepository;
import org.homework.application.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/analysis")
@Slf4j
@RequiredArgsConstructor
public class SwaggerController {
    @Autowired
    private AnalysisService analysisService;
    @Autowired
    private FileAnalysisRepository fileAnalysisRepository;
    @Autowired
    private FileStorageGrpcClient fileStorageToAnalysisGrpcClient;

    @PostMapping("/make_analysis/{id}")
    public ResponseEntity<GatewayResponse> makeAnalysis(@PathVariable int id) {

        try {
            Optional<FileAnalysis> finding = fileAnalysisRepository.findByIdFile(id);
            if (finding.isEmpty()) {
                FileStorageToGatewayResponseFile answer = fileStorageToAnalysisGrpcClient.getFile(id);
                if (answer.getIsExist()) {
                    FileAnalysis fileAnalysis = analysisService.makeAnalysis(id, answer.getFile());
                    if (Objects.isNull(fileAnalysis)) {
                        return ResponseEntity.notFound().build();

                    } else {
                        return ResponseEntity.ok(new GatewayResponse(
                                true,
                                fileAnalysis.getParagraphs(),
                                fileAnalysis.getWords(),
                                fileAnalysis.getLetters(),
                                fileAnalysis.getLocation()
                        ));
                    }

                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.ok(new GatewayResponse(
                        true,
                        finding.get().getParagraphs(),
                        finding.get().getWords(),
                        finding.get().getLetters(),
                        finding.get().getLocation()
                ));

            }
        } catch (StatusRuntimeException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }

    @PostMapping(value ="/get_cloud", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getCloud(@RequestParam String location) {
        try {
            Path path = Paths.get(location);
            byte[] cloud = Files.readAllBytes(path);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(cloud);
        } catch (IOException error) {
            return ResponseEntity.notFound().build();
        }
    }
}
