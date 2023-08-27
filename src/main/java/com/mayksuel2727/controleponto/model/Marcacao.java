package com.mayksuel2727.controleponto.model;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class Marcacao implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @Size(max = 5)
    private String entrada;

    @Size(max = 5)
    private String saida;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

}
