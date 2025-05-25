package org.homework.config;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.homework.grpc.FileStorageServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @GrpcClient("filestorage-service")
    private FileStorageServiceGrpc.FileStorageServiceBlockingStub fileStorageServiceBlockingStub;

    @Bean
    public FileStorageServiceGrpc.FileStorageServiceBlockingStub fileStorageServiceBlockingStub() {
        return fileStorageServiceBlockingStub;
    }
}
