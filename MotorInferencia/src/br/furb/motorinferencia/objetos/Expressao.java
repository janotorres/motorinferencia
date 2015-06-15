package br.furb.motorinferencia.objetos;

import br.furb.motorinferencia.variavel.Variavel;
import br.furb.motorinferencia.variavel.VariavelDouble;
import br.furb.motorinferencia.variavel.VariavelString;

public class Expressao {

	private Variavel<?> variavel;
	
	private Object valor;

	public Expressao(VariavelString variavel, String valor) {
		this.valor = valor;
		this.variavel = variavel;
		((VariavelString) this.variavel).addValor(valor);
	}
	
	public Expressao(VariavelDouble variavel, Double valor) {
		this.valor = valor;
		this.variavel = variavel;
		((VariavelDouble) this.variavel).addValor(valor);
	}
	
	
	public Expressao(Variavel<?> variavel, Object valor, EnumOperadorBooleano enumOp) {
		this.valor = valor;
		this.variavel = variavel;
	}

	public Object getValor() {
		return valor;
	}

	public Variavel<?> getVariavel() {
		return variavel;
	}
	
}
