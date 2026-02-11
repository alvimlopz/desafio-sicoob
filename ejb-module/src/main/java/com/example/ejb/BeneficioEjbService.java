package com.example.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import com.example.backend.entity.Beneficio;

import java.math.BigDecimal;

@Stateless
public class BeneficioEjbService {

    @PersistenceContext
    private EntityManager em;

    public void transfer(Long fromId, Long toId, BigDecimal amount) {

        if (fromId == null || toId == null) {
            throw new IllegalArgumentException("fromId e toId são obrigatórios.");
        }
        if (fromId.equals(toId)) {
            throw new IllegalArgumentException("fromId e toId não podem ser iguais.");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("amount deve ser maior que zero.");
        }
        
        Beneficio from = em.find(Beneficio.class, fromId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        Beneficio to   = em.find(Beneficio.class, toId,   LockModeType.OPTIMISTIC_FORCE_INCREMENT);

        if (from == null) {
            throw new IllegalArgumentException("Benefício origem não encontrado: " + fromId);
        }
        if (to == null) {
            throw new IllegalArgumentException("Benefício destino não encontrado: " + toId);
        }

        if (from.getValor() == null || to.getValor() == null) {
            throw new IllegalStateException("Valor do benefício não pode ser null.");
        }

        if (from.getValor().compareTo(amount) < 0) {
            throw new IllegalStateException("Saldo insuficiente para transferência.");
        }

        from.setValor(from.getValor().subtract(amount));
        to.setValor(to.getValor().add(amount));

    }
}
