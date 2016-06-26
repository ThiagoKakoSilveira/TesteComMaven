package managedBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelos.Livro;
import repositorios.RepositorioLivro;
import util.DateUtil;

/**
 *
 * @author Thiago
 */
@Named(value = "livroMb")
@RequestScoped
public class LivroMb implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private RepositorioLivro repositorio;
	
	private Livro livro;
	private List<Livro> lista;
	private Date temporaria;

    public LivroMb() {
        
    }
    
    @PostConstruct
    public void init(){
    	lista = repositorio.todosLivros();
        livro = new Livro();
    }
    
    public void salvar(){
    	if(livro.getId() == null){
    		convertDateToLocalDate();
    		lista.add(livro);
    		repositorio.inserir(livro);
    		limpar();    		
    	}else{
    		for (Livro l : lista) {
				if(l.getId() == livro.getId()){
					l = livro;
				}
			}
    		convertDateToLocalDate();
    		repositorio.atualizar(livro);
    		limpar();
    	}
    }
    
    private void convertDateToLocalDate(){
    	livro.setAquisicao(DateUtil.dateToLocalDate(temporaria));
    }

    public void limpar() {
        setLivro(new Livro());
        setTemporaria(null);
    }
    
    public void deletarLivro(){
        repositorio.remover(getLivro());
    }
    
    //GETERS & SETERS
    
    public Livro getLivro() {
    	return livro;
    }
    
    public void setLivro(Livro livro) {
    	this.livro = livro;
    }

	public List<Livro> getLista() {
		return lista;
	}

	public void setLista(List<Livro> lista) {
		this.lista = lista;
	}

	public Date getTemporaria() {
		return temporaria;
	}

	public void setTemporaria(Date temporaria) {
		this.temporaria = temporaria;
	}
}
