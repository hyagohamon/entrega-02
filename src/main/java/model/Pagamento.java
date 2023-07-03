package model;

import interfaces.TipoAssinatura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Pagamento {
    private final List<Produto> produtos;
    private final LocalDate dataCompra;
    private final Cliente cliente;
    private TipoAssinatura assinatura;

    public Pagamento(List<Produto> produtos, LocalDate dataCompra, Cliente cliente) {
        this.produtos = produtos;
        this.dataCompra = dataCompra;
        this.cliente = cliente;
    }

    public Pagamento(List<Produto> produtos, LocalDate dataCompra, Cliente cliente, TipoAssinatura assinatura) {
        this.produtos = produtos;
        this.dataCompra = dataCompra;
        this.cliente = cliente;
        this.assinatura = assinatura;
    }

    public static BigDecimal calcularSomaPagamento(Pagamento pagamento) {
        List<Produto> produtos = pagamento.getProdutos();
        BigDecimal soma = BigDecimal.ZERO;
        for (Produto produto : produtos) {
            soma = soma.add(produto.getPreco());
        }
        return soma;
    }

    public static BigDecimal calcularValorTotal(List<Pagamento> pagamentos) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (Pagamento pagamento : pagamentos) {
            List<Produto> produtos = pagamento.getProdutos();
            for (Produto produto : produtos) {
                valorTotal = valorTotal.add(produto.getPreco());
            }
        }
        return valorTotal;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "model.Pagamento{" +
                "produtos=" + produtos +
                ", dataCompra=" + dataCompra +
                ", cliente=" + cliente +
                ", assinatura=" + assinatura +
                '}';
    }
}