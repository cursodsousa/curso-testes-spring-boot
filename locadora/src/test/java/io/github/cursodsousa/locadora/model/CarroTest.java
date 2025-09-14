package io.github.cursodsousa.locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarroTest {

    @Test
    @DisplayName("Deve calcular o valor correto do aluguel")
    void deveCalcularValorAluguel(){
        // 1. Cenário
        Carro carro = new Carro("Sedan", 100.0);

        // 2. Execução
        double total = carro.calcularValorAluguel(3);

        // 3. Verificação
        Assertions.assertEquals(300.0, total);
    }

    @Test
    @DisplayName("Deve calcular o valor do aluguel com desconto")
    void deveCalcularValorAluguelComDesconto(){
        // 1. Cenário
        Carro carro = new Carro("Sedan", 100.0);
        int quantidadeDias = 6;

        // 2. Execução
        double total = carro.calcularValorAluguel(quantidadeDias);

        // 3. Verificação
        Assertions.assertEquals(550.0, total);
    }
}
