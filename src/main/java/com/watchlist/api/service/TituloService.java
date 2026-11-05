package com.watchlist.api.service;

import com.watchlist.api.dto.TituloDTO;
import com.watchlist.api.mapper.TituloMapper;
import com.watchlist.api.model.StatusTitulo;
import com.watchlist.api.model.Titulo;
import com.watchlist.api.repository.TituloRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TituloService {

    private final TituloRepository repository;
    private final TituloMapper mapper;

    public TituloService(TituloRepository repository, TituloMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<TituloDTO> listar(StatusTitulo status, String busca) {
        List<Titulo> titulos;
        if (busca != null && !busca.isBlank()) {
            titulos = repository.findByNomeContainingIgnoreCase(busca);
        } else if (status != null) {
            titulos = repository.findByStatus(status);
        } else {
            titulos = repository.findAll();
        }
        return titulos.stream().map(mapper::toDTO).toList();
    }

    public TituloDTO buscarPorId(Long id) {
        return mapper.toDTO(buscarEntidade(id));
    }

    public TituloDTO criar(TituloDTO dto) {
        Titulo titulo = mapper.toEntity(dto);
        validarNota(titulo);
        titulo.setDataCadastro(LocalDate.now());
        return mapper.toDTO(repository.save(titulo));
    }

    public TituloDTO atualizar(Long id, TituloDTO dto) {
        Titulo titulo = buscarEntidade(id);
        mapper.copiarCampos(dto, titulo);
        validarNota(titulo);
        return mapper.toDTO(repository.save(titulo));
    }

    // marca como assistido e (opcionalmente) ja define a nota
    public TituloDTO marcarComoAssistido(Long id, Integer nota) {
        if (nota != null && (nota < 0 || nota > 10)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A nota deve ser de 0 a 10");
        }
        Titulo titulo = buscarEntidade(id);
        titulo.setStatus(StatusTitulo.ASSISTIDO);
        titulo.setNota(nota);
        return mapper.toDTO(repository.save(titulo));
    }

    public void deletar(Long id) {
        repository.delete(buscarEntidade(id));
    }

    private Titulo buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Título não encontrado com id " + id));
    }

    // regra de negocio: so da pra dar nota em titulo que ja foi assistido
    private void validarNota(Titulo titulo) {
        if (titulo.getNota() != null && titulo.getStatus() != StatusTitulo.ASSISTIDO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Só é possível dar nota para títulos com status ASSISTIDO");
        }
    }
}
