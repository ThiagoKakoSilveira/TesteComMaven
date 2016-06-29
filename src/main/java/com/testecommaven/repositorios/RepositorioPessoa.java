package com.testecommaven.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.testecommaven.modelos.Pessoa;
import com.testecommaven.transacao.Transacional;

/**
 *
 * @author Thiago
 */
public class RepositorioPessoa {

	@Inject
	private EntityManager entityManager;

	@Transacional
	public void inserir(Pessoa amigo) {
		entityManager.persist(amigo);
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> todasPessoas() {
		List<Pessoa> teste = entityManager.createQuery("select l from " + Pessoa.class.getSimpleName() + " l").getResultList();
		return teste;
	}

	@Transacional
	public void remover(Pessoa amigo) {
		entityManager.remove(entityManager.merge(amigo));
	}

	@Transacional
	public void removerPorId(Integer id) {
		Pessoa entidade = entityManager.find(Pessoa.class, id);
		entityManager.remove(entidade);
	}

	@Transacional
	public void atualizar(Pessoa amigo) {
		entityManager.merge(amigo);
	}

	public Pessoa pesquisarPorId(Integer id) {
		return entityManager.find(Pessoa.class, id);
	}

	@SuppressWarnings("unchecked")
	public Pessoa pesquisarPorCpf(String cpf) {
		String sql = "select p from " + Pessoa.class.getSimpleName() + " p where p.cpf = '" + cpf + "'";
		List<Pessoa> resultList = entityManager.createQuery(sql).getResultList();
		return ((resultList != null && !resultList.isEmpty()) ? ((Pessoa) resultList.get(0)) : null);
	}

	public boolean existePessoaComCpf(String cpf) {
		String sql = "select count(1) from " + Pessoa.class.getSimpleName() + " p where p.cpf = '" + cpf + "'";
		long quantidade = (long) entityManager.createQuery(sql).getSingleResult();
		return (quantidade > 0);
	}

}
