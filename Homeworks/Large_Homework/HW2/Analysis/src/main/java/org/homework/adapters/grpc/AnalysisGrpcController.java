package org.homework.adapters.grpc;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.homework.domain.FileAnalysis;
import org.homework.grpc.*;
import org.homework.infrastructure.grpc.FileStorageGrpcClient;
import org.homework.infrastructure.repository.FileAnalysisRepository;
import org.homework.application.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@GrpcService
public class AnalysisGrpcController extends AnalysisServiceGrpc.AnalysisServiceImplBase {
    @Autowired
    private final AnalysisService analysisService;
    @Autowired
    private FileAnalysisRepository fileAnalysisRepository;
    @Autowired
    private FileStorageGrpcClient fileStorageToAnalysisGrpcClient;

    @Override
    public void analysisFile(GatewayRequestId request,
                             StreamObserver<GatewayResponseAnalysis> responseObserver) {

        GatewayResponseAnalysis response;

        Optional<FileAnalysis> finding = fileAnalysisRepository.findByIdFile(request.getId());
        if (finding.isEmpty()) {
            FileStorageToGatewayResponseFile answer = fileStorageToAnalysisGrpcClient.getFile(request.getId());
            if (answer.getIsExist()) {
                FileAnalysis fileAnalysis = analysisService.makeAnalysis(request.getId(), answer.getFile());
                if (Objects.isNull(fileAnalysis)) {
                    response = GatewayResponseAnalysis.newBuilder()
                            .setIsExist(false)
                            .build();
                } else {
                    response = GatewayResponseAnalysis.newBuilder()
                            .setIsExist(true)
                            .setCountParagraphs(fileAnalysis.getParagraphs())
                            .setCountWords(fileAnalysis.getWords())
                            .setCountCharacters(fileAnalysis.getLetters())
                            .setLocation(fileAnalysis.getLocation())
                            .build();
                }

            } else {
                response = GatewayResponseAnalysis.newBuilder()
                        .setIsExist(false)
                        .build();
            }
        } else {
            response = GatewayResponseAnalysis.newBuilder()
                    .setIsExist(true)
                    .setCountParagraphs(finding.get().getParagraphs())
                    .setCountWords(finding.get().getWords())
                    .setCountCharacters(finding.get().getLetters())
                    .setLocation(finding.get().getLocation())
                    .build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getCloud(GatewayToAnalysisRequestLocation request,
                             StreamObserver<AnalysisToGatewayResponseCloud> responseObserver) {

        AnalysisToGatewayResponseCloud response;
        try {
            Path path = Paths.get(request.getLocation());
            byte[] cloud = Files.readAllBytes(path);
            response = AnalysisToGatewayResponseCloud.newBuilder()
                    .setIsExist(true)
                    .setCloud(ByteString.copyFrom(cloud))
                    .build();
        } catch (IOException error) {
            response = AnalysisToGatewayResponseCloud.newBuilder()
                    .setIsExist(false)
                    .build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}