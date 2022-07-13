package br.com.marcelpinotti.gerenciadordeingressos.services;

import br.com.marcelpinotti.gerenciadordeingressos.dtos.EnderecoDTO;
import br.com.marcelpinotti.gerenciadordeingressos.entities.Endereco;
import br.com.marcelpinotti.gerenciadordeingressos.exception.ObjectExistsException;
import br.com.marcelpinotti.gerenciadordeingressos.exception.ObjectNotFoundException;
import br.com.marcelpinotti.gerenciadordeingressos.repositories.EnderecoRepository;
import br.com.marcelpinotti.gerenciadordeingressos.services.format_zip_code.VerificarCep;
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
    private VerificarCep verificarCep;

    public EnderecoService(EnderecoRepository enderecoRepository, ViaCepService viaCepService,
                           ModelMapper mapper, VerificarCep verificarCep) {
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
        this.mapper = mapper;
        this.verificarCep = verificarCep;
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

        String cepFormatado = String.valueOf(verificarCep.vericiacaoDeCep(cep));

        Optional<Endereco> enderecoOptional = enderecoRepository.findById(cepFormatado);

        if (enderecoOptional.isPresent()) {
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

    public Endereco salvarEndereco(String cep) {

        String cepFormatado = verificarCep.vericiacaoDeCep(cep);

        buscarEnderecoPorCepParaSalvar(cepFormatado);
        Endereco enderecoViaCep = buscarEnderecoPorCepViaCEP(cepFormatado);

        return enderecoRepository.save(enderecoViaCep);
    }

    public EnderecoDTO salvarEnderecoPeloLocalDeEvento(String cep) {

        String cepFormatado = String.valueOf(verificarCep.vericiacaoDeCep(cep));

        Optional<Endereco> enderecoOptional = enderecoRepository.findById(cepFormatado);

        if (enderecoOptional.isPresent()) {
            return mapper.map(enderecoOptional, EnderecoDTO.class);
        } else {
            Endereco enderecoViaCep = buscarEnderecoPorCepViaCEP(cepFormatado);
            enderecoRepository.save(enderecoViaCep);
            return mapper.map(enderecoViaCep, EnderecoDTO.class);
        }
    }

    public void deletarEndereco(String cep) {

        EnderecoDTO enderecoDTO = buscarEnderecoPorCep(cep);

        enderecoRepository.deleteById(enderecoDTO.getCep());
    }

    public Endereco atualizarEndereco(String cep, EnderecoDTO enderecoAtualizado) {

        EnderecoDTO enderecoDTO = buscarEnderecoPorCep(cep);

        Endereco endereco = new Endereco();

        endereco.setCep(enderecoDTO.getCep());
        endereco.setLogradouro(enderecoAtualizado.getLogradouro());
        endereco.setBairro(enderecoAtualizado.getBairro());
        endereco.setLocalidade(enderecoAtualizado.getLocalidade());
        endereco.setUf(enderecoAtualizado.getUf());

        return enderecoRepository.save(endereco);
    }

}
