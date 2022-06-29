package br.com.marcelpinotti.gerenciadordeingressos.entities;

import br.com.marcelpinotti.gerenciadordeingressos.enums.TipoDoEvento;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="tb_eventos")
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_evento")
    private Long id;

    @NotEmpty(message = "O nome não pode estar vazio.")
    @Column(name="nome_do_evento")
    private String nome;

    @Min(value = 0, message = "A capacidade deve ser positiva.")
    @Column(name="capacidade")
    private Integer capacidade;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name="data_do_evento")
    private Date dataDoEvento;

    @DecimalMin(value = "1.00")
    @Digits(integer= 10, fraction=2)
    @Column(name = "valor_do_ingresso")
    private BigDecimal valorDoIngresso;

    @ManyToOne
    @JoinColumn(name = "local_do_evento")
    private LocalDeEvento localDeEvento;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_do_evento")
    private TipoDoEvento tipoDoEvento;

    public Evento() {
    }

    public Evento(Long id, String nome, Integer capacidade, Date dataDoEvento,
                  BigDecimal valorDoIngresso, LocalDeEvento localDeEvento, TipoDoEvento tipoDoEvento) {
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
        this.dataDoEvento = dataDoEvento;
        this.valorDoIngresso = valorDoIngresso;
        this.localDeEvento = localDeEvento;
        this.tipoDoEvento = tipoDoEvento;
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

    public Date getDataDoEvento() {
        return dataDoEvento;
    }

    public void setDataDoEvento(Date dataDoEvento) {
        this.dataDoEvento = dataDoEvento;
    }

    public BigDecimal getValorDoIngresso() {
        return valorDoIngresso;
    }

    public void setValorDoIngresso(BigDecimal valorDoIngresso) {
        this.valorDoIngresso = valorDoIngresso;
    }

    public LocalDeEvento getLocalDeEvento() { return localDeEvento; }

    public void setLocalDeEvento(LocalDeEvento localDeEvento) { this.localDeEvento = localDeEvento; }

    public TipoDoEvento getTipoDoEvento() { return tipoDoEvento; }

    public void setTipoDoEvento(TipoDoEvento tipoDoEvento) {
        this.tipoDoEvento = tipoDoEvento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return id.equals(evento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
