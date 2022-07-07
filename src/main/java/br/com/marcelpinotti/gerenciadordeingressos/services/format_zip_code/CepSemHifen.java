package br.com.marcelpinotti.gerenciadordeingressos.services.format_zip_code;

public class CepSemHifen extends FormatarCep {

    public CepSemHifen(FormatarCep proximo) {
        super(proximo);
    }

    @Override
    public String formatar(String cep) {

        String validar = cep.substring(5,6);

        if(!validar.equals("-") && cep.matches("\\d{8}")) {
            cep = cep.substring(0, 5) + '-' + cep.substring(5);
            return cep;
        }

        return proximo.formatar(cep);
    }
}
