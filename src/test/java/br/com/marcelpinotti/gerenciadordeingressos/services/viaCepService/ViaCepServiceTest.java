package br.com.marcelpinotti.gerenciadordeingressos.services.viaCepService;


import br.com.marcelpinotti.gerenciadordeingressos.entities.Endereco;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

@SpringBootTest
public class ViaCepServiceTest {

    private static String BASE_URL = "https://viacep.com.br/ws";

    //Criação de uma String para simular o corpo da resposta.
    private static String VIACEP_RESPONSE = "{\n" +
            "      \"cep\": \"01001-000\",\n" +
            "      \"logradouro\": \"Praça da Sé\",\n" +
            "      \"complemento\": \"lado ímpar\",\n" +
            "      \"bairro\": \"Sé\",\n" +
            "      \"localidade\": \"São Paulo\",\n" +
            "      \"uf\": \"SP\",\n" +
            "      \"ibge\": \"3550308\",\n" +
            "      \"gia\": \"1004\",\n" +
            "      \"ddd\": \"11\",\n" +
            "      \"siafi\": \"7107\"\n" +
            "    }";

    private ViaCepService viaCepService;


    // Criar uma invocação simulado para ter um Mock pré-carregado como conteúdo da resposta.
    // Essa invocação vai atingir as anotações e garantir que tudo estará funcionando.
    private void buildFeignClient(MockClient mockClient) {

        viaCepService = Feign.builder()
                .client(mockClient)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new SpringMvcContract())
                .target(ViaCepService.class, BASE_URL);
    }

    @Test
    void deveRetornarUmaRespostaOkQuandoChamarOViaCepService() {

        this.buildFeignClient(new MockClient().ok(
                HttpMethod.GET,
                BASE_URL.concat("/01001-000/json/"),
                VIACEP_RESPONSE
        ));

        Endereco endereco = viaCepService.consultarCep("01001-000");

        Assertions.assertFalse(endereco.getCep().isEmpty());
        Assertions.assertEquals("01001-000", endereco.getCep());
        Assertions.assertEquals("Praça da Sé", endereco.getLogradouro());
        Assertions.assertEquals("Sé", endereco.getBairro());
        Assertions.assertEquals("São Paulo", endereco.getLocalidade());
        Assertions.assertEquals("SP", endereco.getUf());

    }


}
