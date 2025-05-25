package org.homework.infrastructure.grpc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.homework.grpc.FileStorageServiceGrpc;
import org.homework.grpc.FileStorageToGatewayResponseFile;
import org.homework.grpc.GatewayToFileStorageRequestId;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Component
public class FileStorageGrpcClient {

    @GrpcClient("filestorage-service")
    private final FileStorageServiceGrpc.FileStorageServiceBlockingStub blockingStub;

    public FileStorageToGatewayResponseFile getFile(int id) {
        return blockingStub.getFile(GatewayToFileStorageRequestId.newBuilder().setId(id).build());
    }
}
