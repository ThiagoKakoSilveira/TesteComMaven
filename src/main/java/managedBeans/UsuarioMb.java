package managedBeans;

//import javax.faces.bean.ApplicationScoped;
//import javax.faces.bean.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import modelos.Pessoa;
import repositorios.RepositorioPessoa;

/**
 *
 * @author Thiago
 */
//@ManagedBean(eager=true)
//@ApplicationScoped
@Named
@ApplicationScoped
public class UsuarioMb {
    
    private RepositorioPessoa repositorio;
    private Pessoa amigoSelecionado;   

    public UsuarioMb() {
        repositorio = new RepositorioPessoa();
        amigoSelecionado = new Pessoa();        
    }

    public Pessoa getAmigoSelecionado() {
        return amigoSelecionado;
    }

    public void setAmigoSelecionado(Pessoa amigoSelecionado) {
        this.amigoSelecionado = amigoSelecionado;
    }

    public RepositorioPessoa getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(RepositorioPessoa repositorio) {
        this.repositorio = repositorio;
    }
    
    public String novoAmigo(){
        amigoSelecionado = new Pessoa();
        return ("/paginas/index?faces-redirect=true");
    }
    
    public String adicionarUsuario(){
        repositorio.inserir(amigoSelecionado);
        return(this.novoAmigo());
    }
    
    public String editarUsuario(Pessoa u){
        amigoSelecionado = u;
        return("/admin/formularioEdicao?faces-redirect=true");
    }
    public String atualizarUsuario()
    {
        return("/admin/index?faces-redirect=true");
    }

    public void removerUsuario(Pessoa usuario){
        repositorio.remover(usuario);
    }
}
