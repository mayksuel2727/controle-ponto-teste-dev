package com.mayksuel2727.controleponto.BO;

import com.mayksuel2727.controleponto.HBDAO.ControlePontoHBDAO;
import com.mayksuel2727.controleponto.model.HorarioTrabalho;

import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class ControlePontoBO implements Serializable{
    private static final long serialVersionUID = 1L;


    public void salvar(HorarioTrabalho horarioTrabalho){
        ControlePontoHBDAO controlePonto = new ControlePontoHBDAO();
        controlePonto.adiciona(horarioTrabalho);
    }
}
