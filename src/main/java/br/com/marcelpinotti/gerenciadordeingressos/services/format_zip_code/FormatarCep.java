package br.com.marcelpinotti.gerenciadordeingressos.services.format_zip_code;

public abstract class FormatarCep {

    protected FormatarCep proximo;

    public FormatarCep(FormatarCep proximo) {
        this.proximo = proximo;
    }

    public abstract String formatar(String cep);
}
