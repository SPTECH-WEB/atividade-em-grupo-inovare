package com.inovare.entregas.service.strategy;

public class FreteEconomico implements FreteStrategy {
    @Override
    public double calcularFrete(double peso) {
        return peso*5;
    }
}

