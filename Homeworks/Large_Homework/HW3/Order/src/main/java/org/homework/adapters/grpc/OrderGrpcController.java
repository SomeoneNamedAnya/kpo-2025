package org.homework.adapters.grpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.homework.application.exeption.OrderException;
import org.homework.application.factory.OrderFactory;
import org.homework.application.service.ManageOrderService;
import org.homework.domain.Order;
import org.homework.grpc.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@RequiredArgsConstructor
@GrpcService
public class OrderGrpcController extends OrderServiceGrpc.OrderServiceImplBase{
    @Autowired
    private ManageOrderService manageOrderService;
    @Autowired
    private final OrderFactory orderFactory;
    @Override
    public void createOrder(GatewayRequestToCreate request,
                            StreamObserver<ResponseCreatedOrder> responseObserver) {

        ResponseCreatedOrder response;
        try {
            if (request.getCost() <= 0) {
                throw new OrderException("Bad request");
            }
            Order newOrder = orderFactory.create(request.getId(), request.getCost(), request.getDescription());

            manageOrderService.addOrder(newOrder);
            response = ResponseCreatedOrder.newBuilder()
                    .setIdOrder(newOrder.getId())
                    .setIsOk(true)
                    .build();

        } catch (OrderException e) {
            response = ResponseCreatedOrder.newBuilder()
                    .setIdOrder(-1)
                    .setIsOk(false)
                    .build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void getAllOrder(GatewayRequestAllOrder request,
                            StreamObserver<ResponseAllOrder> responseObserver) {

        ResponseAllOrder response;
        try {
            if (request.getId() <= 0) {
                throw new OrderException("Bad request");
            }

            response = ResponseAllOrder.newBuilder()
                    .addAllOrders(manageOrderService.getAllById(request.getId())
                            .stream()
                            .map(x -> org.homework.grpc.Order.newBuilder()
                                    .setId(x.getId())
                                    .setUserId(x.getUserId())
                                    .setCost(x.getAmount())
                                    .setDescription(x.getDescription())
                                    .setStatus(Status.valueOf(x.getStatus()))
                                    .build())
                            .toList())
                    .setIsOk(true)
                    .build();

        } catch (OrderException e) {
            response = ResponseAllOrder.newBuilder()
                    .setIsOk(false)
                    .build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
    @Override
    public void getOrder(GatewayRequestOrder request,
                         StreamObserver<ResponseOrder> responseObserver) {
        ResponseOrder response;
        try {
            Optional<Order> order = manageOrderService.findById(request.getIdOrder());
            if (order.isEmpty()) {
                throw new OrderException("Bad request");
            }

            response = ResponseOrder.newBuilder()
                    .setIsOk(true)
                    .setOrder(org.homework.grpc.Order.newBuilder()
                            .setId(order.get().getId())
                            .setUserId(order.get().getUserId())
                            .setCost(order.get().getAmount())
                            .setDescription(order.get().getDescription())
                            .setStatus(Status.valueOf(order.get().getStatus()))
                            .build()
                    )
                    .build();
        } catch (OrderException e) {
            response = ResponseOrder.newBuilder()
                    .setIsOk(false)
                    .build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

}