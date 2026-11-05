package com.watchlist.api.controller;

import com.watchlist.api.dto.TituloDTO;
import com.watchlist.api.model.StatusTitulo;
import com.watchlist.api.service.TituloService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/titulos")
public class TituloController {

    private final TituloService service;

    public TituloController(TituloService service) {
        this.service = service;
    }

    // GET /api/titulos?status=ASSISTIDO  ou  GET /api/titulos?busca=matrix
    @GetMapping
    public List<TituloDTO> listar(@RequestParam(required = false) StatusTitulo status,
                                  @RequestParam(required = false) String busca) {
        return service.listar(status, busca);
    }

    @GetMapping("/{id}")
    public TituloDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TituloDTO criar(@Valid @RequestBody TituloDTO dto) {
        return service.criar(dto);
    }

    @PutMapping("/{id}")
    public TituloDTO atualizar(@PathVariable Long id, @Valid @RequestBody TituloDTO dto) {
        return service.atualizar(id, dto);
    }

    // PATCH /api/titulos/1/assistido?nota=9
    @PatchMapping("/{id}/assistido")
    public TituloDTO marcarComoAssistido(@PathVariable Long id,
                                         @RequestParam(required = false) Integer nota) {
        return service.marcarComoAssistido(id, nota);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
