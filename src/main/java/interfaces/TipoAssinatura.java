package interfaces;

import model.Cliente;

import java.math.BigDecimal;

public interface TipoAssinatura {

    BigDecimal calcularTaxaAssinatura();

    Boolean isAssinaturaEmAtraso();

    Cliente getCliente();
}
