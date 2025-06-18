package org.homework.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.homework.application.exeption.NotExistException;
import org.homework.adapter.dto.response.CreateResponse;
import org.homework.adapter.dto.response.ScoreResponse;
import org.homework.application.service.ManageAccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ManageAccountService manageAccountService;
    @PostMapping("/create_account/{id}")
    @Operation(summary = "Введите Id пользователя, чтобы создать счет")
    public ResponseEntity<CreateResponse> createAccount(@PathVariable int id) {
        try {
           return ResponseEntity.ok(new CreateResponse("OK", manageAccountService.createAccount(id).getId()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
    }


    @PostMapping("/top_up_account/{id}/{x}")
    @Operation(summary = "Введите Id пользователя и сумму которую хотите положить на аккаунт пользователя")
    public ResponseEntity<String> topUpAccount(@PathVariable int id, @PathVariable float x) {
        if (x <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            manageAccountService.topUpAccount(id, x);
            return ResponseEntity.ok("OK");
        } catch (NotExistException e) {
            return ResponseEntity.ok("Don't exist user account");
        }
    }


    @PostMapping("/check_account/{id}")
    @Operation(summary = "Введите Id пользователя, чтобы посмотреть баланс счета")
    public ResponseEntity<ScoreResponse> checkAccount(@PathVariable int id) {
        try {
           return ResponseEntity.ok(new ScoreResponse("OK", manageAccountService.sum(id)));
        } catch (NotExistException e) {
            return ResponseEntity.ok(new ScoreResponse("Don't exist user account", -1));
        }
    }
}