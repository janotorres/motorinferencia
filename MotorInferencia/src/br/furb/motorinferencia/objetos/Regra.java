package br.furb.motorinferencia.objetos;

import java.util.List;

import br.furb.motorinferencia.variavel.Variavel;
import br.furb.motorinferencia.variavel.VariavelDouble;
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
		} else {
			variaveis.add(variavelString);
		}
		this.condicao  = new Condicao(new Expressao(variavelString, valor), this.variaveis);
		return this.condicao;
	}
	
	public Condicao se(String variavel, Double valor) {
		VariavelDouble variavelDouble = new VariavelDouble(variavel);
		int index = -1;
		if ((index = variaveis.indexOf(variavelDouble)) != -1){
			variavelDouble = (VariavelDouble) variaveis.get(index);
		} else {
			variaveis.add(variavelDouble);
		}
		this.condicao  = new Condicao(new Expressao(variavelDouble, valor), this.variaveis);
		return this.condicao;
	}

	public Condicao getCondicao() {
		return condicao;
	}

	public boolean testar() {
		for (Expressao expressao : this.condicao.getExpressoes()){
			if (!expressao.testar()){
				return false;
			}
		}
		return true;
		
	}

	public void executaEntao() {
		Operacao operacao = this.condicao.getOperacao();
		operacao.getVariavel().setResposta(operacao.getValor());
		
	}
}
