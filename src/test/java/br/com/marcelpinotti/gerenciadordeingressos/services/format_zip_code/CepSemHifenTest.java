package br.com.marcelpinotti.gerenciadordeingressos.services.format_zip_code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CepSemHifenTest {

    @InjectMocks
    private CepSemHifen cepSemHifen;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarOCepComHifenQuandoOCepDigitadoContiverOitoDigitosMasNaoContiverOHifen() {
        String CepSemHifen = "03201000";

        String cepVerificado = cepSemHifen.formatar(CepSemHifen);

        Assertions.assertNotNull(cepVerificado);
        Assertions.assertEquals("03201-000", cepVerificado);
    }
}
