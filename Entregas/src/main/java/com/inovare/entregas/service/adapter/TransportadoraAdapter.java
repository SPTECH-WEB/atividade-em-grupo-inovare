package com.inovare.entregas.service.adapter;

import com.inovare.entregas.service.strategy.FreteStrategy;




    public class TransportadoraAdapter implements FreteStrategy {

        @Override
        public double calcularFrete(double peso) {

            double taxaFixa = 12.5;
            double frete = peso * 3 + taxaFixa;

            System.out.println("[Adapter] Frete calculado com transportadora externa: R$" + frete);
            return frete;
        }
    }


