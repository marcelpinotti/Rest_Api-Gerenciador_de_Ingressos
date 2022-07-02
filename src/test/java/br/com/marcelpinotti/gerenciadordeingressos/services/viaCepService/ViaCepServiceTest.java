package br.com.marcelpinotti.gerenciadordeingressos.services.viaCepService;

import br.com.marcelpinotti.gerenciadordeingressos.entities.Endereco;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class ViaCepServiceTest {

    private static String BASE_URL = "https://viacep.com.br/ws";
    private static String VIA_CEP_RESPONSE = "{\n" +
            "  \"cep\": \"01001-000\",\n" +
            "  \"logradouro\": \"Praça da Sé\",\n" +
            "  \"complemento\": \"lado ímpar\",\n" +
            "  \"bairro\": \"Sé\",\n" +
            "  \"localidade\": \"São Paulo\",\n" +
            "  \"uf\": \"SP\",\n" +
            "  \"ibge\": \"3550308\",\n" +
            "  \"gia\": \"1004\",\n" +
            "  \"ddd\": \"11\",\n" +
            "  \"siafi\": \"7107\"\n" +
            "}";
    private static String CEP = "01001-000";

    private ViaCepService viaCepService;

    /*
     * Mock pré-carregado como conteúdo da resposta é necessário fazer a invocação simulada.
     * A invocação pré-simulada ira atingir as anotações e garantir que tudo estará funcionando.
     */

    public void builderFeignClient(MockClient mockClient) {

        viaCepService = Feign.builder()
                .client(mockClient)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new SpringMvcContract())
                .target(ViaCepService.class, BASE_URL);
    }

    /*
     * Criar o teste com base no builderFeingClient
     */
    @Test
    void deveRetornarUmaRespostaOKQuandoChamarOViaCepService() {
        this.builderFeignClient(new MockClient().ok(
                HttpMethod.GET,
                BASE_URL.concat("/01001-000/json/"),
                VIA_CEP_RESPONSE
                )
        );

        Endereco endereco = viaCepService.consultarCep(CEP);

        Assertions.assertFalse(endereco.getCep().isEmpty());
        Assertions.assertEquals("01001-000", endereco.getCep());
        Assertions.assertEquals("Praça da Sé", endereco.getLogradouro());
        Assertions.assertEquals("Sé", endereco.getBairro());
        Assertions.assertEquals("São Paulo", endereco.getLocalidade());
        Assertions.assertEquals("SP", endereco.getUf());

    }
}