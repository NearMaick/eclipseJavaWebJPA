package br.com.nearMaick.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.nearMaick.ejb.TarefaBean;
import br.com.nearMaick.model.Tarefa;

@Named("form")
@SessionScoped
public class FormBean implements Serializable{
	
	@EJB
	private TarefaBean tarefaBean;
	
	@Inject
	private FacesContext context;
	
	private UIComponent searchInputText;
	
	private Integer tarefaId;
	
	private Tarefa tarefa;
	
	public void gravar() {
		if(tarefa.getId() == null) {
			tarefaBean.inserir(tarefa);
		}else {
			tarefaBean.atualizar(tarefa);
		}
		
		tarefa = null;
	}
	
	public void pesquisar() {
		tarefa = tarefaBean.carregar(tarefaId);
		
		if (tarefa == null)
			context.addMessage(searchInputText.getClientId(context), new FacesMessage("Tarefa não encontrada"));
		
		tarefaId = null;
	}
	
	public void excluir() {
		tarefaBean.excluir(tarefa);
	}

	public UIComponent getSearchInputText() {
		return searchInputText;
	}

	public void setSearchInputText(UIComponent searchInputText) {
		this.searchInputText = searchInputText;
	}

	public Integer getTarefaId() {
		return tarefaId;
	}

	public void setTarefaId(Integer tarefaId) {
		this.tarefaId = tarefaId;
	}

	public Tarefa getTarefa() {
		if(tarefa == null)
			tarefa = new Tarefa();
		
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	
	
}
