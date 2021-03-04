/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.RacaDAO;
import br.edu.ifsul.modelo.Raca;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author eliel
 */
@Named(value = "controleRaca")
@ViewScoped
public class ControleRaca implements Serializable {
    
    @EJB
    private RacaDAO<Raca> dao;
    private Raca objeto;
    
    public ControleRaca(){
        
    }
    
    public String listar(){
        return "/privado/raca/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Raca();
    }
    
    public void alterar(Object id){
        try {
            objeto = dao.getObjectByID(id);
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao recuperar o objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Object id){
        try {
            objeto = dao.getObjectByID(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao remover o objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if(objeto.getId() == null){
                dao.persist(objeto);
            }else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao salvar o objeto: " + Util.getMensagemErro(e));
        }
    }

    public RacaDAO<Raca> getDao() {
        return dao;
    }

    public void setDao(RacaDAO<Raca> dao) {
        this.dao = dao;
    }

    public Raca getObjeto() {
        return objeto;
    }

    public void setObjeto(Raca objeto) {
        this.objeto = objeto;
    }
    
    
    
}
