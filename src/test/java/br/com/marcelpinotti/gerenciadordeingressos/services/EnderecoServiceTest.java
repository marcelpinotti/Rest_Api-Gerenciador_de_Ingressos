package br.com.marcelpinotti.gerenciadordeingressos.services;

import br.com.marcelpinotti.gerenciadordeingressos.dtos.EnderecoDTO;
import br.com.marcelpinotti.gerenciadordeingressos.entities.Endereco;
import br.com.marcelpinotti.gerenciadordeingressos.repositories.EnderecoRepository;
import br.com.marcelpinotti.gerenciadordeingressos.services.ViaCepService.ViaCepService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class EnderecoServiceTest {

    private static final String CEP = "03201-000";
    private static final String LOGRADOURO = "Rua das Cobéias";
    private static final String BAIRRO = "Vila Bela";
    private static final String CIDADE = "São Paulo";
    private static final String UF = "SP";
    private Endereco endereco;
    private EnderecoDTO enderecoDTO;
    private Optional<Endereco> enderecoOptional;

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private EnderecoRepository enderecoRepository;

    private void iniciandoEnderecos() {
        endereco = new Endereco(CEP, LOGRADOURO, BAIRRO, CIDADE, UF);
        enderecoDTO = new EnderecoDTO(CEP, LOGRADOURO, BAIRRO, CIDADE, UF);;
        enderecoOptional = Optional.of(new Endereco(CEP, LOGRADOURO, BAIRRO, CIDADE, UF));
    }

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        iniciandoEnderecos();
    }

    @Test
    void quandoRetornarUmaBuscaDeEnderecoPorCep() {
        Mockito.when(enderecoRepository.findById(Mockito.anyString())).thenReturn(enderecoOptional);
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(enderecoDTO);

        EnderecoDTO enderecoDeBusca = enderecoService.buscarEnderecoPorCep(CEP);

        Assertions.assertNotNull(enderecoDeBusca);
        Assertions.assertEquals(EnderecoDTO.class, enderecoDeBusca.getClass());
        Assertions.assertEquals(CEP, enderecoDeBusca.getCep());
        Assertions.assertEquals(LOGRADOURO, enderecoDeBusca.getLogradouro());
        Assertions.assertEquals(BAIRRO, enderecoDeBusca.getBairro());
        Assertions.assertEquals(CIDADE, enderecoDeBusca.getLocalidade());
        Assertions.assertEquals(UF, enderecoDeBusca.getUf());
    }


    
}