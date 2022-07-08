package br.com.marcelpinotti.gerenciadordeingressos.services;

import br.com.marcelpinotti.gerenciadordeingressos.dtos.EnderecoDTO;
import br.com.marcelpinotti.gerenciadordeingressos.entities.Endereco;
import br.com.marcelpinotti.gerenciadordeingressos.repositories.EnderecoRepository;

import br.com.marcelpinotti.gerenciadordeingressos.services.format_zip_code.VerificarCep;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EnderecoServiceTest {

    private static final String CEP = "03201-000";
    private static final String CEPSemHifem = "03201000";
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
    private VerificarCep verificarCep;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private br.com.marcelpinotti.gerenciadordeingressos.services.viaCepService.ViaCepService viaCepService;

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
    void deveRetornarUmEnderecoAoBuscarEnderecoPorCepComHifen() {
        Mockito.when(enderecoRepository.findById(Mockito.anyString())).thenReturn(enderecoOptional);
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(enderecoDTO);
        Mockito.when(verificarCep.vericiacaoDeCep(Mockito.anyString())).thenReturn(CEP);

        EnderecoDTO enderecoDeBusca = enderecoService.buscarEnderecoPorCep(CEP);

        Assertions.assertNotNull(enderecoDeBusca);
        Assertions.assertEquals(EnderecoDTO.class, enderecoDeBusca.getClass());
        Assertions.assertEquals(CEP, enderecoDeBusca.getCep());
        Assertions.assertEquals(LOGRADOURO, enderecoDeBusca.getLogradouro());
        Assertions.assertEquals(BAIRRO, enderecoDeBusca.getBairro());
        Assertions.assertEquals(CIDADE, enderecoDeBusca.getLocalidade());
        Assertions.assertEquals(UF, enderecoDeBusca.getUf());
    }

    @Test
    void deveRetornarUmEnderecoAoBuscarEnderecoPorCepSemHifen() {
        Mockito.when(enderecoRepository.findById(Mockito.anyString())).thenReturn(enderecoOptional);
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(enderecoDTO);
        Mockito.when(verificarCep.vericiacaoDeCep(Mockito.anyString())).thenReturn(CEP);

        EnderecoDTO enderecoDeBusca = enderecoService.buscarEnderecoPorCep(CEPSemHifem);

        Assertions.assertNotNull(enderecoDeBusca);
        Assertions.assertEquals(EnderecoDTO.class, enderecoDeBusca.getClass());
        Assertions.assertEquals(CEP, enderecoDeBusca.getCep());
        Assertions.assertEquals(LOGRADOURO, enderecoDeBusca.getLogradouro());
        Assertions.assertEquals(BAIRRO, enderecoDeBusca.getBairro());
        Assertions.assertEquals(CIDADE, enderecoDeBusca.getLocalidade());
        Assertions.assertEquals(UF, enderecoDeBusca.getUf());
    }

    @Test
    void deveRetornarUmaListaDeEnderecosAoListarTodosOsEnderecos() {
        Mockito.when(enderecoRepository.findAll()).thenReturn(List.of(endereco));
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(enderecoDTO);

        List<EnderecoDTO> listaDeEnderecos = enderecoService.listarTodosOsEnderecos();

        Assertions.assertNotNull(listaDeEnderecos);
        Assertions.assertEquals(EnderecoDTO.class, listaDeEnderecos.get(0).getClass());
        Assertions.assertEquals(listaDeEnderecos.get(0).getCep(), endereco.getCep());
        Assertions.assertEquals(1, listaDeEnderecos.size());
    }

    @Test
    void deveSalvarUmEnderecoEmSalvarEnderecoOndeOCepVemComHifen() {
        Mockito.when(enderecoRepository.save(Mockito.any())).thenReturn(endereco);
        Mockito.when(viaCepService.consultarCep(Mockito.anyString())).thenReturn(endereco);
        Mockito.when(verificarCep.vericiacaoDeCep(Mockito.anyString())).thenReturn(CEP);

        Endereco enderecoViaCep = enderecoService.salvarEndereco(CEP);

        Assertions.assertNotNull(enderecoViaCep);
        Assertions.assertEquals(Endereco.class, enderecoViaCep.getClass());
        Assertions.assertEquals(CEP, enderecoViaCep.getCep());
        Assertions.assertEquals(LOGRADOURO, enderecoViaCep.getLogradouro());
        Assertions.assertEquals(BAIRRO, enderecoViaCep.getBairro());
        Assertions.assertEquals(CIDADE, enderecoViaCep.getLocalidade());
        Assertions.assertEquals(UF, enderecoViaCep.getUf());
    }

    @Test
    void deveSalvarUmEnderecoEmSalvarEnderecoOndeOCepVemSemHifen() {
        Mockito.when(enderecoRepository.save(Mockito.any())).thenReturn(endereco);
        Mockito.when(viaCepService.consultarCep(Mockito.anyString())).thenReturn(endereco);
        Mockito.when(verificarCep.vericiacaoDeCep(Mockito.anyString())).thenReturn(CEP);

        Endereco enderecoViaCep = enderecoService.salvarEndereco(CEPSemHifem);

        Assertions.assertNotNull(enderecoViaCep);
        Assertions.assertEquals(Endereco.class, enderecoViaCep.getClass());
        Assertions.assertEquals(CEP, enderecoViaCep.getCep());
        Assertions.assertEquals(LOGRADOURO, enderecoViaCep.getLogradouro());
        Assertions.assertEquals(BAIRRO, enderecoViaCep.getBairro());
        Assertions.assertEquals(CIDADE, enderecoViaCep.getLocalidade());
        Assertions.assertEquals(UF, enderecoViaCep.getUf());
    }


    @Test
    void deveDeletarUmEnderecoEmDeletarEndereco() {
        Mockito.when(enderecoRepository.findById(Mockito.anyString())).thenReturn(enderecoOptional);
        Mockito.when(enderecoService.buscarEnderecoPorCep(Mockito.any())).thenReturn(enderecoDTO);
        Mockito.doNothing().when(enderecoRepository).deleteById(Mockito.anyString());

        enderecoService.deletarEndereco(CEP);

        Mockito.verify(enderecoRepository, Mockito.times(1)).deleteById(Mockito.anyString());
    }

    @Test
    void deveAtualizarUmEnderecoEmAtualizarEndereco() {
        Mockito.when(enderecoRepository.findById(Mockito.anyString())).thenReturn(enderecoOptional);
        Mockito.when(enderecoService.buscarEnderecoPorCep(Mockito.any())).thenReturn(enderecoDTO);
        Mockito.when(enderecoRepository.save(Mockito.any())).thenReturn(endereco);

        Endereco enderecoViaCep = enderecoService.atualizarEndereco(CEP, enderecoDTO);

        Assertions.assertNotNull(enderecoViaCep);
        Assertions.assertEquals(Endereco.class, enderecoViaCep.getClass());
        Assertions.assertEquals(LOGRADOURO, enderecoViaCep.getLogradouro());
        Assertions.assertEquals(BAIRRO, enderecoViaCep.getBairro());
        Assertions.assertEquals(CIDADE, enderecoViaCep.getLocalidade());
        Assertions.assertEquals(UF, enderecoViaCep.getUf());
    }

}
