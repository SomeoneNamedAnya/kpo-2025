package org.homework.adapters.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.homework.domain.StorageFile;
import org.homework.application.service.CheckPlagiarismService;
import org.homework.application.service.ManagerFileService;
import org.homework.grpc.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@GrpcService
@RequiredArgsConstructor
public class FileStorageGrpcController extends FileStorageServiceGrpc.FileStorageServiceImplBase {
    @Autowired
    private ManagerFileService managerFileService;

    @Autowired
    private CheckPlagiarismService checkPlagiarismService;

    @Override
    public void uploadFile(GatewayToFileStorageRequestFile request, StreamObserver<FileStorageToGatewayResponseId> responseObserver) {
        FileStorageToGatewayResponseId response;
        int tempId = checkPlagiarismService.isPlagiarism(request.getFile().toByteArray());
        if (tempId != -1) {
            response = FileStorageToGatewayResponseId.newBuilder()
                    .setId(tempId)
                    .setPlagiarism(true)
                    .build();
        } else {
            response = FileStorageToGatewayResponseId.newBuilder()
                    .setId(managerFileService.uploadFile(request.getFileName(), request.getFile().toByteArray()))
                    .setPlagiarism(false)
                    .build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getFile(GatewayToFileStorageRequestId request, StreamObserver<FileStorageToGatewayResponseFile> responseObserver) {

        FileStorageToGatewayResponseFile response;
        StorageFile file = managerFileService.getFile(request.getId());
        if (Objects.isNull(file)) {
            response = FileStorageToGatewayResponseFile.newBuilder()
                    .setIsExist(false)
                    .setMessage("File is not in database")
                    .build();

        } else {
            try {
                String fileInner = Files.readString(Paths.get(file.getLocation()));
                response = FileStorageToGatewayResponseFile.newBuilder()
                        .setIsExist(true)
                        .setFileName(file.getFilename())
                        .setFile(fileInner)
                        .build();
            } catch (IOException error) {
                response = FileStorageToGatewayResponseFile.newBuilder()
                        .setIsExist(false)
                        .setMessage("File is not in File_Storage")
                        .build();
            }

        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

}
