package br.com.marcelpinotti.gerenciadordeingressos.services.format_zip_code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CepFormatadoTest {

    @InjectMocks
    private CepFormatado cepFormatado;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarOCepComHifen(){
        String cep = "03201-000";

        String consultaCep = cepFormatado.formatar(cep);

        Assertions.assertEquals("03201-000", consultaCep);

    }
}
