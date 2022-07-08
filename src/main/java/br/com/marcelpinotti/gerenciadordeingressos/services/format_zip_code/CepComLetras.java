package br.com.marcelpinotti.gerenciadordeingressos.services.format_zip_code;

import org.springframework.stereotype.Component;

@Component
public class CepComLetras extends FormatarCep {

    public CepComLetras(){
    }

    public CepComLetras(FormatarCep proximo) {
        super(proximo);
    }

    @Override
    public String formatar(String cep) {

        if(!cep.matches("\\d{5}-\\d{3}")){
            String nova = cep.replaceAll(cep,"00000-000");
            return nova;
        }
        return proximo.formatar(cep);
    }
}
