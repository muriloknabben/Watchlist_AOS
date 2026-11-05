package com.watchlist.api.repository;

import com.watchlist.api.model.StatusTitulo;
import com.watchlist.api.model.Titulo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TituloRepository extends JpaRepository<Titulo, Long> {

    List<Titulo> findByStatus(StatusTitulo status);

    List<Titulo> findByNomeContainingIgnoreCase(String nome);
}
