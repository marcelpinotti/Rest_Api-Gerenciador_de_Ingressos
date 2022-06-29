package br.com.marcelpinotti.gerenciadordeingressos.dtos;

import br.com.marcelpinotti.gerenciadordeingressos.enums.TipoDoEvento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EventoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Integer capacidade;
    private Date dataDoEvento;
    private BigDecimal valorDoIngresso;
    private LocalDeEventoDTO localDeEvento;
    private TipoDoEvento tipoDoEvento;

    public EventoDTO() {
    }

    public EventoDTO(Long id, String nome, Integer capacidade, Date dataDoEvento,
                  BigDecimal valorDoIngresso, LocalDeEventoDTO localDeEvento, TipoDoEvento tipoDoEvento) {
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

    public LocalDeEventoDTO getLocalDeEvento() { return localDeEvento; }

    public void setLocalDeEvento(LocalDeEventoDTO localDeEvento) { this.localDeEvento = localDeEvento; }

    public TipoDoEvento getTipoDoEvento() { return tipoDoEvento; }

    public void setTipoDoEvento(TipoDoEvento tipoDoEvento) {
        this.tipoDoEvento = tipoDoEvento;
    }
}
