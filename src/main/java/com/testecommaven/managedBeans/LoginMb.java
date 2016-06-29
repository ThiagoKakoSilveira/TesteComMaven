package com.testecommaven.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.testecommaven.modelos.Pessoa;
import com.testecommaven.repositorios.RepositorioPessoa;

/**
 *
 * @author Thiago
 */
@ManagedBean
@javax.faces.bean.SessionScoped
public class LoginMb implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Inject
    private RepositorioPessoa repositorioPessoa;
    
    private Pessoa pessoaLogada;
    private String login, senha;
    private final String nomeMae = "1";
    private final String senhaMae = "1";

    /**
     * Creates a new instance of LoginMb
     */
    public LoginMb() {
        pessoaLogada = new Pessoa();
    }

    @PostConstruct
    public void init() {
        pessoaLogada = new Pessoa();
    }

    public Pessoa getPessoaLogada() {
        if (pessoaLogada == null) {
            pessoaLogada = new Pessoa();
        }
        return pessoaLogada;
    }

    public void setPessoaLogada(Pessoa pessoaLogada) {
        this.pessoaLogada = pessoaLogada;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public String getSenhaMae() {
        return senhaMae;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean estaLogado() {
        return (pessoaLogada != null);
    }

    public boolean eAdmin() {
        return (this.estaLogado() && (login.equals(nomeMae) && senha.equals(senhaMae)));
    }

    public String logar() {        
        List<Pessoa> listaPessoas = repositorioPessoa.todasPessoas();
        if ((login.equals(nomeMae)) && senha.equals(senhaMae)) {//colocar uma lógica para testar se Ã© a mÃ£e se for colocar a opÃ§Ã£o de crud
            FacesContext.getCurrentInstance().addMessage("Logado", new FacesMessage("Bem vindo!!! A sua Biblioteca Dona Clélia "));
            return ("/paginas/admin/cleliaLogada.xhtml");
        } else {
            for (Pessoa pessoa : listaPessoas) {
                if (pessoa.verificaLogin(login, senha)) {
                    pessoaLogada = pessoa;
                    FacesContext.getCurrentInstance().addMessage("Logado", new FacesMessage("Bem vindo a Biblioteca da Vovó! " + pessoa.getNome()));
                    return ("/paginas/usuario/logado.xhtml");
                }
            }
        }
        FacesContext.getCurrentInstance().addMessage("Falha no Login", new FacesMessage("não bombou"));
        return ("index.xhtml");
    }

    public String realizaLogout() {
        pessoaLogada = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return ("/index?faces-redirect=true");
    }
}
