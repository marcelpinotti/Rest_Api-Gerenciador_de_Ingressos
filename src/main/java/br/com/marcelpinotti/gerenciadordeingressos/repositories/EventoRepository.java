package br.com.marcelpinotti.gerenciadordeingressos.repositories;

import br.com.marcelpinotti.gerenciadordeingressos.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
