package com.inovare.entregas.service.observer;


import com.inovare.entregas.model.Pedido;

public interface PedidoObserver {
    void notificar(Pedido pedido);
}
