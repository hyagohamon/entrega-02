package model;

import interfaces.TipoAssinatura;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class AssinaturaTrimestral implements TipoAssinatura {

    private BigDecimal valorTotalAteMomento;
    private  BigDecimal mensalidade;
    private LocalDateTime begin;
    private Optional<LocalDateTime> end;
    private Cliente cliente;
    private boolean assinaturaEmAtraso;

    public AssinaturaTrimestral(BigDecimal mensalidade, LocalDateTime begin, Cliente cliente) {
        this.mensalidade = mensalidade;
        this.begin = begin;
        this.end = Optional.empty();
        this.cliente = cliente;
        this.valorTotalAteMomento= BigDecimal.valueOf(ChronoUnit.MONTHS.between(this.getBegin(), LocalDateTime.now())).multiply(this.mensalidade);
        this.assinaturaEmAtraso=false;
    }

    @Override
    public BigDecimal calcularTaxaAssinatura() {
        return valorTotalAteMomento.multiply(BigDecimal.valueOf(0.05));

    }

    @Override
    public Boolean isAssinaturaEmAtraso() {
        return assinaturaEmAtraso;
    }

    public BigDecimal getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(BigDecimal mensalidade) {
        this.mensalidade = mensalidade;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public Optional<LocalDateTime> getEnd() {
        return end;
    }

    public void setEnd(Optional<LocalDateTime> end) {
        this.end = end;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
