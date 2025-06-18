package org.homework.adapters.controller;

import io.grpc.StatusRuntimeException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homework.grpc.ResponseAccount;
import org.homework.grpc.ResponseAccountScore;
import org.homework.grpc.ResponseTopUp;
import org.homework.infrastructure.grpc.PaymentServiceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/payment_service")
public class PaymentController {
    private final PaymentServiceClient paymentServiceClient;
    @PostMapping("/create_account/{id}")
    @Operation(summary = "Введите Id пользователя, чтобы создать счет")
    public ResponseEntity<Integer> createAccount(@PathVariable int id) {
        try {
            ResponseAccount response = paymentServiceClient.createAccount(id);
            if (response.getIsOk()) {
                return ResponseEntity.ok(response.getIdAccount());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }


    @PostMapping("/top_up_account/{id}/{x}")
    @Operation(summary = "Введите Id пользователя и сумму которую хотите положить на аккаунт пользователя")
    public ResponseEntity<Boolean> topUpAccount(@PathVariable int id, @PathVariable float x) {
        if (x <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            ResponseTopUp response = paymentServiceClient.topUpAccount(id, x);

            return ResponseEntity.ok(response.getIsOk());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }


    @PostMapping("/check_account/{id}")
    @Operation(summary = "Введите Id пользователя, чтобы посмотреть баланс счета")
    public ResponseEntity<Float> checkAccount(@PathVariable int id) {
        try {
            ResponseAccountScore response = paymentServiceClient.checkAccount(id);

            if (response.getIsOk()) {
                return ResponseEntity.ok(response.getScore());
            } else {
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }
}