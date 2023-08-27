package com.mayksuel2727.controleponto.managedbean;

import com.mayksuel2727.controleponto.BO.ControlePontoBO;
import com.mayksuel2727.controleponto.HBDAO.ControlePontoHBDAO;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

import com.mayksuel2727.controleponto.model.HorarioTrabalho;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "ControlePontoMB")
//@Named("ControlePontoMB")
@ViewScoped
public class ControlePontoMB implements Serializable {
    private static final long serialVersionUID = 1L;


    private ControlePontoBO controlePontoBO;

    private ControlePontoHBDAO controlePontoHBDAO;

    private HorarioTrabalho horarioTrabalho;



    @PostConstruct
    private void init() {

		this.controlePontoHBDAO = new ControlePontoHBDAO();
		this.controlePontoBO = new ControlePontoBO();
        this.horarioTrabalho = new HorarioTrabalho();

    }

    public void salvar(){
        if (validar(horarioTrabalho)){
            return;
        }
        if (controlePontoHBDAO.getListaHorarioTrablho().size()>3){
            PrimeFaces.current().executeScript("alert('Número máximo de horários atingido')");
            return;
        }
        controlePontoBO.salvar(horarioTrabalho);
        this.horarioTrabalho = new HorarioTrabalho();
        PrimeFaces.current().ajax().update("form-horario-trabalho");
    }

    public boolean validar(HorarioTrabalho horarioTrabalho){
        if (horarioTrabalho.getEntrada().equals("") || horarioTrabalho.getSaida().equals("")){
            PrimeFaces.current().executeScript("alert('Horário inválido')");
            return true;
        }else if (horarioTrabalho.getEntrada().equals(horarioTrabalho.getSaida())){
            PrimeFaces.current().executeScript("alert('Horário de entrada e saída não podem ser iguais')");
            return true;
        }else if (horarioTrabalho.getEntrada().compareTo(horarioTrabalho.getSaida())>0){
            PrimeFaces.current().executeScript("alert('Horário de entrada não pode ser maior que o de saída')");
            return true;
        } else {
            return false;
        }
    }

    public ControlePontoHBDAO getControlePontoHBDAO() {
        return controlePontoHBDAO;
    }

    public void setControlePontoHBDAO(ControlePontoHBDAO controlePontoHBDAO) {
        this.controlePontoHBDAO = controlePontoHBDAO;
    }

    public HorarioTrabalho getHorarioTrabalho() {
        return horarioTrabalho;
    }

    public void setHorarioTrabalho(HorarioTrabalho horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }
}
