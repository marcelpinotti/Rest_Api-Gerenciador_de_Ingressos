package br.com.marcelpinotti.gerenciadordeingressos.services.format_zip_code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CepComLetrasTest {

    @InjectMocks
    private CepComLetras cepComLetras;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarOCepComHifenEOitoZerosQuandoOCepContiverNumerosECaracteresEspeciais(){
        String CepComLetras = "abcde-230";

        String cepFormatado = cepComLetras.formatar(CepComLetras);

        Assertions.assertEquals("00000-000", cepFormatado);

    }
}
