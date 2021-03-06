package br.furb.motorinferencia.objetos;

import br.furb.motorinferencia.variavel.Variavel;
import br.furb.motorinferencia.variavel.VariavelString;

public class Operacao {
	
	private Variavel<?> variavel;
	
	private String valor;
	
	public Operacao(VariavelString variavelString, String valor) {
		this.variavel = variavelString;
		this.valor = valor;
		((VariavelString) this.variavel).addValor(valor);
	}

	public Variavel<?> getVariavel() {
		return this.variavel;
	}
	
	public String getValor() {
		return this.valor;
	}
	
	public String toString() {
		return "variavel:"+variavel.getNome()+",valor:"+this.valor;
		
	}

}
