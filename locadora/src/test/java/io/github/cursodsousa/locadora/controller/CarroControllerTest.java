package io.github.cursodsousa.locadora.controller;

import io.github.cursodsousa.locadora.entity.CarroEntity;
import io.github.cursodsousa.locadora.model.exception.EntityNotFoundException;
import io.github.cursodsousa.locadora.service.CarroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarroController.class)
public class CarroControllerTest {

    @Autowired
    MockMvc mvc;

//    @MockBean
    @MockitoBean
    CarroService carroService;

    @Test
    void deveSalvarUmCarro() throws Exception {
        //cenário
        CarroEntity carro = new CarroEntity(
                1L, "Honda Civic", 150, 2027);

        when(carroService.salvar(Mockito.any())).thenReturn(carro);

        String json = """
                {
                    "modelo": "Honda Civic",
                    "valorDiaria": 150,
                    "ano": 2027
                }
                """;

        //execução
        ResultActions result = mvc.perform(
                post("/carros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)

        );

        //verificação
        result
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.modelo").value("Honda Civic"));

    }

    @Test
    void deveObterDetalhesCarro() throws Exception {
        when(carroService.buscarPorId(Mockito.any())).thenReturn(new CarroEntity(
                1L, "Civic", 250, 2028
        ));


        mvc.perform(
            MockMvcRequestBuilders.get("/carros/1")
        ).andExpect(status().isOk())
         .andExpect(jsonPath("$.id").value(1))
         .andExpect(jsonPath("$.modelo").value("Civic"))
         .andExpect(jsonPath("$.valorDiaria").value(250))
         .andExpect(jsonPath("$.ano").value(2028));

    }

    @Test
    void deveRetornarNotFoundAoTentarObterDetalhesCarroInexistente() throws Exception {
        when(carroService.buscarPorId(Mockito.any())).thenThrow(EntityNotFoundException.class);

        mvc.perform(
                MockMvcRequestBuilders.get("/carros/1")
        ).andExpect(status().isNotFound());

    }
}
