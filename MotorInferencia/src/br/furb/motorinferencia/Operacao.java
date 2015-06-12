package br.furb.motorinferencia;

import br.furb.motorinferencia.variavel.Variavel;
import br.furb.motorinferencia.variavel.VariavelString;

public class Operacao {
	
	public Variavel<?> variavel;
	
	public String valor;
	
	public Operacao(VariavelString variavelString, String valor) {
		this.variavel = variavelString;
		this.valor = valor;
	}

	public Variavel<?> getVariavel() {
		return this.variavel;
	}	

}
