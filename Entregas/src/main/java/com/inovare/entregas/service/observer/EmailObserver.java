package com.inovare.entregas.service.observer;

import com.inovare.entregas.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class EmailObserver implements PedidoObserver {

    @Override
    public void notificar(Pedido pedido) {
        System.out.println("Email enviado para: " + pedido.getCliente());
    }
}
