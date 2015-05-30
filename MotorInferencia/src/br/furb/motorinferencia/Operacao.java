package br.furb.motorinferencia;

public class Operacao<T> {
	
	public Variavel<T> variavel;
	
	public T valor;
	
	public Operacao(Variavel<T> variavel, T valor) {
		this.variavel = variavel;
		this.valor = valor;
	}	

}
