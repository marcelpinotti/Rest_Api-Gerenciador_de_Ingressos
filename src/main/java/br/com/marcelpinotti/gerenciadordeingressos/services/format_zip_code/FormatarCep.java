package br.com.marcelpinotti.gerenciadordeingressos.services.format_zip_code;

import org.springframework.stereotype.Component;

@Component
public abstract class FormatarCep {

    protected FormatarCep proximo;

    public FormatarCep(){
    }

    public FormatarCep(FormatarCep proximo) {
        this.proximo = proximo;
    }

    public abstract String formatar(String cep);
}
