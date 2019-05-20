package br.com.nearMaick.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.nearMaick.model.Tarefa;

@Stateless
public class TarefaBean {
	
	@PersistenceContext
	private EntityManager em;

	public void inserir(Tarefa tarefa) {
		em.persist(tarefa);
	}
	
	public void atualizar(Tarefa tarefa) {
		em.merge(tarefa);
	}
	
	public Tarefa carregar(int id) {
		return em.find(Tarefa.class, id);
	}

	public void excluir(Tarefa tarefa) {
		tarefa = carregar(tarefa.getId());
		em.remove(tarefa);
		
	}
	
	public List<Tarefa> carregarTodos() {
		TypedQuery<Tarefa> q = em.createQuery("SELECT t FROM Tarefa t ORDER BY t.prioridade", Tarefa.class);
		return q.getResultList();
	}
}
