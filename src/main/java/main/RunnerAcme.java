package main;

import exception.AssinaturaAtrasadaException;
import interfaces.TipoAssinatura;
import model.*;
import service.FaturamentoService;
import service.ValidarAssinatura;
import util.Impressao;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

public class RunnerAcme {
    private static final Logger logger = Logger.getLogger(RunnerAcme.class.getName());

    public static void main(String[] args) throws AssinaturaAtrasadaException {
        Produto produto1 = new Produto("Música", Paths.get("caminho/para/musica-01.mp3"), BigDecimal.valueOf(5.99));
        Produto produto2 = new Produto("Vídeo", Paths.get("caminho/para/video-01.mp4"), BigDecimal.valueOf(8.99));
        Produto produto3 = new Produto("Imagem", Paths.get("caminho/para/imagem-01.jpg"), BigDecimal.valueOf(1.99));

        Cliente cliente1 = new Cliente("Airton Maciel Costa");
        Cliente cliente2 = new Cliente("Maria de Jesus Pereira");

        Pagamento hoje = new Pagamento(Arrays.asList(produto1, produto2), LocalDate.now(), cliente1);
        Pagamento ontem = new Pagamento(Arrays.asList(produto2, produto3), LocalDate.now().minusDays(1), cliente2);
        Pagamento mesPassado = new Pagamento(Collections.singletonList(produto3), LocalDate.now().minusMonths(1), cliente2);

        List<Pagamento> pagamentos = new ArrayList<>();
        pagamentos.addAll(Arrays.asList(hoje, ontem, mesPassado));


        Impressao.imprimirPagamentos(pagamentos);


        BigDecimal somaPagamentoHoje = Pagamento.calcularSomaPagamento(hoje);
        String mensagemPagamentoHoje = "Total pagamentos hoje Double: " + somaPagamentoHoje;

        logger.info(mensagemPagamentoHoje);

        BigDecimal somaPagamentoHojeDouble = Pagamento.calcularSomaPagamento(hoje);
        Double somaDouble = somaPagamentoHojeDouble.doubleValue();
        String mensagem = "Total pagamentos hoje Double: " + somaDouble;
        logger.info(mensagem);


        String valorTotal = "Valor total pagamentos:" + Pagamento.calcularValorTotal(pagamentos);
        logger.info(valorTotal);


        String mensagemQuantidadeProdutosVendidos = "quantidade de produtos vendidos: " + Produto.quantidadePorProduto(pagamentos);
        logger.info(mensagemQuantidadeProdutosVendidos);


        Map<Cliente, List<Produto>> mapaClienteProdutos = new HashMap<>();
        Produto.adicionarProdutos(mapaClienteProdutos, cliente1, produto1, produto2);
        Produto.adicionarProdutos(mapaClienteProdutos, cliente2, produto3, produto1);
        Produto.adicionarProdutos(mapaClienteProdutos, cliente1, produto2, produto3);

        Cliente clienteQueGastouMais = null;
        BigDecimal maiorValorGasto = BigDecimal.ZERO;

        for (Map.Entry<Cliente, List<Produto>> entry : mapaClienteProdutos.entrySet()) {
            Cliente cliente = entry.getKey();
            BigDecimal valorGasto = Produto.calcularValorGasto(entry.getValue());

            if (valorGasto.compareTo(maiorValorGasto) > 0) {
                clienteQueGastouMais = cliente;
                maiorValorGasto = valorGasto;
            }
        }
        String cliente = (clienteQueGastouMais != null ? clienteQueGastouMais.getNome() : "Nenhum cliente encontrado");
        String clienteMaisGastou = String.format("Cliente que mais gastou %s", cliente);
        logger.info(clienteMaisGastou);
        String maiorValorGastoMensagem = "Valor gasto: " + maiorValorGasto;
        logger.info(maiorValorGastoMensagem);


        BigDecimal totalFaturamento = FaturamentoService.getTotalFaturamento(pagamentos, 6, 2023);
        String mensagemFaturamento = String.format("Faturamento total no mês 06: %s ", totalFaturamento);
        logger.info(mensagemFaturamento);

        BigDecimal valorAssinatura = new BigDecimal("99.98");
        Assinatura assinatura1 = new Assinatura(valorAssinatura, LocalDateTime.of(2022, 6, 1, 10, 0), cliente1);
        Assinatura assinatura2 = new Assinatura(valorAssinatura, LocalDateTime.of(2023, 2, 1, 15, 0), LocalDateTime.of(2023, 3, 30, 23, 0), cliente2);
        Assinatura assinatura3 = new Assinatura(valorAssinatura, LocalDateTime.of(2023, 3, 10, 14, 0), LocalDateTime.of(2023, 5, 31, 23, 0), cliente1);


        Long mesesAtivosAssinatura1 = Assinatura.getDuracaoEmMeses(assinatura1);
        Long mesesAtivosAssinatura2 = Assinatura.getDuracaoEmMeses(assinatura2);
        Long mesesAtivosAssinatura3 = Assinatura.getDuracaoEmMeses(assinatura3);

        Impressao.imprimirTempoEmMeses(mesesAtivosAssinatura1);
        Impressao.imprimirTempoEmMeses(mesesAtivosAssinatura2);
        Impressao.imprimirTempoEmMeses(mesesAtivosAssinatura3);


        BigDecimal valorPagoAssinatura1 = assinatura1.getMensalidade().multiply(new BigDecimal(mesesAtivosAssinatura1));
        Impressao.imprimirTotalAssinatura(valorPagoAssinatura1);

        BigDecimal valorPagoAssinatura2 = assinatura2.getMensalidade().multiply(new BigDecimal(mesesAtivosAssinatura2));
        Impressao.imprimirTotalAssinatura(valorPagoAssinatura2);

        BigDecimal valorPagoAssinatura3 = assinatura3.getMensalidade().multiply(new BigDecimal(mesesAtivosAssinatura3));
        Impressao.imprimirTotalAssinatura(valorPagoAssinatura3);


        TipoAssinatura assinaturaTrimestral = new AssinaturaTrimestral(new BigDecimal("100"), LocalDateTime.of(2022, 7, 1, 0, 0), cliente1);
        TipoAssinatura assinaturaSemestral = new AssinaturaSemestral(new BigDecimal("100"), LocalDateTime.of(2022, 7, 1, 0, 0), cliente1);
        TipoAssinatura assinaturaAnual = new AssinaturaAnual(new BigDecimal("100"), LocalDateTime.of(2022, 7, 1, 0, 0), cliente1);

        Impressao.imprimirTaxaAssinatura(assinaturaTrimestral);
        Impressao.imprimirTaxaAssinatura(assinaturaSemestral);
        Impressao.imprimirTaxaAssinatura(assinaturaAnual);


        TipoAssinatura assinaturaAnualAtrasada = new AssinaturaAnual(new BigDecimal("100"), LocalDateTime.of(2022, 7, 1, 0, 0), cliente1, true);

        ValidarAssinatura validarAssinatura = new ValidarAssinatura();
        validarAssinatura.validarPagamentoAssinatura(assinaturaAnualAtrasada);


    }


}
