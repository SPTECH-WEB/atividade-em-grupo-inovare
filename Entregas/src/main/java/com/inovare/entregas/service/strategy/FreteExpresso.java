package com.inovare.entregas.service.strategy;

public class FreteExpresso implements FreteStrategy {
    @Override
    public double calcularFrete(double peso) {
        return peso*7;
    }

}
