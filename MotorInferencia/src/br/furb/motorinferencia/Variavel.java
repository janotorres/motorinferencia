package br.furb.motorinferencia;

import java.util.ArrayList;
import java.util.List;

public class Variavel<T> {

	private String nome;
	
	private boolean objetivo;
	
	private List<T> valores;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isObjetivo() {
		return objetivo;
	}

	public void setObjetivo(boolean objetivo) {
		this.objetivo = objetivo;
	}

	public Variavel(String nome, boolean objetivo, List<T> valores) {
		super();
		this.nome = nome;
		this.objetivo = objetivo;
		this.valores = valores;
	}
	
	public Variavel(String nome, boolean objetivo) {
		this(nome, objetivo, new ArrayList<T>());
	}
	
	
	public Variavel(String nome) {
		this(nome, false);
	}
	public Variavel<T> addValor(T valor){
		this.valores.add(valor);
		return this;
	}
	
}
