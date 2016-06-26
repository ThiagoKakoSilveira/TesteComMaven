package repositorios;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import modelos.Emprestimo;

/**
 *
 * @author Thiago
 */
public class RepositorioEmprestimo {
    @Inject
	private EntityManager entityManager;
	
	public void inserir(Emprestimo emprestimo) {
		entityManager.persist(emprestimo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Emprestimo> todosEmprestimos(){
		return entityManager.createQuery("select e from " + Emprestimo.class.getSimpleName() + " e").getResultList();
	}
	
	public void remover(Emprestimo emprestimo){
		entityManager.remove(entityManager.merge(emprestimo));
	}
	
	public void removerPorId(Integer id){
		Emprestimo entidade = entityManager.find(Emprestimo.class, id);
		entityManager.remove(entidade);
	}
	
	public void atualizar(Emprestimo emprestimo){
		entityManager.merge(emprestimo);
	}
	
	public Emprestimo pesquisarPorId(Integer id){
		return entityManager.find(Emprestimo.class, id);
	}
    
}
