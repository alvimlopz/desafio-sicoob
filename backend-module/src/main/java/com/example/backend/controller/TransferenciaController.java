package com.example.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.dto.TransferRequest;
import com.example.backend.integration.ejb.BeneficioEjbClient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Transferências", description = "Operações de transferência entre benefícios")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/transferencias")
public class TransferenciaController {

    private final BeneficioEjbClient ejbClient;

    public TransferenciaController(BeneficioEjbClient ejbClient) {
        this.ejbClient = ejbClient;
    }

    @Operation(
    	    summary = "Realizar transferência entre benefícios",
    	    description = "Executa uma transferência de valor entre dois benefícios utilizando EJB remoto",
    	    responses = {
    	        @ApiResponse(responseCode = "204", description = "Transferência realizada com sucesso"),
    	        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
    	        @ApiResponse(responseCode = "500", description = "Erro interno ou falha no EJB remoto")
    	    }
    	)
    	@PostMapping
    	public ResponseEntity<Void> transferir(
    	    @io.swagger.v3.oas.annotations.parameters.RequestBody(
    	        required = true,
    	        content = @Content(schema = @Schema(implementation = TransferRequest.class))
    	    )
    	    @Valid @org.springframework.web.bind.annotation.RequestBody TransferRequest req
    	) {
    	    ejbClient.transfer(req.getFromId(), req.getToId(), req.getAmount());
    	    return ResponseEntity.noContent().build();
    	}
}
