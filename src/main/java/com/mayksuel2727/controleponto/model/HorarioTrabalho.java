package com.mayksuel2727.controleponto.model;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class HorarioTrabalho implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idTurno;
    @Size(max = 5)
    private String entrada;

    @Size(max = 5)
    private String saida;


    public Long getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Long idTurno) {
        this.idTurno = idTurno;
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
