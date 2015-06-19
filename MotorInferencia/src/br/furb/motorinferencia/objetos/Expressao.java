package br.furb.motorinferencia.objetos;

import br.furb.motorinferencia.variavel.Variavel;
import br.furb.motorinferencia.variavel.VariavelDouble;
import br.furb.motorinferencia.variavel.VariavelString;

public class Expressao {

	private Variavel<?> variavel;
	
	private Object valor;

	private EnumOperadorBooleano enumOp = EnumOperadorBooleano.E;

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
		this.enumOp = enumOp;
		((VariavelString) this.variavel).addValor(valor);
	}
	
	public Expressao(VariavelDouble variavel, Double valor, EnumOperadorBooleano enumOp) {
		this.valor = valor;
		this.variavel = variavel;
		this.enumOp = enumOp;
		((VariavelDouble) this.variavel).addValor(valor);
	}

	public Object getValor() {
		return valor;
	}

	public EnumOperadorBooleano getEnumOp() {
		return enumOp;
	}
	
	public Variavel<?> getVariavel() {
		return variavel;
	}

	public boolean testar() {
		if (variavel.getResposta() != null && valor != null){
			if (variavel instanceof VariavelString){
				return variavel.getResposta().equals(valor);
			} else {
				return Double.valueOf(variavel.getResposta()).equals(valor);
			}
			
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "Expressao:[variavel:"+this.variavel.getNome()+",valor:"+this.valor+"]";
	}
}
