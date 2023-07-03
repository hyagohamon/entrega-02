package model;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.*;

public class Produto {

    private String nome;
    private Path file;
    private BigDecimal preco;

    public Produto(String nome, Path file, BigDecimal preco) {
        this.nome = nome;
        this.file = file;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public Path getFile() {
        return file;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public static BigDecimal calcularValorGasto(List<Produto> produtos) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (Produto produto : produtos) {
            valorTotal = valorTotal.add(produto.getPreco());
        }
        return valorTotal;
    }

    public static void adicionarProdutos(Map<Cliente, List<Produto>> mapaClienteProdutos, Cliente cliente, Produto... produtos) {
        List<Produto> listaProdutos = mapaClienteProdutos.getOrDefault(cliente, new ArrayList<>());
        listaProdutos.addAll(Arrays.asList(produtos));
        mapaClienteProdutos.put(cliente, listaProdutos);
    }

    public static Map<Produto, Integer> quantidadePorProduto(List<Pagamento> pagamentos) {
        Map<Produto, Integer> quantidadePorProduto = new HashMap<>();
        for (Pagamento pagamento : pagamentos) {
            List<Produto> produtos = pagamento.getProdutos();
            for (Produto produto : produtos) {
                quantidadePorProduto.put(produto, quantidadePorProduto.getOrDefault(produto, 0) + 1);
            }
        }
        return quantidadePorProduto;
    }

    @Override
    public String toString() {
        return "model.Produto{" +
                "nome='" + nome + '\'' +
                ", file=" + file +
                ", preco=" + preco +
                '}';
    }
}
