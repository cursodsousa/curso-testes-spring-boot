package io.github.cursodsousa.locadora.controller;

import io.github.cursodsousa.locadora.entity.CarroEntity;
import io.github.cursodsousa.locadora.model.exception.EntityNotFoundException;
import io.github.cursodsousa.locadora.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("carros")
public class CarroController {

    private final CarroService service;

    public CarroController(CarroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody CarroEntity carro){
        try {
            var carroSalvo = service.salvar(carro);
            return ResponseEntity.status(HttpStatus.CREATED).body(carroSalvo);
        } catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<CarroEntity> detalhesCarro(@PathVariable("id") Long id){
        try {
            var carroEncontrado = service.buscarPorId(id);
            return ResponseEntity.ok(carroEncontrado);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
