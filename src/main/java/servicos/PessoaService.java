package servicos;

import java.util.List;

import javax.inject.Inject;

import modelos.Pessoa;
import repositorios.RepositorioPessoa;
import transacao.Transacional;

public class PessoaService {
	
	@Inject
	private RepositorioPessoa repositorio;
	
	@Transacional
	public void save(Pessoa amigo){
		if (amigo == null) {
			repositorio.inserir(amigo);
		}else{
			repositorio.atualizar(amigo);
		}
	}
	
	@Transacional
	public void delete(Pessoa amigo){
		repositorio.remover(amigo);
	}
	
	@Transacional
	public List<Pessoa> devolvePessoas(){
		return repositorio.todasPessoas();		
	}
}
