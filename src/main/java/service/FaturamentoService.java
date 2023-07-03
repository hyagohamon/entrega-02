package service;

import model.Pagamento;
import model.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FaturamentoService {

    public static BigDecimal getTotalFaturamento(List<Pagamento> pagamentos, int mesDesejado, int anoDesejado) {
        BigDecimal faturamentoTotal = BigDecimal.ZERO;
        for (Pagamento pagamento : pagamentos) {
            LocalDate dataCompra = pagamento.getDataCompra();
            int mesCompra = dataCompra.getMonthValue();
            int anoCompra = dataCompra.getYear();

            if (mesCompra == mesDesejado && anoCompra == anoDesejado) {
                List<Produto> produtos = pagamento.getProdutos();
                for (Produto produto : produtos) {
                    faturamentoTotal = faturamentoTotal.add(produto.getPreco());
                }
            }
        }
        return faturamentoTotal;
    }

    private FaturamentoService() {
    }
}
