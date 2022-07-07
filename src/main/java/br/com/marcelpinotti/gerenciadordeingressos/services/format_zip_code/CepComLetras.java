package br.com.marcelpinotti.gerenciadordeingressos.services.format_zip_code;

public class CepComLetras extends FormatarCep{

    public CepComLetras(FormatarCep proximo) {
        super(proximo);
    }

    @Override
    public String formatar(String cep) {

        if(!cep.matches("\\d")){
            String nova = cep.replaceAll(cep,"00000-000");
            return nova;
        }
        return proximo.formatar(cep);
    }
}
