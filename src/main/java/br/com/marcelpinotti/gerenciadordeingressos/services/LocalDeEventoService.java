package br.com.marcelpinotti.gerenciadordeingressos.services;

import br.com.marcelpinotti.gerenciadordeingressos.dtos.EnderecoDTO;
import br.com.marcelpinotti.gerenciadordeingressos.dtos.LocalDeEventoDTO;
import br.com.marcelpinotti.gerenciadordeingressos.dtos.SalvarLocalDeEventoDTO;
import br.com.marcelpinotti.gerenciadordeingressos.entities.LocalDeEvento;
import br.com.marcelpinotti.gerenciadordeingressos.exception.ObjectNotFoundException;
import br.com.marcelpinotti.gerenciadordeingressos.repositories.LocalDeEventoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocalDeEventoService {

    private ModelMapper modelMapper;

    private LocalDeEventoRepository localDeEventoRepository;

    private EnderecoService enderecoService;

    public LocalDeEventoService(LocalDeEventoRepository repository, ModelMapper mapper, EnderecoService endereco) {
        this.localDeEventoRepository = repository;
        this.modelMapper = mapper;
        this.enderecoService = endereco;
    }

    public List<LocalDeEventoDTO> listarTodosOsLocaisDeEventos() {

        List<LocalDeEvento> locaisDeEventos = localDeEventoRepository.findAll();

        List<LocalDeEventoDTO> localDeEventoDTOS = locaisDeEventos.stream()
                .map(locais -> modelMapper.map(locais, LocalDeEventoDTO.class))
                .collect(Collectors.toList());

        return localDeEventoDTOS;
    }

    public LocalDeEventoDTO buscarLocalDeEventoPorId(Long id) {

        Optional<LocalDeEvento> localOptional = localDeEventoRepository.findById(id);

        if(localOptional.isPresent()) {
            LocalDeEvento localDeEvento = localOptional.get();
            return modelMapper.map(localDeEvento, LocalDeEventoDTO.class);
        } else {
            throw new ObjectNotFoundException("Local de Evento não encontrato");
        }
    }

    public LocalDeEvento salvarLocalDeEvento(SalvarLocalDeEventoDTO salvarLocalDeEventoDTO) {

        EnderecoDTO enderecoDTO = enderecoService
                                .salvarEnderecoPeloLocalDeEvento(salvarLocalDeEventoDTO.getEndereco());

        LocalDeEventoDTO localDeEventoDTO = new LocalDeEventoDTO();
        localDeEventoDTO.setNome(salvarLocalDeEventoDTO.getNome());
        localDeEventoDTO.setEndereco(enderecoDTO);

        LocalDeEvento localDeEvento = modelMapper.map(localDeEventoDTO, LocalDeEvento.class);

        return localDeEventoRepository.save(localDeEvento);
    }

    public void deletarLocalDeEvento (Long id) {
        buscarLocalDeEventoPorId(id);
        localDeEventoRepository.deleteById(id);
    }

    public LocalDeEvento atualizarLocalDeEvento(Long id, SalvarLocalDeEventoDTO salvarLocalDeEventoDTO) {

        LocalDeEventoDTO localDeEventoDTO = buscarLocalDeEventoPorId(id);
        EnderecoDTO enderecoDTO = enderecoService
                                .salvarEnderecoPeloLocalDeEvento(salvarLocalDeEventoDTO.getEndereco());

        localDeEventoDTO.setNome(salvarLocalDeEventoDTO.getNome());
        localDeEventoDTO.setEndereco(enderecoDTO);
        LocalDeEvento localDeEvento = modelMapper.map(localDeEventoDTO, LocalDeEvento.class);

        localDeEventoRepository.save(localDeEvento);

        return localDeEvento;
    }

}
