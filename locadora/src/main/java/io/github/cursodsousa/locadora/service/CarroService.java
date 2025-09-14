package io.github.cursodsousa.locadora.service;

import io.github.cursodsousa.locadora.entity.CarroEntity;
import io.github.cursodsousa.locadora.model.exception.EntityNotFoundException;
import io.github.cursodsousa.locadora.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    private final CarroRepository repository;

    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    public CarroEntity salvar(CarroEntity carro){
        if(carro.getValorDiaria() <= 0){
            throw new IllegalArgumentException("Preço da diária não pode ser negativo.");
        }
        return repository.save(carro);
    }

    public CarroEntity atualizar(Long id, CarroEntity carroAtualizado){
        var carroExistente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado!"));

        carroExistente.setAno(carroAtualizado.getAno());
        carroExistente.setModelo(carroAtualizado.getModelo());
        carroExistente.setValorDiaria(carroAtualizado.getValorDiaria());

        return repository.save(carroExistente);
    }

    public void deletar(Long id){
        var carroExistente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado!"));
        repository.delete(carroExistente);
    }

    public CarroEntity buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado!"));
    }

    public List<CarroEntity> listarTodos(){
        return repository.findAll();
    }
}
