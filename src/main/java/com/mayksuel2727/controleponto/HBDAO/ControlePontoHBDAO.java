package com.mayksuel2727.controleponto.HBDAO;


import com.mayksuel2727.controleponto.model.Marcacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ControlePontoHBDAO implements Serializable{
    private static final long serialVersionUID = 1L;

    private static Long chaveSequencial = 1L;

    private static List<Marcacao> listaHorarioTrabalhos = new ArrayList<>();
    private static List<Marcacao> listaHorarioMarcacaos = new ArrayList<>();
    private static List<Marcacao> listaHorarioAtraso = new ArrayList<>();
    private static List<Marcacao> listaHoraExtra = new ArrayList<>();


    public void adicionaHorarioTrabalho(Marcacao horarioTrabalho) {
//        if(horarioTrabalho.getIdTurno()!=null){
            horarioTrabalho.setId(chaveSequencial++);
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

    public void adicionaHorarioMarcacao(Marcacao horarioMarcacao) {
        horarioMarcacao.setId(chaveSequencial++);
        ControlePontoHBDAO.listaHorarioMarcacaos.add(horarioMarcacao);
    }


    public void adicionaHorarioAtraso(Marcacao horarioAtraso) {
        horarioAtraso.setId(chaveSequencial++);
        ControlePontoHBDAO.listaHorarioAtraso.add(horarioAtraso);
    }

    public void adicionaHoraExtra(Marcacao horaExtra) {
        horaExtra.setId(chaveSequencial++);
        ControlePontoHBDAO.listaHoraExtra.add(horaExtra);
    }


    public List<Marcacao> getListaHorarioTrablho() {
        return ControlePontoHBDAO.listaHorarioTrabalhos;
    }

    public List<Marcacao> getListaHorarioMarcacao() {
        return ControlePontoHBDAO.listaHorarioMarcacaos;
    }

    public List<Marcacao> getListaHorarioAtraso() {
        return ControlePontoHBDAO.listaHorarioAtraso;
    }

    public List<Marcacao> getListaHoraExtra() {
        return ControlePontoHBDAO.listaHoraExtra;
    }

    public void limparDados() {
        ControlePontoHBDAO.listaHorarioTrabalhos.clear();
        ControlePontoHBDAO.listaHorarioMarcacaos.clear();
        ControlePontoHBDAO.listaHorarioAtraso.clear();
        ControlePontoHBDAO.listaHoraExtra.clear();
    }
}
