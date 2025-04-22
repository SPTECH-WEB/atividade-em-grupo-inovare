package com.inovare.entregas.service;

import com.inovare.entregas.model.Pedido;
import com.inovare.entregas.repository.PedidoRepository;
import com.inovare.entregas.service.adapter.TransportadoraAdapter;
import com.inovare.entregas.service.observer.PedidoObserver;
import com.inovare.entregas.service.strategy.FreteEconomico;
import com.inovare.entregas.service.strategy.FreteExpresso;
import com.inovare.entregas.service.strategy.FreteGratis;
import com.inovare.entregas.service.strategy.FreteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    private PedidoObserver pedidoObserver;

    public Pedido salvarPedido(Pedido pedido) {
        pedidoObserver.notificar(pedido);
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public double calcularFrete(double peso, String tipoFrete) {
        FreteStrategy freteStrategy;


        switch (tipoFrete.toLowerCase()) {
            case "economico":
                freteStrategy = new FreteEconomico();
                break;
            case "expresso":
                freteStrategy = new FreteExpresso();
                break;
            case "gratis":
                freteStrategy = new FreteGratis();
                break;
            case "transportadora":
                freteStrategy = new TransportadoraAdapter();
                break;
            default:
                throw new IllegalArgumentException("Tipo de frete inválido");
        }

        return freteStrategy.calcularFrete(peso);
    }
}