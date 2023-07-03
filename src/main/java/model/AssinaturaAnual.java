package model;

import interfaces.TipoAssinatura;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class AssinaturaAnual implements TipoAssinatura {
    private final BigDecimal mensalidade;
    private final LocalDateTime begin;
    private final Optional<LocalDateTime> end;
    private final Cliente cliente;
    private final boolean assinaturaAtrasada;

    public AssinaturaAnual(BigDecimal mensalidade, LocalDateTime begin, Cliente cliente) {
        this.mensalidade = mensalidade;
        this.begin = begin;
        this.end = Optional.empty();
        this.cliente = cliente;
        this.assinaturaAtrasada = false;
    }

    public AssinaturaAnual(BigDecimal mensalidade, LocalDateTime begin, Cliente cliente, Boolean assinaturaAtrasada) {
        this.mensalidade = mensalidade;
        this.begin = begin;
        this.end = Optional.empty();
        this.cliente = cliente;
        this.assinaturaAtrasada = assinaturaAtrasada;
    }

    @Override
    public BigDecimal calcularTaxaAssinatura() {
        return BigDecimal.ZERO;
    }

    @Override
    public Boolean isAssinaturaEmAtraso() {
        return assinaturaAtrasada;
    }

    public BigDecimal getMensalidade() {
        return mensalidade;
    }


    public LocalDateTime getBegin() {
        return begin;
    }


    public Optional<LocalDateTime> getEnd() {
        return end;
    }

    public Cliente getCliente() {
        return cliente;
    }

}
