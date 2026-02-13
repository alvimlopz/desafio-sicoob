package com.example.backend.controller;

import com.example.backend.entity.Beneficio;
import com.example.backend.service.BeneficioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.List;

@Tag(name = "Benefícios", description = "Operações de CRUD de benefícios")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/beneficios")
public class BeneficioController {

    private final BeneficioService service;

    public BeneficioController(BeneficioService service) {
        this.service = service;
    }

    @Operation(
        summary = "Listar benefícios",
        description = "Retorna a lista de todos os benefícios cadastrados"
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<Beneficio> listar() {
        return service.listar();
    }

    @Operation(
        summary = "Buscar benefício por ID",
        description = "Retorna um benefício específico pelo ID"
    )
    @ApiResponse(responseCode = "200", description = "Benefício encontrado")
    @ApiResponse(responseCode = "404", description = "Benefício não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Beneficio> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Criar benefício",
        description = "Cria um novo benefício"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Benefício criado",
        content = @Content(schema = @Schema(implementation = Beneficio.class))
    )
    @PostMapping
    public Beneficio criar(@RequestBody Beneficio beneficio) {
        return service.salvar(beneficio);
    }

    @Operation(
        summary = "Atualizar benefício",
        description = "Atualiza um benefício existente pelo ID"
    )
    @ApiResponse(responseCode = "200", description = "Benefício atualizado")
    @ApiResponse(responseCode = "404", description = "Benefício não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Beneficio> atualizar(
            @PathVariable Long id,
            @RequestBody Beneficio beneficio) {

        try {
            Beneficio atualizado = service.atualizar(id, beneficio);
            return ResponseEntity.ok(atualizado);
        } catch (java.util.NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
        summary = "Excluir benefício",
        description = "Remove um benefício pelo ID"
    )
    @ApiResponse(responseCode = "204", description = "Benefício excluído com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

