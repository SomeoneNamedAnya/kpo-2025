package org.homework.infrastructure.grpc;

import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.homework.grpc.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
@Component
public class FileStorageClient {
    @GrpcClient("filestorage-service")
    private FileStorageServiceGrpc.FileStorageServiceBlockingStub fileStorageServiceBlockingStub;

    public FileStorageToGatewayResponseId uploadFile(MultipartFile file) {
        try {
            GatewayToFileStorageRequestFile request = GatewayToFileStorageRequestFile.newBuilder()
                    .setFileName(file.getOriginalFilename())
                    .setFile(ByteString.copyFrom(file.getBytes())).build();
            return fileStorageServiceBlockingStub.uploadFile(request);
        } catch (IOException error) {
            return null;
        }


    }
    public FileStorageToGatewayResponseFile getFile(int id) {
        GatewayToFileStorageRequestId request = GatewayToFileStorageRequestId.newBuilder()
                .setId(id)
                .build();
        return fileStorageServiceBlockingStub.getFile(request);

    }
}
