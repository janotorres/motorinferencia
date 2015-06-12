package br.furb.motorinferencia;

import br.furb.motorinferencia.variavel.Variavel;
import br.furb.motorinferencia.variavel.VariavelString;

public class Expressao {

	private Variavel<?> variavel;
	
	private String valor;

	public Expressao(VariavelString variavel, String valor) {
		this.valor = valor;
		this.variavel = variavel;
		((VariavelString) this.variavel).addValor(valor);
	}
	
	public Expressao(Variavel<?> variavel, String valor, EnumOperadorBooleano enumOp) {
		this.valor = valor;
		this.variavel = variavel;
	}

	public String getValor() {
		return valor;
	}

	public Variavel<?> getVariavel() {
		return variavel;
	}
	
}
