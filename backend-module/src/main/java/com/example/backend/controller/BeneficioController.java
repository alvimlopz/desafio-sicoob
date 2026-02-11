package com.example.backend.controller;

import com.example.backend.entity.Beneficio;
import com.example.backend.service.BeneficioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/beneficios")
public class BeneficioController {

    private final BeneficioService service;

    public BeneficioController(BeneficioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Beneficio> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficio> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Beneficio criar(@RequestBody Beneficio beneficio) {
        return service.salvar(beneficio);
    }

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


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
