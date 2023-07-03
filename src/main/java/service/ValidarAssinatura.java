package service;

import exception.AssinaturaAtrasadaException;
import interfaces.TipoAssinatura;

public class ValidarAssinatura {


    public void validarPagamentoAssinatura(TipoAssinatura assinatura) throws AssinaturaAtrasadaException {
        if (Boolean.TRUE.equals(assinatura.isAssinaturaEmAtraso())) {
                throw new AssinaturaAtrasadaException("O cliente " + assinatura.getCliente().getNome() + " não pode realizar a compra pois a assinatura está atrasada");
        }
    }
}
