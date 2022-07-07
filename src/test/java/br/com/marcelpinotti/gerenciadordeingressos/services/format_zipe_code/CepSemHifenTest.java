package br.com.marcelpinotti.gerenciadordeingressos.services.format_zipe_code;

import br.com.marcelpinotti.gerenciadordeingressos.services.format_zip_code.CepSemHifen;
import br.com.marcelpinotti.gerenciadordeingressos.services.format_zip_code.CepFormatado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CepSemHifenTest {

    @InjectMocks
    private CepSemHifen cepSemHifen;

    @Mock
    private CepFormatado cepFormatado;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarOCepComHifenOndeOCepDeEntradaSeraSemHifen(){
        String CepSemHifen = "03201000";

        String cepFormatado = cepSemHifen.formatar(CepSemHifen);

        Assertions.assertEquals("03201-000", cepFormatado);

    }

    @Test
    void deveRetornarOCepComHifenOndeOCepDeEntradaTeraHifenEOitoNumeros(){
        Mockito.when(cepFormatado.formatar(Mockito.anyString())).thenReturn("03201-000");

        String CepComHifen = "03201-000";

        String cepFormatado = cepSemHifen.formatar(CepComHifen);

        Assertions.assertEquals("03201-000", cepFormatado);
    }

    @Test
    void deveRetornarOCepComHifenOndeOCepDeEntradaSeraTeraHi(){
        Mockito.when(cepFormatado.formatar(Mockito.anyString())).thenReturn("00000-000");

        String CepComLetras = "abcde-230";

        String cepFormatado = cepSemHifen.formatar(CepComLetras);

        Assertions.assertEquals("00000-000", cepFormatado);

    }

}
