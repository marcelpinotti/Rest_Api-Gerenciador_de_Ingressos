package br.com.marcelpinotti.gerenciadordeingressos.services.format_zip_code;

public class CepFormatado extends FormatarCep {

    public CepFormatado() {
        super(null);
    }

    @Override
    public String formatar(String cep) {
        return cep;
    }
}
