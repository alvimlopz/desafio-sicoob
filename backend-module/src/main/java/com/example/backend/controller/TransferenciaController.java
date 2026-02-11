package com.example.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.TransferRequest;
import com.example.backend.integration.ejb.BeneficioEjbClient;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/transferencias")
public class TransferenciaController {

    private final BeneficioEjbClient ejbClient;

    public TransferenciaController(BeneficioEjbClient ejbClient) {
        this.ejbClient = ejbClient;
    }

    @PostMapping
    public ResponseEntity<Void> transferir(@Valid @RequestBody TransferRequest req) {
        ejbClient.transfer(req.getFromId(), req.getToId(), req.getAmount());
        return ResponseEntity.noContent().build();
    }
}
