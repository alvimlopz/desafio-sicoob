package com.example.backend;

import com.example.backend.dto.TransferRequest;
import com.example.ejb.BeneficioEjbService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transferencias")
public class TransferenciaController {

    private final BeneficioEjbService ejbService;

    public TransferenciaController(BeneficioEjbService ejbService) {
        this.ejbService = ejbService;
    }

    @PostMapping
    public ResponseEntity<Void> transferir(@Valid @RequestBody TransferRequest req) {
        ejbService.transfer(req.getFromId(), req.getToId(), req.getAmount());
        return ResponseEntity.noContent().build();
    }
}
