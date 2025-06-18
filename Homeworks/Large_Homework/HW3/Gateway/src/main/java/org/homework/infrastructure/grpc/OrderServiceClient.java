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
public class OrderServiceClient {
    @GrpcClient("order-service")
    private final OrderServiceGrpc.OrderServiceBlockingStub blockingStub;

    public ResponseCreatedOrder createOrder(int id, float cost, String description) {
        return blockingStub.createOrder(GatewayRequestToCreate.newBuilder()
                .setId(id)
                .setCost(cost)
                .setDescription(description)
                .build());
    }
    public ResponseAllOrder getAllOrder(int id) {
        return blockingStub.getAllOrder(GatewayRequestAllOrder.newBuilder().setId(id).build());
    }
    public ResponseOrder getOrder(int idOrder) {
        return blockingStub.getOrder(GatewayRequestOrder.newBuilder().setIdOrder(idOrder).build());
    }
}
