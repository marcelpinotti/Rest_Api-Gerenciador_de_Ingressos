package br.com.marcelpinotti.gerenciadordeingressos.services;

import br.com.marcelpinotti.gerenciadordeingressos.dtos.EnderecoDTO;
import br.com.marcelpinotti.gerenciadordeingressos.entities.Endereco;
import br.com.marcelpinotti.gerenciadordeingressos.exception.ObjectExistsException;
import br.com.marcelpinotti.gerenciadordeingressos.exception.ObjectNotFoundException;
import br.com.marcelpinotti.gerenciadordeingressos.repositories.EnderecoRepository;
import br.com.marcelpinotti.gerenciadordeingressos.services.viaCepService.ViaCepService;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;
    private ViaCepService viaCepService;
    private ModelMapper mapper;

    public EnderecoService(EnderecoRepository enderecoRepository, ViaCepService viaCepService, ModelMapper mapper) {
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
        this.mapper = mapper;
    }

    private Endereco buscarEnderecoPorCepViaCEP(String cep) {

        Endereco endereco = viaCepService.consultarCep(cep);

        if (endereco.getCep() == null) {
            throw new ObjectNotFoundException("Endereço não encontrado");
        } else {
            return endereco;
        }
    }

    private void buscarEnderecoPorCepParaSalvar(String cep) {

        Optional<Endereco> endereco = enderecoRepository.findById(cep);

        if (endereco.isPresent()){
            throw new ObjectExistsException("O Endereço já existe na base de dados");
        }
    }

    public EnderecoDTO buscarEnderecoPorCep(String cep) {

        Optional<Endereco> enderecoOptional = enderecoRepository.findById(cep);

        if (enderecoOptional.isPresent()){
            return mapper.map(enderecoOptional.get(), EnderecoDTO.class);
        } else {
            throw new ObjectNotFoundException("Endereço não encontrado");
        }
    }

    public List<EnderecoDTO> listarTodosOsEnderecos() {

        List<Endereco> enderecos = enderecoRepository.findAll();

        List<EnderecoDTO> listaEnderecos = enderecos.stream()
                .map(endereco -> mapper.map(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());

        return listaEnderecos;
    }

    private String formatarCep(String cep) {

        String validar = cep.substring(5,6);

        if(!validar.equals("-"))
            cep = cep.substring(0, 5) + '-' + cep.substring(5);

        return cep;
    }

    public Endereco salvarEndereco(String cep) {

        String cepFormatado = formatarCep(cep);

        buscarEnderecoPorCepParaSalvar(cepFormatado);
        Endereco enderecoViaCep = buscarEnderecoPorCepViaCEP(cepFormatado);

        return enderecoRepository.save(enderecoViaCep);
    }

    public void deletarEndereco(String cep) {
        buscarEnderecoPorCep(cep);

        enderecoRepository.deleteById(cep);
    }

    public Endereco atualizarEndereco(String cep, EnderecoDTO enderecoAtualizado) {

        String cepFormatado = formatarCep(cep);
        buscarEnderecoPorCep(cepFormatado);

        Endereco endereco = new Endereco();

        endereco.setCep(cepFormatado);
        endereco.setLogradouro(enderecoAtualizado.getLogradouro());
        endereco.setBairro(enderecoAtualizado.getBairro());
        endereco.setLocalidade(enderecoAtualizado.getLocalidade());
        endereco.setUf(enderecoAtualizado.getUf());

        return enderecoRepository.save(endereco);
    }

}
