package br.furb.motorinferencia.variavel;

import java.util.List;

public abstract class Variavel<T> {

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

	public Boolean addValor(T valor){
		if (!this.valores.contains(valor)){
			return this.valores.add(valor);
		}
		return false;
	}
	
	
}
