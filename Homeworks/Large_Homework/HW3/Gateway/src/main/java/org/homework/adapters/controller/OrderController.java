package org.homework.adapters.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homework.adapters.dto.request.OrderRequest;
import org.homework.adapters.dto.response.OrderResponse;
import org.homework.grpc.ResponseAllOrder;
import org.homework.grpc.ResponseCreatedOrder;
import org.homework.grpc.ResponseOrder;
import org.homework.infrastructure.grpc.OrderServiceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/order_service")
public class OrderController {
    private final OrderServiceClient orderServiceClient;
    @PostMapping("/create_order")
    @Operation(summary = "Создать заказ")
    public ResponseEntity<Integer> createAccount(@Valid @RequestBody OrderRequest request,
                                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            ResponseCreatedOrder response = orderServiceClient.createOrder(
                    request.id(),
                    request.cost(),
                    request.description());
            if (response.getIsOk()) {
                return ResponseEntity.ok(response.getIdOrder());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }


    @PostMapping("/get_all/{id}")
    @Operation(summary = "Введите Id пользователя чтобы получить все его заказы")
    public ResponseEntity<List<OrderResponse>> getAllOrder(@PathVariable int id) {
        try {
            ResponseAllOrder response = orderServiceClient.getAllOrder(id);

            if (response.getIsOk()) {
                return ResponseEntity.ok(response.getOrdersList().stream()
                                            .map(item -> new OrderResponse(
                                                    item.getId(),
                                                    item.getUserId(),
                                                    item.getCost(),
                                                    item.getDescription(),
                                                    item.getStatus().name()))
                                            .toList());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }


    @PostMapping("/get_order/{id}")
    @Operation(summary = "Введите Id заказа, чтобы посмотреть информацию о нем")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable int id) {
        try {
            ResponseOrder response = orderServiceClient.getOrder(id);
            if (response.getIsOk()) {
                return ResponseEntity.ok(new OrderResponse(
                        response.getOrder().getId(),
                        response.getOrder().getUserId(),
                        response.getOrder().getCost(),
                        response.getOrder().getDescription(),
                        response.getOrder().getStatus().name()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }
}