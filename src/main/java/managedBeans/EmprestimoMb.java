package managedBeans;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import modelos.Emprestimo;
import repositorios.RepositorioEmprestimo;
import util.DateUtil;

/**
 *
 * @author Thiago
 */

@Named(value = "emprestimoMb")
@RequestScoped
public class EmprestimoMb {
	
	private final int SALVA = 1;	
	private int controlador;
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
    		convertDateToLocalDate(SALVA);
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
    		convertDateToLocalDate(SALVA);
    		repositorioEmprestimo.atualizar(emprestimo);
    	}
    }
    
    private void convertDateToLocalDate(int acao){
    	if(acao == SALVA){
    		emprestimo.setDataEmprestimo((DateUtil.dateToLocalDate(empDataTemp)));    		
    	}else{
    		emprestimo.setDataDevolucao((DateUtil.dateToLocalDate(devDateTemp)));    		
    	}
    }

    public void deletar() {
        repositorioEmprestimo.remover(emprestimo);
    }

    public void limpar() {
        setEmprestimo(new Emprestimo());
    }

    public void devolver(Emprestimo emprestimo) {
    	emprestimo.setDataDevolucao(LocalDate.now());
    	emprestimo.setEmprestimoAtivo(false);
    	repositorioEmprestimo.atualizar(emprestimo);
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

	public int getControlador() {
		return controlador;
	}

	public void setControlador(int controlador) {
		this.controlador = controlador;
	}

	public int getSALVA() {
		return SALVA;
	}	
}