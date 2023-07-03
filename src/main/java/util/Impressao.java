package util;

import interfaces.TipoAssinatura;
import model.Pagamento;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class Impressao {
    private static final Logger logger = Logger.getLogger(Impressao.class.getName());

    private Impressao() {
    }

    public static void imprimirTempoEmMeses(Long mesesAtivosAssinatura) {
        String mensagem = String.format("Tempo em meses da assinatura ativa: %d ", mesesAtivosAssinatura);

        logger.info(mensagem);
    }

    public static void imprimirTaxaAssinatura(TipoAssinatura assinatura) {
        String mensagem = String.format("Taxa da assinatura: %s %s", assinatura.getClass().getName(), assinatura.calcularTaxaAssinatura());

        logger.info(mensagem);
    }

    public static void imprimirPagamentos(List<Pagamento> pagamentoList) {
        pagamentoList.stream().sorted(Comparator.comparing(Pagamento::getDataCompra)).forEach(pagamento -> {
            String mensagem = "Data de Compra: " + pagamento.getDataCompra() + "\n" + "model.Cliente: " + pagamento.getCliente().getNome() + "\n" + "Produtos: " + pagamento.getProdutos();
            logger.info(mensagem);
        });
    }

    public static void imprimirTotalAssinatura(BigDecimal valorPagoAssinatura) {
        String mensagem = String.format("valor pago da assinatura até o momento: %s ", valorPagoAssinatura);
        logger.info(mensagem);
    }
}
