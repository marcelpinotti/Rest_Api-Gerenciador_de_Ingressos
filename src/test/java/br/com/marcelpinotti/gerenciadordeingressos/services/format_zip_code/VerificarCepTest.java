package br.com.marcelpinotti.gerenciadordeingressos.services.format_zip_code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class VerificarCepTest {

    @InjectMocks
    private VerificarCep verificarCep;

    @Mock
    private CepSemHifen cepSemHifen;

    @Mock
    private CepComLetras cepComLetras;

    @Mock
    private CepFormatado cepFormatado;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarOCepComHifenQuandoOCepDigitadoContiverOitoDigitosMasNaoContiverOHifen() {
        Mockito.when(cepSemHifen.formatar(Mockito.anyString())).thenReturn("11111-111");

        String cepVerificado = verificarCep.vericiacaoDeCep("11111111");

        Assertions.assertNotNull(cepVerificado);
        Assertions.assertEquals("11111-111", cepVerificado);
    }

    @Test
    void deveRetornarUmaSequenciaDeZerosComHifenQuandoOCepDigitadoContiverLetrasOuCaracteresEspeciais() {
        Mockito.when(cepComLetras.formatar(Mockito.anyString())).thenReturn("00000-000");

        String cepVerificado = verificarCep.vericiacaoDeCep("sdfrtghytjhb");

        Assertions.assertNotNull(cepVerificado);
        Assertions.assertEquals("00000-000", cepVerificado);
    }

    @Test
    void deveRetornarOCepComHifenQuandoOCepDigitadoForVálido() {
        Mockito.when(cepFormatado.formatar(Mockito.anyString())).thenReturn("11111-111");

        String cepVerificado = verificarCep.vericiacaoDeCep("11111-111");

        Assertions.assertNotNull(cepVerificado);
        Assertions.assertEquals("11111-111", cepVerificado);
    }
}