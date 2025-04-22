package com.inovare.entregas.service.observer;

import com.inovare.entregas.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class LogObserver implements PedidoObserver {

    @Override
    public void notificar(Pedido pedido) {
        System.out.println("Log enviado para: " + pedido.getCliente());
    }
}
