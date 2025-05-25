package org.homework.config;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.homework.grpc.AnalysisServiceGrpc;
import org.homework.grpc.FileStorageServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @GrpcClient("analysis-service")
    private AnalysisServiceGrpc.AnalysisServiceBlockingStub analysisServiceStub;

    @GrpcClient("filestorage-service")
    private FileStorageServiceGrpc.FileStorageServiceBlockingStub fileStorageServiceBlockingStub;


    @Bean
    public AnalysisServiceGrpc.AnalysisServiceBlockingStub analysisServiceStub() {
        return analysisServiceStub;
    }

    @Bean
    public FileStorageServiceGrpc.FileStorageServiceBlockingStub fileStorageServiceBlockingStub() {
        return fileStorageServiceBlockingStub;
    }
}
