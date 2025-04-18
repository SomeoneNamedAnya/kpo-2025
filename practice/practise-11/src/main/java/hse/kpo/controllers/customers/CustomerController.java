package hse.kpo.controllers.customers;

import hse.kpo.domains.Customer;
import hse.kpo.dto.request.CustomerRequest;
import hse.kpo.dto.response.CustomerResponse;
import hse.kpo.enums.EngineTypes;
import hse.kpo.facade.Hse;
import hse.kpo.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Tag(name = "Клиенты", description = "Управление клиентами")
public class CustomerController {
    private final CustomerService customerService;
    private final Hse hseFacade;

    @PostMapping
    @Operation(summary = "Создать клиента")
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CustomerRequest request,
            BindingResult bindingResult) {
        /*hseFacade.addCustomer(request.getName(),
                request.getLegPower(),
                request.getHandPower(),
                request.getIq());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToResponse(findCustomerByName(request.getName())));
*/

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        hseFacade.addCustomer(request.getName(),
                request.getLegPower(),
                request.getHandPower(),
                request.getIq());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

 /*   @PutMapping("/{name}")
    @Operation(summary = "Обновить клиента")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable String name,
            @Valid @RequestBody CustomerRequest request) {

        return customerService.findCustomerByName(name)
                .map(existingCar -> {
                    existingCar.setEngine(createEngineFromRequest(request));
                    carService.addExistingCar(existingCar);
                    return ResponseEntity.ok(existingCar);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{name}")
    @Operation(summary = "Удалить клиента")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String name) {
        hseFacade.deleteCustomer(name);
        return ResponseEntity.noContent().build();
    }
*/
    @GetMapping
    @Operation(summary = "Получить всех клиентов")
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getCustomers().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private Customer findCustomerByName(String name) {
        return customerService.getCustomers().stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));
    }

    private CustomerResponse convertToResponse(Customer customer) {
        return new CustomerResponse(
                customer.getName(),
                customer.getLegPower(),
                customer.getHandPower(),
                customer.getIq(),
                customer.getCars() != null ? customer.getCars().stream().map(car -> car.getVin()).collect(Collectors.toList()) : null,
                customer.getCatamaran() != null ? customer.getCatamaran().getVin() : null
        );
    }
}
