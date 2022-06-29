package br.com.marcelpinotti.gerenciadordeingressos.repositories;

import br.com.marcelpinotti.gerenciadordeingressos.entities.LocalDeEvento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalDeEventoRepository extends JpaRepository<LocalDeEvento, Long> {
}
