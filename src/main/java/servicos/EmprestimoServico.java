package servicos;

import java.util.List;

import javax.inject.Inject;

import modelos.Emprestimo;
import repositorios.RepositorioEmprestimo;
import transacao.Transacional;

public class EmprestimoServico {
	@Inject
	private RepositorioEmprestimo repositorio;
	
	@Transacional
	public void save(Emprestimo emprestimo){
		if (emprestimo == null) {
			repositorio.inserir(emprestimo);
		}else{
			repositorio.atualizar(emprestimo);
		}
	}
	
	@Transacional
	public void delete(Emprestimo emprestimo){
		repositorio.remover(emprestimo);
	}
	
	@Transacional
	public List<Emprestimo> devolveEmprestimos(){
		return repositorio.todosEmprestimos();		
	}

}
