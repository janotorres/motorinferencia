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
	
	
	public Expressao(VariavelString variavel, String valor, EnumOperadorBooleano enumOp) {
		this.valor = valor;
		this.variavel = variavel;
		((VariavelString) this.variavel).addValor(valor);
	}
	
	public Expressao(VariavelDouble variavel, Double valor, EnumOperadorBooleano enumOp) {
		this.valor = valor;
		this.variavel = variavel;
		((VariavelDouble) this.variavel).addValor(valor);
	}

	public Object getValor() {
		return valor;
	}

	public Variavel<?> getVariavel() {
		return variavel;
	}

	public boolean testar() {
		if (variavel.getResposta() != null && valor != null)
			return variavel.getResposta().equals(valor);
		return false;
		
	}
	
}
