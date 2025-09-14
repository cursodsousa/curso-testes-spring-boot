package io.github.cursodsousa.locadora.repository;

import io.github.cursodsousa.locadora.entity.CarroEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarroRepositoryTest {

    @Autowired
    CarroRepository repository;

    CarroEntity carro;

    @BeforeEach
    void setUp(){
        carro = new CarroEntity("Honda Civic", 200.0, 2027);
    }

    @Test
    void deveSalvarUmCarro(){
        var entity = new CarroEntity("Sedan", 100.0, 2027);

        repository.save(entity);

        assertNotNull(entity.getId());
    }

    @Test
    void deveBuscarCarroPorId(){
        var carroSalvo = repository.save(carro);

        Optional<CarroEntity> carroEncontrado = repository.findById(carroSalvo.getId());

        assertThat(carroEncontrado).isPresent();
        assertThat(carroEncontrado.get().getModelo()).isEqualTo("Honda Civic");
    }

    @Test
    void deveAtualizarCarro(){
        var carroSalvo = repository.save(carro);

        carroSalvo.setAno(2028);

        var carroAtualizado = repository.save(carroSalvo);

        assertThat(carroAtualizado.getAno()).isEqualTo(2028);
    }

    @Test
    void deveDeletarCarro(){
        var carroSalvo = repository.save(carro);

        repository.deleteById(carroSalvo.getId());

        Optional<CarroEntity> carroEncontrado = repository.findById(carroSalvo.getId());

        assertThat(carroEncontrado).isEmpty();

    }
}