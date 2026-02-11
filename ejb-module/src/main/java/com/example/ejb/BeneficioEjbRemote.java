package com.example.ejb;

import java.math.BigDecimal;

import javax.ejb.Remote;

@Remote
public interface BeneficioEjbRemote {
    void transfer(Long fromId, Long toId, BigDecimal amount);
}
