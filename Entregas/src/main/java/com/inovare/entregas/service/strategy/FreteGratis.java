package com.inovare.entregas.service.strategy;

public class FreteGratis implements FreteStrategy {
    @Override
    public double calcularFrete(double peso) {
        return peso * 0;
    }
}
