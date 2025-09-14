package io.github.cursodsousa.locadora.model;

import io.github.cursodsousa.locadora.model.exception.ReservaInvalidaException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservaTest {

    Cliente cliente;
    Carro carro;

    @BeforeEach
    void setUp(){
        cliente = new Cliente("José");
        carro = new Carro("Hatch", 50.0);
    }

    @Test
    void deveCriarUmaReserva(){

        // cenário
        var dias = 5;

        // execucao
        var reserva = new Reserva(cliente, carro, dias);

        // verificacao
        Assertions.assertThat(reserva).isNotNull();
    }

    @Test
    void deveDarErroAoCriarUmaReservaComDiasNegativos(){

        // JUnit
        assertThrows(ReservaInvalidaException.class, () -> new Reserva(cliente, carro, 0) );
        assertDoesNotThrow(() -> new Reserva(cliente, carro, 1) );

        //AssertJ
        var erro = Assertions.catchThrowable(() -> new Reserva(cliente, carro, 0) );

        Assertions.assertThat(erro)
                .isInstanceOf(ReservaInvalidaException.class)
                .hasMessage("A Reserva não pode ter uma quantidade de dias menor que 1.");
    }

    @Test
    void deveCalcularOTotaldoAluguel(){
        var reserva = new Reserva(cliente, carro, 3);

        var total = reserva.calcularTotal();

        Assertions.assertThat(total).isEqualTo(150.0);
    }
}