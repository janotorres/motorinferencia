package br.furb.motorinferencia.objetos;

import java.util.ArrayList;
import java.util.List;

import br.furb.motorinferencia.variavel.Variavel;
import br.furb.motorinferencia.variavel.VariavelDouble;
import br.furb.motorinferencia.variavel.VariavelString;

public class Condicao {

	private List<Expressao> expressoes;
	
	@SuppressWarnings("unused")
	private Operacao operacao;

	private List<Variavel<?>> variaveis;
	
	public Condicao(Expressao expressao, List<Variavel<?>> variaveis) {
		this.expressoes = new ArrayList<Expressao>();
		this.variaveis = variaveis;
		expressoes.add(expressao);
	}

	public Condicao e(String variavel, String valor) {
		VariavelString variavelString = new VariavelString(variavel);
		int index = -1;
		if ((index = variaveis.indexOf(variavelString)) != -1){
			variavelString = (VariavelString) variaveis.get(index);
		} else {
			variaveis.add(variavelString);
		}
		expressoes.add(new Expressao(variavelString, valor, EnumOperadorBooleano.E));
		return this;
	}

	public Condicao e(String variavel, Double valor) {
		VariavelDouble variavelDouble = new VariavelDouble(variavel);
		int index = -1;
		if ((index = variaveis.indexOf(variavelDouble)) != -1){
			variavelDouble = (VariavelDouble) variaveis.get(index);
		} else {
			variaveis.add(variavelDouble);
		}
		expressoes.add(new Expressao(variavelDouble, valor, EnumOperadorBooleano.E));
		return this;
	}
	
	public Condicao ou(String variavel, String valor) {
		VariavelString variavelString = new VariavelString(variavel);
		int index = -1;
		if ((index = variaveis.indexOf(variavelString)) != -1){
			variavelString = (VariavelString) variaveis.get(index);
		} else {
			variaveis.add(variavelString);
		}
		expressoes.add(new Expressao(variavelString, valor, EnumOperadorBooleano.OU));
		return this;
	}

	public Condicao ou(String variavel, Double valor) {
		VariavelDouble variavelDouble = new VariavelDouble(variavel);
		int index = -1;
		if ((index = variaveis.indexOf(variavelDouble)) != -1){
			variavelDouble = (VariavelDouble) variaveis.get(index);
		} else {
			variaveis.add(variavelDouble);
		}
		expressoes.add(new Expressao(variavelDouble, valor, EnumOperadorBooleano.OU));
		return this;
	}
	
	public void entao(String variavel, String valor) {
		VariavelString variavelString = new VariavelString(variavel);
		int index = -1;
		if ((index = variaveis.indexOf(variavelString)) != -1){
			variavelString = (VariavelString) variaveis.get(index);
		} else {
			variaveis.add(variavelString);
		}
		this.operacao = new Operacao(variavelString, valor);
	}

	public Operacao getOperacao() {
		return this.operacao;
	}

	public List<Expressao> getExpressoes() {
		return this.expressoes;
	}


}
