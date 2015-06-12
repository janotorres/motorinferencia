package br.furb.motorinferencia.main;

import java.util.ArrayList;
import java.util.List;

import br.furb.motorinferencia.Expressao;
import br.furb.motorinferencia.Regra;
import br.furb.motorinferencia.variavel.Variavel;

public class MotorInferencia {


	private List<Regra> regras;
	
	private List<Variavel<?>> variaveis;
	
	private List<Premissa> premissas;

	private Variavel<?> variavelObjetivo;
	
	public MotorInferencia() {
		this.regras = new ArrayList<Regra>();
		this.variaveis = new ArrayList<Variavel<?>>();
		this.premissas = new ArrayList<Premissa>();
	}
	
	public Regra novaRegra() {
		Regra regra = new Regra(this.variaveis);
		this.regras.add(regra);
		return regra;
	}

	public void setObjetivo(String variavelObjetivo) throws Exception {
		for (Variavel<?> variavel : variaveis){
			if (variavel.getNome().equals(variavelObjetivo)){
				variavel.setObjetivo(true);
				this.variavelObjetivo = variavel;
				return;
			}
		}
		
		throw new Exception("Não existe a váriavel"+variavelObjetivo);
	
	}

	public void processar() {
		for (Regra regra : regras){
			if (regra.getCondicao().getOperacao().getVariavel().equals(variavelObjetivo)){
				for (Expressao expressao : regra.getCondicao().getExpressoes()){
					Variavel<?> variavel = expressao.getVariavel();
					if (isPremissa(variavel)){
						this.premissas.add(new Premissa(variavel));
					}
				}
			}
		}
		
		
	}

	private boolean isPremissa(Variavel<?> variavel) {
		for (Regra regra : regras){
			if (regra.getCondicao().getOperacao().getVariavel().equals(variavel)){
				return false;
			}
		}
		return true;
		
	}

	public Premissa obterPremissa() {
		return this.premissas.get(0);
	}

	public void setPremissa(Premissa premissa) {
		
	}

}
