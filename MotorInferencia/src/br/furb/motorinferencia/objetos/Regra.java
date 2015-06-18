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
		boolean valor = true;
		for (Expressao expressao : this.condicao.getExpressoes()){
			switch (expressao.getEnumOp()) {
			case OU:
				valor = valor || expressao.testar();
				break;
			case E:
				valor = valor && expressao.testar();
				break;
			default:
				break;
			}

		}
		return valor;
		
	}

	public void executaEntao() throws Exception {
		Operacao operacao = this.condicao.getOperacao();
		operacao.getVariavel().setResposta(operacao.getValor());
		
	}

	public void tryExecutaEntao() {
		try {
			if (this.testar()){
				executaEntao();
			}
		} catch (Exception e){ 
			
		}
	}
	
	@Override
	public String toString() {	
		return "Regra:["+condicao.toString()+"]";
	}
	
	
	public boolean hasOr(){
		for (Expressao expressao : this.condicao.getExpressoes()){
			if (EnumOperadorBooleano.OU.equals(expressao.getEnumOp())){
				return true;
			}
		}
		return false;
	}
}
