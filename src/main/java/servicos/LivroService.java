package servicos;

import java.util.List;

import javax.inject.Inject;

import modelos.Livro;
import repositorios.RepositorioLivro;
import transacao.Transacional;

public class LivroService {
	
	@Inject
	private RepositorioLivro repositorio;
	
	@Transacional
	public void save(Livro livro){
		if (livro == null) {
			repositorio.inserir(livro);
		}else{
			repositorio.atualizar(livro);
		}
	}
	
	@Transacional
	public void delete(Livro livro){
		repositorio.remover(livro);
	}
	
	@Transacional
	public List<Livro> devolveLivros(){
		return repositorio.todosLivros();		
	}
}
