package br.com.marcelpinotti.gerenciadordeingressos.services.format_zip_code;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class VerificarCep {

    public String vericiacaoDeCep(String cep) {

        FormatarCep verificarCep = new CepSemHifen(
                                        new CepComLetras(
                                                new CepFormatado()));

        return verificarCep.formatar(cep);
    }
}
