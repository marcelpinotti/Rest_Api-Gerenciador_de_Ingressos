package br.com.marcelpinotti.gerenciadordeingressos.dtos;

import java.io.Serializable;

public class SalvarLocalDeEventoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;

    private Integer capacidade;
    private String endereco;

    public SalvarLocalDeEventoDTO() {
    }

    public SalvarLocalDeEventoDTO(String nome, Integer capacidade, String endereco) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
