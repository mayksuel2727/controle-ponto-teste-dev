package com.mayksuel2727.controleponto.managedbean;

import com.mayksuel2727.controleponto.BO.ControlePontoBO;
import com.mayksuel2727.controleponto.HBDAO.ControlePontoHBDAO;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

import com.mayksuel2727.controleponto.model.Marcacao;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "ControlePontoMB")
//@Named("ControlePontoMB")
@ViewScoped
public class ControlePontoMB implements Serializable {
    private static final long serialVersionUID = 1L;


    private ControlePontoBO controlePontoBO;

    private ControlePontoHBDAO controlePontoHBDAO;

    private Marcacao horarioTrabalho;

    private Marcacao marcacaoFeita;



    @PostConstruct
    private void init() {

		this.controlePontoHBDAO = new ControlePontoHBDAO();
		this.controlePontoBO = new ControlePontoBO();
        this.horarioTrabalho = new Marcacao();
        this.marcacaoFeita = new Marcacao();

    }

    public void salvarMarcacaoFeita(){
        if (validar(marcacaoFeita, 2)){
            return;
        }
        controlePontoBO.salvarMarcacaoFeita(marcacaoFeita);
        this.marcacaoFeita = new Marcacao();
        PrimeFaces.current().ajax().update("form-marcacao-feita");
    }

    public void salvarHorarioDetrabalho(){
        if (validar(horarioTrabalho, 1)){
            return;
        }
        controlePontoBO.salvarHorarioTrabalho(horarioTrabalho);
        this.horarioTrabalho = new Marcacao();
        PrimeFaces.current().ajax().update("form-horario-trabalho");
    }

    public boolean validar(Marcacao macacao, int i){

        if (macacao.getEntrada().equals("") || macacao.getSaida().equals("")){
            PrimeFaces.current().executeScript("alert('Horário inválido')");
            return true;
        }else if (macacao.getEntrada().equals(macacao.getSaida())){
            PrimeFaces.current().executeScript("alert('Horário de entrada e saída não podem ser iguais')");
            return true;
        }else if (macacao.getEntrada().compareTo(macacao.getSaida())>0){
            PrimeFaces.current().executeScript("alert('Horário de entrada não pode ser maior que o de saída')");
            return true;
        }else if (controlePontoHBDAO.getListaHorarioTrablho().size()>3){
            PrimeFaces.current().executeScript("alert('Número máximo de horários atingido')");
            return true;
        }
        else {
            if (i==1){
                for (Marcacao horarioTrabalhoLista : controlePontoHBDAO.getListaHorarioTrablho()) {
                    if (horarioTrabalhoLista.getEntrada().equals(macacao.getEntrada())) {
                        PrimeFaces.current().executeScript("alert('Horário de entrada já cadastrado')");
                        return true;
                    }else if (horarioTrabalhoLista.getSaida().equals(macacao.getSaida())) {
                        PrimeFaces.current().executeScript("alert('Horário de saída já cadastrado')");
                        return true;
                    }else if (horarioTrabalhoLista.getEntrada().compareTo(macacao.getEntrada()) < 0 || horarioTrabalhoLista.getSaida().compareTo(macacao.getSaida()) > 0) {
                        PrimeFaces.current().executeScript("alert('Intervalo de Horário já cadastrado')");
                        return true;
                    }
                }
            }else if (i==2){
                for (Marcacao horarioTrabalhoLista : controlePontoHBDAO.getListaHorarioMarcacao()) {
                    if (horarioTrabalhoLista.getEntrada().equals(macacao.getEntrada())) {
                        PrimeFaces.current().executeScript("alert('Horário de entrada já cadastrado')");
                        return true;
                    }else if (horarioTrabalhoLista.getSaida().equals(macacao.getSaida())) {
                        PrimeFaces.current().executeScript("alert('Horário de saída já cadastrado')");
                        return true;
                    }else if (horarioTrabalhoLista.getEntrada().compareTo(macacao.getEntrada()) < 0 || horarioTrabalhoLista.getSaida().compareTo(macacao.getSaida()) > 0) {
                        PrimeFaces.current().executeScript("alert('Intervalo de Horário já cadastrado')");
                        return true;
                    }
                }
            }


            return false;
        }
    }

    public ControlePontoHBDAO getControlePontoHBDAO() {
        return controlePontoHBDAO;
    }

    public void setControlePontoHBDAO(ControlePontoHBDAO controlePontoHBDAO) {
        this.controlePontoHBDAO = controlePontoHBDAO;
    }

    public Marcacao getHorarioTrabalho() {
        return horarioTrabalho;
    }

    public void setHorarioTrabalho(Marcacao horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }

    public Marcacao getMarcacaoFeita() {
        return marcacaoFeita;
    }

    public void setMarcacaoFeita(Marcacao marcacaoFeita) {
        this.marcacaoFeita = marcacaoFeita;
    }


}
