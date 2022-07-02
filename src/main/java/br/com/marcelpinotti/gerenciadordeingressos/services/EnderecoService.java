package br.com.marcelpinotti.gerenciadordeingressos.services;

import br.com.marcelpinotti.gerenciadordeingressos.dtos.EnderecoDTO;
import br.com.marcelpinotti.gerenciadordeingressos.entities.Endereco;
import br.com.marcelpinotti.gerenciadordeingressos.repositories.EnderecoRepository;
import br.com.marcelpinotti.gerenciadordeingressos.services.viaCepService.ViaCepService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;
    private ViaCepService viaCepService;

    private ModelMapper mapper;

    public EnderecoService(EnderecoRepository enderecoRepository, ViaCepService viaCepService) {
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
    }

    private Endereco buscarEnderecoPorCepViaCEP(String cep) {

        Endereco endereco = viaCepService.consultarCep(cep);

        if (endereco.getCep() == null) {
            throw new RuntimeException("Endereço não encontrado");
        } else {
            return endereco;
        }
    }

    private void buscarEnderecoPorCepParaSalvar(String cep) {

        Optional<Endereco> endereco = enderecoRepository.findById(cep);

        if (endereco.isPresent()){
            throw new RuntimeException("O Endereço já existe");
        }
    }

    public EnderecoDTO buscarEnderecoPorCep(String cep) {

        Optional<Endereco> enderecoOptional = enderecoRepository.findById(cep);

        if (enderecoOptional.isPresent()){
            return mapper.map(enderecoOptional.get(), EnderecoDTO.class);
        } else {
            throw new RuntimeException("Endereço não encontrado");
        }
    }

    public List<EnderecoDTO> listarTodosOsEnderecos() {

        List<Endereco> enderecos = enderecoRepository.findAll();

        List<EnderecoDTO> listaEnderecos = enderecos.stream()
                .map(endereco -> mapper.map(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());

        return listaEnderecos;
    }

    public Endereco salvarEndereco(String cep) {

        buscarEnderecoPorCepParaSalvar(cep);
        Endereco enderecoViaCep = buscarEnderecoPorCepViaCEP(cep);

        return enderecoRepository.save(enderecoViaCep);
    }

    public void deletarEndereco(String cep) {
        buscarEnderecoPorCep(cep);

        enderecoRepository.deleteById(cep);
    }

    public Endereco atualizarEndereco(String cep, EnderecoDTO enderecoAtualizado) {
        buscarEnderecoPorCep(cep);

        Endereco endereco = new Endereco();

        endereco.setCep(cep);
        endereco.setLogradouro(enderecoAtualizado.getLogradouro());
        endereco.setBairro(enderecoAtualizado.getBairro());
        endereco.setLocalidade(enderecoAtualizado.getLocalidade());
        endereco.setUf(enderecoAtualizado.getUf());

        return enderecoRepository.save(endereco);
    }

}
