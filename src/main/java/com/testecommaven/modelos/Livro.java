package com.testecommaven.modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.testecommaven.util.ConversorDeLocalDate;

/**
 *
 * @author Thiago
 */
@Entity
public class Livro implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false)
	private String autor;
	@Column(nullable = false)
	private String sinopse;
	@Column(nullable = false)
	private boolean emprestado;
	@Convert(converter = ConversorDeLocalDate.class)
	private LocalDate aquisicao;

	public Livro() {

	}

	public Livro(String titulo, String autor, boolean emprestimo, String sinopse, LocalDate aquisicao) {
		this.autor = autor;
		this.emprestado = emprestimo;
		this.titulo = titulo;
		this.sinopse = sinopse;
	}

	public Livro(Livro livro) {
		this.autor = livro.getAutor();
		this.emprestado = livro.isEmprestado();
		this.titulo = livro.getTitulo();
		this.sinopse = livro.getSinopse();
		this.id = livro.getId();
		this.aquisicao = livro.aquisicao;
	}
	// GETERS & SETERS

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public boolean isEmprestado() {
		return emprestado;
	}

	public String getEmprestadoString() {
		if (emprestado)
			return "Emprestado";
		else
			return "Em casa";
	}

	public void setEmprestado(boolean emprestado) {
		this.emprestado = emprestado;
	}

	public LocalDate getAquisicao() {
		return aquisicao;
	}

	public String getAquisicaoString() {
		return aquisicao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public void setAquisicao(LocalDate dataDeAquisicao) {
		this.aquisicao = dataDeAquisicao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 83 * hash + Objects.hashCode(this.id);
		hash = 83 * hash + Objects.hashCode(this.titulo);
		hash = 83 * hash + Objects.hashCode(this.autor);
		hash = 83 * hash + Objects.hashCode(this.sinopse);
		hash = 83 * hash + (this.emprestado ? 1 : 0);
		hash = 83 * hash + Objects.hashCode(this.aquisicao);
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
		final Livro other = (Livro) obj;
		if (this.emprestado != other.emprestado) {
			return false;
		}
		if (!Objects.equals(this.titulo, other.titulo)) {
			return false;
		}
		if (!Objects.equals(this.autor, other.autor)) {
			return false;
		}
		if (!Objects.equals(this.sinopse, other.sinopse)) {
			return false;
		}
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.aquisicao, other.aquisicao)) {
			return false;
		}
		return true;
	}
}
