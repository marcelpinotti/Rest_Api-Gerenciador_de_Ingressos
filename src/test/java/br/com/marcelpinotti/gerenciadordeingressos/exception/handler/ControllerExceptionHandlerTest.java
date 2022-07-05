package br.com.marcelpinotti.gerenciadordeingressos.exception.handler;

import br.com.marcelpinotti.gerenciadordeingressos.exception.ObjectExistsException;
import br.com.marcelpinotti.gerenciadordeingressos.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

@SpringBootTest
class ControllerExceptionHandlerTest {

    @InjectMocks
    private ControllerExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarAExcecaoObjectNotFoundExceptionAoNaoEncontrarUmEnderecoPorCepNoViaCep() {

        ResponseEntity<StandardError> response = exceptionHandler.
                objectNotFound(
                        new ObjectNotFoundException("Endereço não encontrado"),
                        new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals("Endereço não encontrado", response.getBody().getMessage());
        Assertions.assertEquals(404, response.getStatusCodeValue());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(StandardError.class, response.getBody().getClass());
        Assertions.assertEquals("Não encontrado", response.getBody().getError());
    }

    @Test
    void deveRetornarAExcecaoObjectExistsExceptionAoEncontrarUmEnderecoPorCepNoBancoDeDados() {

        ResponseEntity<StandardError> response = exceptionHandler.
                objectExists(
                        new ObjectExistsException("O Endereço já existe na base de dados"),
                        new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Assertions.assertEquals("O Endereço já existe na base de dados", response.getBody().getMessage());
        Assertions.assertEquals(409, response.getStatusCodeValue());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(StandardError.class, response.getBody().getClass());
        Assertions.assertEquals("Já existe", response.getBody().getError());
    }
}