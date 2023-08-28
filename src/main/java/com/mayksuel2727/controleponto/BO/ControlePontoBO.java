package com.mayksuel2727.controleponto.BO;

import com.mayksuel2727.controleponto.HBDAO.ControlePontoHBDAO;
import com.mayksuel2727.controleponto.model.Marcacao;

import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class ControlePontoBO implements Serializable{
    private static final long serialVersionUID = 1L;
    ControlePontoHBDAO controlePontoHBDAO = new ControlePontoHBDAO();

    public void salvarHorarioTrabalho(Marcacao horarioTrabalho){
        controlePontoHBDAO.adicionaHorarioTrabalho(horarioTrabalho);
    }

    public void salvarMarcacaoFeita(Marcacao marcacaoFeita){
        controlePontoHBDAO.adicionaHorarioMarcacao(marcacaoFeita);
    }



    public void limparDados() {
        controlePontoHBDAO.limparDados();
    }

    public void limparDadosMarcacoes() {
        controlePontoHBDAO.limparDadosMarcacoes();
    }
}
