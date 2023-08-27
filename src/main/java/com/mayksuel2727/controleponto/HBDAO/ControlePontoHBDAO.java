package com.mayksuel2727.controleponto.HBDAO;


import com.mayksuel2727.controleponto.model.HorarioTrabalho;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ControlePontoHBDAO implements Serializable{
    private static final long serialVersionUID = 1L;

    private static Long chaveSequencial = 1L;

    private static List<HorarioTrabalho> listaHorarioTrabalhos = new ArrayList<>();


    public void adiciona(HorarioTrabalho horarioTrabalho) {
//        if(horarioTrabalho.getIdTurno()!=null){
            horarioTrabalho.setIdTurno(chaveSequencial++);
            ControlePontoHBDAO.listaHorarioTrabalhos.add(horarioTrabalho);
//        }else {
//			for (HorarioTrabalho horarioTrabalhoLista : ControlePontoHBDAO.listaHorarioTrabalhos){
//                if (horarioTrabalhoLista.getIdTurno()==horarioTrabalho.getIdTurno()){
//                    horarioTrabalhoLista.setEntrada(horarioTrabalho.getEntrada());
//                    horarioTrabalhoLista.setSaida(horarioTrabalho.getSaida());
//                }
//            }
//        }

    }


    public List<HorarioTrabalho> getListaHorarioTrablho() {
        return ControlePontoHBDAO.listaHorarioTrabalhos;
    }
}
