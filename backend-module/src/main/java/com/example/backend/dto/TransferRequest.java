package com.example.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para solicitação de transferência")
public class TransferRequest {

    @Schema(description = "ID do benefício de origem", example = "1", required = true)
    @NotNull
    private Long fromId;

    @Schema(description = "ID do benefício de destino", example = "2", required = true)
    @NotNull
    private Long toId;

    @Schema(description = "Valor da transferência", example = "100.50", required = true)
    @NotNull
    @DecimalMin(value = "0.01", inclusive = true)
    private BigDecimal amount;

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
