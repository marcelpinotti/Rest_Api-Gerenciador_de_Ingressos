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
    private static final String LOGRADOURO = "Rua das Cobéias";
    private static final String BAIRRO = "Vila Bela";
    private static final String CIDADE = "São Paulo";
    private static final String UF = "SP";
    private static final Long ID = 1L;
    private static final String NOME = "Local de Evento";
    private static final Integer CAPACIDADE = 1000;
    private LocalDeEvento localDeEvento;
    private LocalDeEventoDTO localDeEventoDTO;
    private SalvarLocalDeEventoDTO salvarLocalDeEventoDTO;
    private Endereco endereco;
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
        endereco = new Endereco(CEP, LOGRADOURO, BAIRRO, CIDADE, UF);
        enderecoDTO = new EnderecoDTO(CEP, LOGRADOURO, BAIRRO, CIDADE, UF);
        localDeEvento = new LocalDeEvento(ID, NOME,CAPACIDADE, endereco);
        localDeEventoDTO = new LocalDeEventoDTO(ID, NOME, CAPACIDADE, enderecoDTO);
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
        Assertions.assertEquals(enderecoDTO.getCep(), lista.get(0).getEndereco().getCep());
        Assertions.assertEquals(1, lista.size());

    }

    @Test
    void buscarLocalDeEventoPorId() {
    }

    @Test
    void salvarLocalDeEvento() {
    }

    @Test
    void deletarLocalDeEvento() {
    }

    @Test
    void atualizarLocalDeEvento() {
    }
}