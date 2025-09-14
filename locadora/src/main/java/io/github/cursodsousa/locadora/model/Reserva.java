package io.github.cursodsousa.locadora.model;

import io.github.cursodsousa.locadora.model.exception.ReservaInvalidaException;

public class Reserva {

    private Cliente cliente;
    private Carro carro;
    private int dias;

    public Reserva(Cliente cliente, Carro carro, int dias) {
        if(dias < 1){
            throw new ReservaInvalidaException("A Reserva não pode ter uma quantidade de dias menor que 1.");
        }
        this.cliente = cliente;
        this.carro = carro;
        this.dias = dias;
    }

    public double calcularTotal(){
        return this.carro.calcularValorAluguel(this.dias);
    }
}
