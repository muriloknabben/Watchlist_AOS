package com.watchlist.api.dto;

import com.watchlist.api.model.StatusTitulo;
import com.watchlist.api.model.TipoTitulo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record TituloDTO(
        Long id,

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "O tipo é obrigatório (FILME, SERIE ou ANIME)")
        TipoTitulo tipo,

        String genero,

        @NotNull(message = "O ano é obrigatório")
        @Min(value = 1888, message = "Ano inválido")
        Integer ano,

        StatusTitulo status,

        @Min(value = 0, message = "A nota mínima é 0")
        @Max(value = 10, message = "A nota máxima é 10")
        Integer nota,

        LocalDate dataCadastro
) {
}
