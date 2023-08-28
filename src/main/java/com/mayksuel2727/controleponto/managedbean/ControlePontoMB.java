package com.mayksuel2727.controleponto.managedbean;

import com.mayksuel2727.controleponto.BO.ControlePontoBO;
import com.mayksuel2727.controleponto.HBDAO.ControlePontoHBDAO;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalTime;

import com.mayksuel2727.controleponto.model.Marcacao;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "ControlePontoMB")
@ViewScoped
public class ControlePontoMB implements Serializable {
    private static final long serialVersionUID = 1L;


    private ControlePontoBO controlePontoBO;

    private ControlePontoHBDAO controlePontoHBDAO;

    private Marcacao horarioTrabalho;

    private Marcacao marcacaoFeita;

    private Marcacao horarioAtraso;

    private Marcacao horaExtra;

    private boolean isRegistroPonto = false;


    @PostConstruct
    private void init() {

		this.controlePontoHBDAO = new ControlePontoHBDAO();
		this.controlePontoBO = new ControlePontoBO();
        this.horarioTrabalho = new Marcacao();
        this.marcacaoFeita = new Marcacao();
        this.horarioAtraso = new Marcacao();
		this.horaExtra = new Marcacao();
    }

    public void limparDados(){
        controlePontoBO.limparDados();
        PrimeFaces.current().ajax().update("form-horario","form-marcacao");
    }

    public void salvarMarcacaoFeita(){
        if (validar(marcacaoFeita, 2)){
            return;
        }
        controlePontoBO.salvarMarcacaoFeita(marcacaoFeita);
        this.marcacaoFeita = new Marcacao();
        calcularAtrasoHoraExtra();
        PrimeFaces.current().ajax().update("form-marcacao");
    }

    public void salvarHorarioDetrabalho(){
        if (validar(horarioTrabalho, 1)){
            return;
        }
        controlePontoBO.salvarHorarioTrabalho(horarioTrabalho);
        this.horarioTrabalho = new Marcacao();
        isRegistroPonto = true;
        PrimeFaces.current().ajax().update("form-horario","form-marcacao");
    }

    public void calcularAtrasoHoraExtra(){
        for (Marcacao horarioTrabalhoLista : controlePontoHBDAO.getListaHorarioTrablho()) {
            for (Marcacao horarioMarcado :controlePontoHBDAO.getListaHorarioMarcacao()){
                LocalTime entradaMarcada = LocalTime.parse(horarioMarcado.getEntrada());
                LocalTime saidaMarcada = LocalTime.parse(horarioMarcado.getSaida());
                LocalTime entrada = LocalTime.parse(horarioTrabalhoLista.getEntrada());
                LocalTime saida = LocalTime.parse(horarioTrabalhoLista.getSaida());

                if (entradaMarcada.isBefore(entrada)){
                    horaExtra.setEntrada(entradaMarcada.toString());
                    horaExtra.setSaida(entrada.toString());
                    controlePontoHBDAO.adicionaHoraExtra(horaExtra);
                }
                if (saidaMarcada.isAfter(saida)){
                    horaExtra.setEntrada(saida.toString());
                    horaExtra.setSaida(saidaMarcada.toString());
                    controlePontoHBDAO.adicionaHoraExtra(horaExtra);
                }

                if (entradaMarcada.isAfter(entrada)){
                    horarioAtraso.setEntrada(entrada.toString());
                    horarioAtraso.setSaida(entradaMarcada.toString());
                    controlePontoHBDAO.adicionaHorarioAtraso(horarioAtraso);
                }
                if (saidaMarcada.isBefore(saida)){
                    horarioAtraso.setEntrada(saidaMarcada.toString());
                    horarioAtraso.setSaida(saida.toString());
                    controlePontoHBDAO.adicionaHorarioAtraso(horarioAtraso);
                }

            }
        }
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
        } else {
            if (i==1){
                for (Marcacao horarioTrabalhoLista : controlePontoHBDAO.getListaHorarioTrablho()) {
                    LocalTime entradaMarcada = LocalTime.parse(macacao.getEntrada());
                    LocalTime saidaMarcada = LocalTime.parse(macacao.getSaida());
                    LocalTime entradaLista = LocalTime.parse(horarioTrabalhoLista.getEntrada());
                    LocalTime saidaLista = LocalTime.parse(horarioTrabalhoLista.getSaida());
                    if (entradaMarcada.equals(entradaLista)) {
                        PrimeFaces.current().executeScript("alert('Horário de entrada já cadastrado')");
                        return true;
                    }else if (saidaMarcada.equals(saidaLista)) {
                        PrimeFaces.current().executeScript("alert('Horário de saída já cadastrado')");
                        return true;
                    }else if (!entradaMarcada.isAfter(saidaLista) || entradaMarcada.isBefore(entradaMarcada)){
                        PrimeFaces.current().executeScript("alert('Intervalo de Horário já cadastrado')");
                        return true;
                    }else if (!saidaMarcada.isAfter(entradaLista)|| saidaMarcada.isBefore(saidaLista)){
                        PrimeFaces.current().executeScript("alert('Intervalo de Horário já cadastrado')");
                        return true;
                    }
                }
            }else if (i==2){

                for (Marcacao horarioTrabalhoLista : controlePontoHBDAO.getListaHorarioMarcacao()) {
                    LocalTime entradaMarcada = LocalTime.parse(macacao.getEntrada());
                    LocalTime saidaMarcada = LocalTime.parse(macacao.getSaida());
                    LocalTime entradaLista = LocalTime.parse(horarioTrabalhoLista.getEntrada());
                    LocalTime saidaLista = LocalTime.parse(horarioTrabalhoLista.getSaida());
                    if (entradaMarcada.equals(entradaLista)) {
                        PrimeFaces.current().executeScript("alert('Horário de Marcação já cadastrado')");
                        return true;
                    }else if (saidaMarcada.equals(saidaLista)) {
                        PrimeFaces.current().executeScript("alert('Horário de Marcação já cadastrado')");
                        return true;
                    }else if (!entradaMarcada.isAfter(saidaLista)){
                        PrimeFaces.current().executeScript("alert('Intervalo de Marcação já cadastrado')");
                        return true;
                    }else if (!saidaMarcada.isBefore(entradaLista)){
                        PrimeFaces.current().executeScript("alert('Intervalo de Marcação já cadastrado')");
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

    public boolean isRegistroPonto() {
        return isRegistroPonto;
    }

    public void setRegistroPonto(boolean registroPonto) {
        isRegistroPonto = registroPonto;
    }
}
