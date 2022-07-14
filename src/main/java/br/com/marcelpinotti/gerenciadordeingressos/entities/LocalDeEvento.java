package br.com.marcelpinotti.gerenciadordeingressos.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="tb_locais_de_eventos")
public class LocalDeEvento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_local")
    private Long id;

    @NotEmpty(message = "O nome não pode estar vazio.")
    @Column(name = "nome_do_local")
    private String nome;

    @NotEmpty(message = "Informe a capacidade máxima do local de evento")
    @Column(name = "capacidade")
    private Integer capacidade;

    @Valid
    @ManyToOne
    @JoinColumn(name = "endereco_cep")
    private Endereco endereco;

    public LocalDeEvento() {
    }

    public LocalDeEvento(Long id, String nome, Integer capacidade, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
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

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalDeEvento that = (LocalDeEvento) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
