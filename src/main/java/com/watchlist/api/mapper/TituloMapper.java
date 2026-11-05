package com.watchlist.api.mapper;

import com.watchlist.api.dto.TituloDTO;
import com.watchlist.api.model.StatusTitulo;
import com.watchlist.api.model.Titulo;
import org.springframework.stereotype.Component;

@Component
public class TituloMapper {

    public Titulo toEntity(TituloDTO dto) {
        Titulo titulo = new Titulo();
        copiarCampos(dto, titulo);
        return titulo;
    }

    // copia os dados do DTO para uma entidade (usado no criar e no atualizar)
    public void copiarCampos(TituloDTO dto, Titulo titulo) {
        titulo.setNome(dto.nome());
        titulo.setTipo(dto.tipo());
        titulo.setGenero(dto.genero());
        titulo.setAno(dto.ano());
        titulo.setNota(dto.nota());
        // se nao informar status, entra como "quero assistir"
        titulo.setStatus(dto.status() != null ? dto.status() : StatusTitulo.QUERO_ASSISTIR);
    }

    public TituloDTO toDTO(Titulo titulo) {
        return new TituloDTO(
                titulo.getId(),
                titulo.getNome(),
                titulo.getTipo(),
                titulo.getGenero(),
                titulo.getAno(),
                titulo.getStatus(),
                titulo.getNota(),
                titulo.getDataCadastro()
        );
    }
}
