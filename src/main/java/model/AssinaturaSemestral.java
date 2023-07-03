package model;

import interfaces.TipoAssinatura;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class AssinaturaSemestral implements TipoAssinatura {
    private final BigDecimal valorTotalAteMomento;
    private final BigDecimal mensalidade;
    private final LocalDateTime begin;
    private final Optional<LocalDateTime> end;
    private final Cliente cliente;
    private final boolean assinaturaEmAtraso;


    public AssinaturaSemestral(BigDecimal mensalidade, LocalDateTime begin, Cliente cliente) {
        this.assinaturaEmAtraso = false;
        this.mensalidade = mensalidade;
        this.begin = begin;
        this.end = Optional.empty();
        this.cliente = cliente;
        this.valorTotalAteMomento = BigDecimal.valueOf(ChronoUnit.MONTHS.between(this.getBegin(), LocalDateTime.now())).multiply(this.mensalidade);
    }

    @Override
    public BigDecimal calcularTaxaAssinatura() {
        return valorTotalAteMomento.multiply(BigDecimal.valueOf(0.03));

    }

    @Override
    public Boolean isAssinaturaEmAtraso() {
        return assinaturaEmAtraso;
    }


    public BigDecimal getValorTotalAteMomento() {
        return valorTotalAteMomento;
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


    @Override
    public String toString() {
        return "model.AssinaturaSemestral{" +
                "valorTotalAteMomento=" + valorTotalAteMomento +
                ", mensalidade=" + mensalidade +
                ", begin=" + begin +
                ", end=" + end +
                ", cliente=" + cliente +
                ", assinaturaEmAtraso=" + assinaturaEmAtraso +
                '}';
    }
}
