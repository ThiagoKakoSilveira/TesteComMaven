package com.testecommaven.managedBeans;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.testecommaven.modelos.Emprestimo;
import com.testecommaven.repositorios.RepositorioEmprestimo;
import com.testecommaven.util.DateUtil;

/**
 *
 * @author Thiago
 */

@Named(value = "emprestimoMb")
@RequestScoped
public class EmprestimoMb {

    private Emprestimo emprestimo;	
    private List<Emprestimo> listaDeEmprestimo; 
    private Date empDataTemp, devDateTemp;
    
    @Inject
    private RepositorioEmprestimo repositorioEmprestimo;

    public EmprestimoMb() {
        emprestimo = new Emprestimo();
        repositorioEmprestimo = new RepositorioEmprestimo();
    }

    public void salvar() {
    	if(emprestimo.getId() == null){
    		convertDateToLocalDate();
    		emprestimo.setEmprestimoAtivo(true);
    		emprestimo.getLivro().setEmprestado(true);
    		listaDeEmprestimo.add(emprestimo);
    		repositorioEmprestimo.inserir(emprestimo);
//    		limpar();    		
    	}else{
    		for (Emprestimo e : listaDeEmprestimo) {
				if(e.getId() == emprestimo.getId()){
					e = emprestimo;
				}
			}
    		repositorioEmprestimo.atualizar(emprestimo);
    	}
    }
    
    private void convertDateToLocalDate(){
    	
    	emprestimo.setDataEmprestimo((DateUtil.dateToLocalDate(empDataTemp)));
//    	emprestimo.setDataDevolucao(null); tem que fazer ainda
    }

    public void deletar() {
        repositorioEmprestimo.remover(emprestimo);
    }

    public void limpar() {
        setEmprestimo(new Emprestimo());
    }

    public void devolver(Emprestimo emprestimo) {
//        listaDeEmprestimo.devolver(emprestimo);
        limpar();
    }

    public void carregarListaDeEmprestimo() {
        setListaDeEmprestimo(repositorioEmprestimo.todosEmprestimos());
    }

    //GETERS & SETERS
    
    public Emprestimo getEmprestimo() {
        if (emprestimo == null) {
            limpar();
        }
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public List<Emprestimo> getListaDeEmprestimo() {
        if (listaDeEmprestimo == null) {
            carregarListaDeEmprestimo();
        }
        return listaDeEmprestimo;
    }

    public void setListaDeEmprestimo(List<Emprestimo> listaDeEmprestimo) {
        this.listaDeEmprestimo = listaDeEmprestimo;
    }

	public Date getEmpDataTemp() {
		return empDataTemp;
	}

	public void setEmpDataTemp(Date empDataTemp) {
		this.empDataTemp = empDataTemp;
	}

	public Date getDevDateTemp() {
		return devDateTemp;
	}

	public void setDevDateTemp(Date devDateTemp) {
		this.devDateTemp = devDateTemp;
	}
}