package br.com.marcelpinotti.gerenciadordeingressos.controllers;

import br.com.marcelpinotti.gerenciadordeingressos.dtos.LocalDeEventoDTO;
import br.com.marcelpinotti.gerenciadordeingressos.dtos.SalvarLocalDeEventoDTO;
import br.com.marcelpinotti.gerenciadordeingressos.entities.LocalDeEvento;
import br.com.marcelpinotti.gerenciadordeingressos.services.LocalDeEventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/local")
public class LocalDeEventoController {

    private LocalDeEventoService localDeEventoService;

    public LocalDeEventoController(LocalDeEventoService service) {
        this.localDeEventoService = service;
    }

    @PostMapping
    public ResponseEntity<LocalDeEvento> salvarLocalDeEvento(@RequestBody @Valid SalvarLocalDeEventoDTO localDeEventoDTO) {
        LocalDeEvento localDeEvento = localDeEventoService.salvarLocalDeEvento(localDeEventoDTO);
        return ResponseEntity.ok(localDeEvento);
    }

    @GetMapping(value = "/todos")
    public ResponseEntity<List<LocalDeEventoDTO>> buscarTodosLocaisDeEventos() {
        List<LocalDeEventoDTO> locais = localDeEventoService.listarTodosOsLocaisDeEventos();
        return ResponseEntity.ok(locais);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LocalDeEventoDTO> buscarLocalDeEventoPorId(@PathVariable("id") Long id) {
        LocalDeEventoDTO local = localDeEventoService.buscarLocalDeEventoPorId(id);
        return ResponseEntity.ok(local);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarLocalDeEvento(@PathVariable ("id") Long id) {
        localDeEventoService.deletarLocalDeEvento(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LocalDeEvento> atualizarLocalDeEvento(@PathVariable("id") Long id,
                                                                @RequestBody @Valid SalvarLocalDeEventoDTO salvarLocalDeEventoDTO) {
        LocalDeEvento local = localDeEventoService.atualizarLocalDeEvento(id, salvarLocalDeEventoDTO);
        return ResponseEntity.ok().body(local);
    }
}
