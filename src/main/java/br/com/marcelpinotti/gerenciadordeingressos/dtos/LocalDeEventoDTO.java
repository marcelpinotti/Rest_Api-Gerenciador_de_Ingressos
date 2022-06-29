package br.com.marcelpinotti.gerenciadordeingressos.dtos;

import java.io.Serializable;

public class LocalDeEventoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private EnderecoDTO endereco;

    public LocalDeEventoDTO() {
    }

    public LocalDeEventoDTO(Long id, String nome, EnderecoDTO endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
