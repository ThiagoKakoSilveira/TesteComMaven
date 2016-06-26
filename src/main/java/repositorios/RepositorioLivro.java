package repositorios;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import modelos.Livro;
import transacao.Transacional;

/**
 *
 * @author Thiago
 */
public class RepositorioLivro {

    @Inject
    private EntityManager entityManager;

    @Transacional
    public void inserir(Livro livro) {
        entityManager.persist(livro);
    }
    
    @SuppressWarnings("unchecked")
    public List<Livro> todosLivros() {
        return entityManager.createQuery("select l from " + Livro.class.getSimpleName() + " l").getResultList();
    }
    
    @Transacional
    public void remover(Livro livro) {
        entityManager.remove(entityManager.merge(livro));
    }
    
    @Transacional
    public void removerPorId(Integer id) {
        Livro entity = entityManager.find(Livro.class, id);
        entityManager.remove(entity);
    }
    
    @Transacional
    public void atualizar(Livro livro) {
        entityManager.merge(livro);
    }
    
    @Transacional
    public Livro pesquisarPorID(Integer id) {
        return entityManager.find(Livro.class, id);
    }
}
