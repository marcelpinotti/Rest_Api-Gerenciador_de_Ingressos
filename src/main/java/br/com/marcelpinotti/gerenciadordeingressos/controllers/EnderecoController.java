package br.com.marcelpinotti.gerenciadordeingressos.controllers;


import br.com.marcelpinotti.gerenciadordeingressos.dtos.EnderecoDTO;
import br.com.marcelpinotti.gerenciadordeingressos.entities.Endereco;
import br.com.marcelpinotti.gerenciadordeingressos.services.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/endereco")
public class EnderecoController {

    private EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping (value = "/{cep}")
    public ResponseEntity<Endereco> salvandoEndereco(@PathVariable("cep") String cep) {
        Endereco endereco = enderecoService.salvarEndereco(cep);
        return ResponseEntity.ok(endereco);
    }

    @GetMapping (value = "/{cep}")
    public ResponseEntity<EnderecoDTO> buscarEnderecoPorCep(@PathVariable("cep") String cep) {
            EnderecoDTO endereco = enderecoService.buscarEnderecoPorCep(cep);
            return ResponseEntity.ok(endereco);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> buscarTodosOsEnderecos() {
        List<EnderecoDTO> enderecos = enderecoService.listarTodosOsEnderecos();
        return ResponseEntity.ok(enderecos);
    }

    @DeleteMapping(value = "/{cep}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable("cep") String cep) {
        enderecoService.deletarEndereco(cep);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{cep}")
    public ResponseEntity<Endereco> atualizarEndereco (@PathVariable("cep") String cep, @RequestBody EnderecoDTO enderecoAtualizado) {
        Endereco endereco = enderecoService.atualizarEndereco(cep, enderecoAtualizado);
        return ResponseEntity.ok().body(endereco);
    }
}
