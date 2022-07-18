package br.com.marcelpinotti.gerenciadordeingressos.services;

import br.com.marcelpinotti.gerenciadordeingressos.dtos.EnderecoDTO;
import br.com.marcelpinotti.gerenciadordeingressos.dtos.LocalDeEventoDTO;
import br.com.marcelpinotti.gerenciadordeingressos.dtos.SalvarLocalDeEventoDTO;
import br.com.marcelpinotti.gerenciadordeingressos.entities.Endereco;
import br.com.marcelpinotti.gerenciadordeingressos.entities.LocalDeEvento;
import br.com.marcelpinotti.gerenciadordeingressos.repositories.LocalDeEventoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;


class LocalDeEventoServiceTest {

    private static final String CEP = "03201-000";
    private static final String CEPATUALIZADO = "12345-678";
    private static final String LOGRADOURO = "Rua das Cobéias";
    private static final String BAIRRO = "Vila Bela";
    private static final String CIDADE = "São Paulo";
    private static final String UF = "SP";
    private static final Long ID = 1L;
    private static final String NOME = "Local de Evento";
    private static final String NOMEATUALIZADO = "Local de Evento 2";
    private static final Integer CAPACIDADE = 1000;
    private static final Integer CAPACIDADEATUALIZADA = 900;
    private LocalDeEvento localDeEvento;
    private LocalDeEvento localDeEventoAtualizado;
    private LocalDeEventoDTO localDeEventoDTO;
    private SalvarLocalDeEventoDTO salvarLocalDeEventoDTO;
    private SalvarLocalDeEventoDTO salvarLocalDeEventoAtualizado;

    private Optional<LocalDeEvento> optionalLocalDeEvento;
    private Endereco endereco;
    private Endereco enderecoAtualizado;
    private EnderecoDTO enderecoDTO;

    @InjectMocks
    private LocalDeEventoService localService;

    @Mock
    private LocalDeEventoRepository localRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private EnderecoService enderecoService;

    void inicializandoVariaveis(){
        salvarLocalDeEventoDTO = new SalvarLocalDeEventoDTO(NOME, CAPACIDADE, CEP);
        salvarLocalDeEventoAtualizado = new SalvarLocalDeEventoDTO(NOMEATUALIZADO, CAPACIDADEATUALIZADA, CEPATUALIZADO);
        enderecoAtualizado = new Endereco(CEPATUALIZADO, LOGRADOURO, BAIRRO, CIDADE,UF);
        endereco = new Endereco(CEP, LOGRADOURO, BAIRRO, CIDADE, UF);
        enderecoDTO = new EnderecoDTO(CEP, LOGRADOURO, BAIRRO, CIDADE, UF);
        localDeEvento = new LocalDeEvento(ID, NOME,CAPACIDADE, endereco);
        localDeEventoDTO = new LocalDeEventoDTO(ID, NOME, CAPACIDADE, enderecoDTO);
        optionalLocalDeEvento = Optional.of(new LocalDeEvento(ID, NOME,CAPACIDADE,endereco));
        localDeEventoAtualizado = new LocalDeEvento(ID, NOMEATUALIZADO, CAPACIDADEATUALIZADA, enderecoAtualizado);
    }

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        inicializandoVariaveis();
    }

    @Test
    void deveRetornarUmaListaDeLocaisDeEventosQuandoListarTodosOsLocaisDeEventos() {
        Mockito.when(localRepository.findAll()).thenReturn(List.of(localDeEvento));
        Mockito.when(modelMapper.map(Mockito.any(),Mockito.any())).thenReturn(localDeEventoDTO);

        List<LocalDeEventoDTO> lista = localService.listarTodosOsLocaisDeEventos();

        Assertions.assertNotNull(lista);
        Assertions.assertEquals(LocalDeEventoDTO.class, lista.get(0).getClass());
        Assertions.assertEquals(ID, lista.get(0).getId());
        Assertions.assertEquals(NOME, lista.get(0).getNome());
        Assertions.assertEquals(CAPACIDADE, lista.get(0).getCapacidade());
        Assertions.assertEquals(EnderecoDTO.class, lista.get(0).getEndereco().getClass());
        Assertions.assertEquals(CEP, lista.get(0).getEndereco().getCep());
        Assertions.assertEquals(1, lista.size());
    }

    @Test
    void DeveRetornarUmLocalDeEventoQuandoBuscarLocalDeEventoPorId() {
        Mockito.when(localRepository.findById(Mockito.anyLong())).thenReturn(optionalLocalDeEvento);
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(localDeEventoDTO);

        LocalDeEventoDTO buscarLocalDeEventoPorId = localService.buscarLocalDeEventoPorId(ID);

        Assertions.assertNotNull(buscarLocalDeEventoPorId);
        Assertions.assertEquals(LocalDeEventoDTO.class, buscarLocalDeEventoPorId.getClass());
        Assertions.assertEquals(ID, buscarLocalDeEventoPorId.getId());
        Assertions.assertEquals(NOME, buscarLocalDeEventoPorId.getNome());
        Assertions.assertEquals(CAPACIDADE, buscarLocalDeEventoPorId.getCapacidade());
        Assertions.assertEquals(CEP, buscarLocalDeEventoPorId.getEndereco().getCep());
    }
    @Test
    void DeveSalvarUmLocalDeEventoQuandoSalvarLocalDeEvento() {
        Mockito.when(localRepository.save(Mockito.any())).thenReturn(localDeEvento);
        Mockito.when(enderecoService.salvarEnderecoPeloLocalDeEvento(Mockito.anyString())).thenReturn(enderecoDTO);
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(localDeEvento);

        LocalDeEvento localDeEventoSalvo = localService.salvarLocalDeEvento(salvarLocalDeEventoDTO);

        Assertions.assertNotNull(localDeEventoSalvo);
        Assertions.assertEquals(LocalDeEvento.class, localDeEventoSalvo.getClass());
        Assertions.assertEquals(ID, localDeEventoSalvo.getId());
        Assertions.assertEquals(NOME, localDeEventoSalvo.getNome());
        Assertions.assertEquals(CAPACIDADE, localDeEventoSalvo.getCapacidade());
        Assertions.assertEquals(CEP, localDeEventoSalvo.getEndereco().getCep());
    }

    @Test
    void deveDeletarLocalDeEventoQuandoOIdForValido() {
        Mockito.when(localRepository.findById(Mockito.anyLong())).thenReturn(optionalLocalDeEvento);
        Mockito.when(localService.buscarLocalDeEventoPorId(Mockito.anyLong())).thenReturn(localDeEventoDTO);
        Mockito.doNothing().when(localRepository).deleteById(Mockito.anyLong());

        localService.deletarLocalDeEvento(ID);

        Mockito.verify(localRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    void DeveAtualizarUmLocalDeEventoAtualQuandoAtualizarLocalDeEvento() {
        Mockito.when(localRepository.findById(Mockito.anyLong())).thenReturn(optionalLocalDeEvento);
        Mockito.when(modelMapper.map(Mockito.any(),Mockito.any())).thenReturn(enderecoAtualizado);
        Mockito.when(enderecoService.salvarEnderecoPeloLocalDeEvento(Mockito.anyString())).thenReturn(enderecoDTO);
        Mockito.when(localRepository.save(Mockito.any())).thenReturn(localDeEventoAtualizado);

        LocalDeEvento localDeEventoAtualizado = localService.atualizarLocalDeEvento(ID, salvarLocalDeEventoAtualizado);

        Assertions.assertNotNull(localDeEventoAtualizado);
        Assertions.assertEquals(LocalDeEvento.class, localDeEventoAtualizado.getClass());
        Assertions.assertEquals(ID, localDeEventoAtualizado.getId());
        Assertions.assertEquals("Local de Evento 2", localDeEventoAtualizado.getNome());
        Assertions.assertEquals(900, localDeEventoAtualizado.getCapacidade());
        Assertions.assertEquals("12345-678", localDeEventoAtualizado.getEndereco().getCep());
    }
}