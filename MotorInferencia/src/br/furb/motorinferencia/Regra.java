package br.furb.motorinferencia;

import java.util.List;

import br.furb.motorinferencia.variavel.Variavel;
import br.furb.motorinferencia.variavel.VariavelString;

public class Regra {
	
	private Condicao condicao;
	
	private List<Variavel<?>> variaveis;
	
	public Regra(List<Variavel<?>> variaveis) {
		this.variaveis = variaveis;
	}

	public Condicao se(String variavel, String valor) {
		VariavelString variavelString = new VariavelString(variavel);
		int index = -1;
		if ((index = variaveis.indexOf(variavelString)) != -1){
			variavelString = (VariavelString) variaveis.get(index);
		}
		this.condicao  = new Condicao(new Expressao(variavelString, valor), this.variaveis);
		return this.condicao;
	}
}
