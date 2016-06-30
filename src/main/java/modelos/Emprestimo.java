package modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import util.ConversorDeLocalDate;

/**
 *
 * @author Thiago
 */
@Entity
public class Emprestimo implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(nullable=false)
    private Livro livro;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(nullable=false)
    private Pessoa amigo;
	@Convert(converter = ConversorDeLocalDate.class)
    private LocalDate dataEmprestimo;
    @Convert(converter = ConversorDeLocalDate.class)
    @Column(nullable=true)
    private LocalDate dataDevolucao;
    @Column(nullable=false)
    private boolean emprestimoAtivo;

    public Emprestimo() {
    }

    public Emprestimo(Integer id, Livro livro, Pessoa amigo, LocalDate dataEmprestimo, 
            LocalDate dataDevolucao, boolean emprestimoAtivo) {
    	
        this.id = id;
        this.livro = livro;
        this.amigo = amigo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.emprestimoAtivo = emprestimoAtivo;
    }
    
    //GETERS & SETERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Pessoa getAmigo() {
        return amigo;
    }

    public void setAmigo(Pessoa amigo) {
        this.amigo = amigo;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isEmprestimoAtivo() {
        return emprestimoAtivo;
    }
    
    public String getEmprestimoAtivoString(){    	
    	if(emprestimoAtivo)return "Ativo";
        else return "Devolvido";
    }

    public void setEmprestimoAtivo(boolean emprestimoAtivo) {
        this.emprestimoAtivo = emprestimoAtivo;
    }
    
    public String getDataEmprestimoString(){
        return dataEmprestimo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    
    public String getDataDevolucaoString(){
    	if(dataDevolucao != null){    		
    		return dataDevolucao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    	}else {
    		return "Ainda não foi devolvido";
    	}
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.livro);
        hash = 89 * hash + Objects.hashCode(this.amigo);
        hash = 89 * hash + Objects.hashCode(this.dataEmprestimo);
        hash = 89 * hash + Objects.hashCode(this.dataDevolucao);
        hash = 89 * hash + (this.emprestimoAtivo ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Emprestimo other = (Emprestimo) obj;
        if (this.emprestimoAtivo != other.emprestimoAtivo) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.livro, other.livro)) {
            return false;
        }
        if (!Objects.equals(this.amigo, other.amigo)) {
            return false;
        }
        if (!Objects.equals(this.dataEmprestimo, other.dataEmprestimo)) {
            return false;
        }
        if (!Objects.equals(this.dataDevolucao, other.dataDevolucao)) {
            return false;
        }
        return true;
    }
}
