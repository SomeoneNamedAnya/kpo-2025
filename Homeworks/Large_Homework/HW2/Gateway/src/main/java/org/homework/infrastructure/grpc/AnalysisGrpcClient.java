package org.homework.infrastructure.grpc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.homework.grpc.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Component
public class AnalysisGrpcClient {
    @GrpcClient("analysis-service")
    private final AnalysisServiceGrpc.AnalysisServiceBlockingStub blockingStub;

    public GatewayResponseAnalysis analysisFile(int id) {
        return blockingStub.analysisFile(GatewayRequestId.newBuilder().setId(id).build());
    }

    public AnalysisToGatewayResponseCloud getCloud(String location) {
        return blockingStub.getCloud(GatewayToAnalysisRequestLocation.newBuilder().setLocation(location).build());
    }
}
