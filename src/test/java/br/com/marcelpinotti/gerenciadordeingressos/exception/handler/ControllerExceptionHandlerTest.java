package br.com.marcelpinotti.gerenciadordeingressos.exception.handler;

import br.com.marcelpinotti.gerenciadordeingressos.entities.Endereco;
import br.com.marcelpinotti.gerenciadordeingressos.exception.ErrorObject;
import br.com.marcelpinotti.gerenciadordeingressos.exception.ObjectExistsException;
import br.com.marcelpinotti.gerenciadordeingressos.exception.ObjectNotFoundException;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.validation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
class ControllerExceptionHandlerTest {

    @InjectMocks
    private ControllerExceptionHandler exceptionHandler;

    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void deveRetornarAExcecaoObjectNotFoundExceptionQuandoNaoEncontrarUmEnderecoPorCepNoViaCep() {

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
    void deveRetornarAExcecaoObjectExistsExceptionQuandoEncontrarUmEnderecoPorCepNoBancoDeDados() {

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

    @Test
    void deveRetornarAExcecaoFeignExceptionQuandoEncontrarAlgumErroNoCepDigtadoNaConsultaDoViaCep() {

        ResponseEntity<StandardError> response = exceptionHandler.
                badRequest(
                        new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Possivelmente o CEP digitado é inválido, o mesmo só aceita números. Exemplos, 12345678 ou 12345-678", response.getBody().getMessage());
        Assertions.assertEquals(400, response.getStatusCodeValue());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(StandardError.class, response.getBody().getClass());
        Assertions.assertEquals("Não encontrado", response.getBody().getError());
    }

    @Test
    void deveRetornarAExcecaoConstraintViolationExceptionQuandoAlgumCampoNãoForDigitadoNaAtualazacaoDoCep() {

        Endereco endereco = new Endereco();
        Set<ConstraintViolation<Endereco>> violations = validator.validate(endereco);

        ResponseEntity<StandardErrorValidation> response = exceptionHandler.
                notValid(new ConstraintViolationException(violations));

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(StandardErrorValidation.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Campos inválidos", response.getBody().getMessage());
        Assertions.assertEquals(400, response.getStatusCodeValue());
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertFalse(response.getBody().getErrors().isEmpty());
        Assertions.assertEquals(5, response.getBody().getErrors().size());
    }

    @Test
    void deveRetornarAExcecaoHttpRequestMethodNotSupportedExceptionQuandoOCepNaoForDigitadoNoPut() {

        ResponseEntity<StandardError> response = exceptionHandler
                .badRequestPath(new HttpRequestMethodNotSupportedException("PUT"),
                                new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(StandardError.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        Assertions.assertEquals("O erro está na URI, o CEP não pode ser nulo", response.getBody().getError());
        Assertions.assertEquals(405, response.getStatusCodeValue());
        Assertions.assertEquals("Request method 'PUT' not supported", response.getBody().getMessage());
    }

    @Test
    void deveRetornarAExcecaoHttpRequestMethodNotSupportedExceptionQuandoOCepNaoForDigitadoNoDelete() {

        ResponseEntity<StandardError> response = exceptionHandler
                .badRequestPath(new HttpRequestMethodNotSupportedException("DELETE"),
                                new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(StandardError.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        Assertions.assertEquals("O erro está na URI, o CEP não pode ser nulo", response.getBody().getError());
        Assertions.assertEquals(405, response.getStatusCodeValue());
        Assertions.assertEquals("Request method 'DELETE' not supported", response.getBody().getMessage());
    }

    @Test
    void deveRetornarAExcecaoHttpRequestMethodNotSupportedExceptionQuandoOCepNaoForDigitadoNoFindAll() {

        ResponseEntity<StandardError> response = exceptionHandler
                .badRequestPath(new HttpRequestMethodNotSupportedException("GET"),
                        new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(StandardError.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        Assertions.assertEquals("O erro está na URI, o CEP não pode ser nulo", response.getBody().getError());
        Assertions.assertEquals(405, response.getStatusCodeValue());
        Assertions.assertEquals("Request method 'GET' not supported", response.getBody().getMessage());
    }

}