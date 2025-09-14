package io.github.cursodsousa.locadora.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carro")
public class CarroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private double valorDiaria;
    private int ano;

    public CarroEntity() {
    }

    public CarroEntity(String modelo, double valorDiaria, int ano) {
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
        this.ano = ano;
    }

    public CarroEntity(Long id, String modelo, double valorDiaria, int ano) {
        this(modelo, valorDiaria, ano);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
