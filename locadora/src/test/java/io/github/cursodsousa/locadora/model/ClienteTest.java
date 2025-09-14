package io.github.cursodsousa.locadora.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClienteTest {

    @Test
    void deveCriarClienteComNome(){
        //1. Cenário
        var cliente = new Cliente("Maria");

        //2. execução
        String nome = cliente.getNome();

        //3. Verificação
        assertNotNull(nome);

        assertThat(nome).isEqualTo("Maria");
        assertThat(nome).isLessThan("Maria5");

        assertTrue(nome.startsWith("M"));
        assertFalse(nome.length() == 100);

        assertThat(nome.length()).isLessThan(100);

        assertThat(nome).contains("Ma");

    }

    @Test
    void deveCriarClienteSemNome(){
        var cliente = new Cliente(null);

        var nome = cliente.getNome();

        assertNull(nome);
    }
}
