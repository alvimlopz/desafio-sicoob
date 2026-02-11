package com.example.backend.service;

import com.example.backend.entity.Beneficio;
import com.example.backend.repository.BeneficioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BeneficioService {

    private final BeneficioRepository repository;

    public BeneficioService(BeneficioRepository repository) {
        this.repository = repository;
    }

    public List<Beneficio> listar() {
        return repository.findAll();
    }

    public Optional<Beneficio> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Beneficio salvar(Beneficio beneficio) {
        return repository.save(beneficio);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
    
    public Beneficio obterObrigatorio(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Benefício não encontrado: " + id));
    }

    public Beneficio atualizar(Long id, Beneficio novosDados) {
        Beneficio atual = obterObrigatorio(id);

        atual.setNome(novosDados.getNome());
        atual.setDescricao(novosDados.getDescricao());
        atual.setValor(novosDados.getValor());
        atual.setAtivo(novosDados.getAtivo());

        return repository.save(atual);
    }

}
