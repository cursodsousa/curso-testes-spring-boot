package io.github.cursodsousa.locadora.service;

import io.github.cursodsousa.locadora.entity.CarroEntity;
import io.github.cursodsousa.locadora.model.exception.EntityNotFoundException;
import io.github.cursodsousa.locadora.repository.CarroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

    @InjectMocks
    CarroService service;

    @Mock
    CarroRepository repository;

    @Test
    void deveSalvarUmCarro(){
        CarroEntity carroParaSalvar = new CarroEntity("Sedan", 10.0, 2027);

        CarroEntity carroParaRetornar = new CarroEntity("Sedan", 10.0, 2027);
        carroParaRetornar.setId(1L);

        when( repository.save(any()) ).thenReturn(carroParaRetornar);

        var carroSalvo = service.salvar(carroParaSalvar);

        assertNotNull(carroSalvo);
        assertEquals("Sedan", carroSalvo.getModelo());

        Mockito.verify(repository).save(any());

    }

    @Test
    void deveDarErroAoTentarSalvarCarroComDiariaNegativa(){
        CarroEntity carro = new CarroEntity("Sedan", 0, 2027);

        var erro = catchThrowable( () -> service.salvar(carro) );

        assertThat(erro).isInstanceOf(IllegalArgumentException.class);

        Mockito.verify(repository, Mockito.never()).save(any());
    }

    @Test
    void deveAtualizarUmCarro(){

        var carroExistente = new CarroEntity("Gol", 80.0, 2026);
        when(repository.findById(1L)).thenReturn(Optional.of(carroExistente));

        var carroAtualizado = new CarroEntity("Gol", 80.0, 2026);
        carroAtualizado.setId(1L);
        when(repository.save(any())).thenReturn(carroAtualizado);

        Long id = 1L;
        var carro = new CarroEntity("Sedan", 0, 2027);

        var resultado = service.atualizar(id, carro);

        assertEquals(resultado.getModelo(), "Gol");
        Mockito.verify(repository, Mockito.times(1)).save(any());
    }

    @Test
    void deveDarErroAoTentarAtualizarCarroInexistente(){
        Long id = 1L;
        var carro = new CarroEntity("Sedan", 10, 2027);

        when(repository.findById(any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> service.atualizar(id, carro));

        assertThat(erro).isInstanceOf(EntityNotFoundException.class);

        Mockito.verify(repository, Mockito.never()).save(any());
    }

    @Test
    void deveDarErroAoTentarDeletarCarroInexistente(){
        Long id = 1L;

        when(repository.findById(any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> service.deletar(id));

        assertThat(erro).isInstanceOf(EntityNotFoundException.class);

        Mockito.verify(repository, Mockito.never()).delete(any());
    }

    @Test
    void deveDeletarUmCarro(){
        Long id = 1L;
        var carro = new CarroEntity("Sedan", 10, 2027);

        when(repository.findById(any())).thenReturn(Optional.of(carro));

        service.deletar(id);

        Mockito
                .verify(repository, Mockito.times(1))
                .delete(carro);
    }

    @Test
    void deveBuscarCarroPorId(){
        Long id = 1L;
        var carro = new CarroEntity("Sedan", 10, 2027);
        when(repository.findById(any())).thenReturn(Optional.of(carro));

        var carroEncontrado = service.buscarPorId(id);

        assertThat(carroEncontrado.getModelo()).isEqualTo("Sedan");
        assertThat(carroEncontrado.getValorDiaria()).isEqualTo(10);
        assertThat(carroEncontrado.getAno()).isEqualTo(2027);
    }

    @Test
    void deveListarTodos(){
        var carro = new CarroEntity(1L,"Sedan", 10, 2027);
        var carro2 = new CarroEntity(2L,"Hatch", 10, 2027);

        var lista = List.of(carro, carro2);
        when(repository.findAll()).thenReturn(lista);

        List<CarroEntity> resultado = service.listarTodos();

        assertThat(resultado).hasSize(2);
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

}