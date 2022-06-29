package br.com.marcelpinotti.gerenciadordeingressos.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_enderecos")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "cep")
    @NotNull(message = "O CEP deve seguir esse formato, ex. 12345-678.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve seguir esse formato, ex. 12345-678.")
    private String cep;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String localidade;

    @Column(name = "estado",length = 20)
    private String uf;

    public Endereco() {
    }

    public Endereco(String cep, String logradouro, String bairro, String localidade, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return cep.equals(endereco.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep);
    }
}
