package org.homework.infrastructure.grpc;
import org.homework.grpc.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Component
public class PaymentServiceClient {
    @GrpcClient("payment-service")
    private final PaymentServiceGrpc.PaymentServiceBlockingStub blockingStub;

    public ResponseAccount createAccount(int id) {
        return blockingStub.createAccount(GatewayRequestId.newBuilder().setId(id).build());
    }
    public ResponseTopUp topUpAccount(int id, float x) {
        return blockingStub.topUpAccount(GatewayRequestIdAndMoney.newBuilder().setId(id).setMoney(x).build());
    }
    public ResponseAccountScore checkAccount(int id) {
        return blockingStub.checkAccount(GatewayRequestId.newBuilder().setId(id).build());
    }
}
