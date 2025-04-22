package com.inovare.entregas.service.observer;

import com.inovare.entregas.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoObserver implements PedidoObserver{

    @Override
    public void notificar(Pedido pedido) {
        System.out.println("Usuário notificado: " + pedido.getCliente());
    }
}
