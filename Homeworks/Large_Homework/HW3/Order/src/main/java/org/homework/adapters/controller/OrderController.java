package org.homework.adapters.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homework.application.exeption.OrderException;
import org.homework.adapters.dto.request.OrderRequest;
import org.homework.adapters.dto.response.CreateResponse;
import org.homework.adapters.dto.response.OrderResponse;
import org.homework.application.factory.OrderFactory;
import org.homework.application.service.ManageOrderService;
import org.homework.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/order_service")
public class OrderController {
    @Autowired
    private final ManageOrderService manageOrderService;
    @Autowired
    private final OrderFactory orderFactory;
    @PostMapping("/create_order")
    @Operation(summary = "Создать заказ")
    public ResponseEntity<CreateResponse> createOrder(@Valid @RequestBody OrderRequest request,
                                                        BindingResult bindingResult) {

        if (bindingResult.hasErrors() || request.cost() < 0) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Order newOrder = orderFactory.create(request.id(), request.cost(), request.description());
            manageOrderService.addOrder(newOrder);
            return ResponseEntity.ok(new CreateResponse("OK", newOrder.getId()));
        } catch (OrderException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/get_all/{id}")
    @Operation(summary = "Введите Id пользователя чтобы получить все его заказы")
    public ResponseEntity<List<OrderResponse>> getAllOrder(@PathVariable int id) {
        if (id < 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(manageOrderService.getAllById(id)
                .stream().map(x -> new OrderResponse(
                        x.getId(),
                        x.getUserId(),
                        x.getAmount(),
                        x.getDescription(),
                        x.getStatus())).toList());
    }


    @PostMapping("/get_order/{id}")
    @Operation(summary = "Введите Id заказа, чтобы посмотреть информацию о нем")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable int id) {
        Optional<Order> order = manageOrderService.findById(id);
        return order.map(value -> ResponseEntity.ok(new OrderResponse(
                value.getId(),
                value.getUserId(),
                value.getAmount(),
                value.getDescription(),
                value.getStatus()))).orElseGet(() -> ResponseEntity.notFound().build());
    }


}