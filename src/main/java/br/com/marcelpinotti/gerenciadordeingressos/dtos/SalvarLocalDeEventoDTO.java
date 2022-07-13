package br.com.marcelpinotti.gerenciadordeingressos.dtos;

import java.io.Serializable;

public class SalvarLocalDeEventoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String endereco;

    public SalvarLocalDeEventoDTO() {
    }

    public SalvarLocalDeEventoDTO(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
