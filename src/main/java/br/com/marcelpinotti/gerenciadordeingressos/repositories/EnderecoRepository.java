package br.com.marcelpinotti.gerenciadordeingressos.repositories;

import br.com.marcelpinotti.gerenciadordeingressos.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
