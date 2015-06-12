package br.furb.motorinferencia;

import java.util.ArrayList;
import java.util.List;

import br.furb.motorinferencia.variavel.Variavel;
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
		}
		expressoes.add(new Expressao(variavelString, valor, EnumOperadorBooleano.E));
		return this;
	}

	public Condicao ou(String variavel, String valor) {
		VariavelString variavelString = new VariavelString(variavel);
		int index = -1;
		if ((index = variaveis.indexOf(variavelString)) != -1){
			variavelString = (VariavelString) variaveis.get(index);
		}
		expressoes.add(new Expressao(variavelString, valor, EnumOperadorBooleano.OU));
		return this;
	}

	public void entao(String variavel, String valor) {
		this.operacao = new Operacao(variavel, valor);
	}


}
