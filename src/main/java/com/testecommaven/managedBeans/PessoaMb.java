package com.testecommaven.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.testecommaven.modelos.Pessoa;
import com.testecommaven.repositorios.RepositorioPessoa;
import com.testecommaven.util.Mensageiro;

/**
 *
 * @author Thiago
 */
@Named(value = "pessoaMb")
@RequestScoped
public class PessoaMb implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Pessoa amigo;
	private List<Pessoa> lista;
	
	@Inject
    private RepositorioPessoa repositorio;

    public PessoaMb() {
        
    }
    
    @PostConstruct
    public void init(){
    	amigo = new Pessoa();
    	lista = repositorio.todasPessoas();
    }
    
    public void deletarAmigo(Pessoa amigo) {
    	try {
    		lista.remove(amigo);
    		repositorio.remover(amigo);			
		} catch (Exception e) {
			 Mensageiro.nootificaErro("Erro inesperado!", "Não foi possível excluir.");
		}
    }

    public void salvar() {
    	if(amigo.getId()==null){
    		lista.add(amigo);
    		repositorio.inserir(amigo);
//    		limpar();    		
    	}else {
    		for (Pessoa pessoa : lista) {
				if(pessoa.getId() == amigo.getId()){
					pessoa = amigo;
				}
			}
    		repositorio.atualizar(amigo);
//    		limpar();
    	}
    }

    public void limpar() {
        setAmigo(new Pessoa());
        setLista(repositorio.todasPessoas());
    }
    
    //GETERS & SETERS
    
    public Pessoa getAmigo() {
//        if(amigo == null){
//        	amigo = new Pessoa();
//        }
    	return amigo;
    }

    public void setAmigo(Pessoa amigo) {
        this.amigo = new Pessoa(amigo);
    }
   
	public List<Pessoa> getLista() {
//		if(lista == null){
//			lista = repositorio.todasPessoas();
//		}
		return lista;
	}

	public void setLista(List<Pessoa> lista) {
		this.lista = lista;
	}
}
