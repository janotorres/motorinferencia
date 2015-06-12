package br.furb.motorinferencia.main;

import java.util.ArrayList;
import java.util.List;

import br.furb.motorinferencia.Regra;
import br.furb.motorinferencia.variavel.Variavel;

public class MotorInferencia {


	private List<Regra> regras;
	
	private List<Variavel<?>> variaveis;
	
	public MotorInferencia() {
		this.regras = new ArrayList<Regra>();
		this.variaveis = new ArrayList<Variavel<?>>();
	}
	
	public Regra novaRegra() {
		Regra regra = new Regra(this.variaveis);
		this.regras.add(regra);
		return regra;
	}

	public void setObjetivo(String variavelObjetivo) {
		for (Variavel<?> variavel : variaveis){
			if (variavel.getNome().equals(variavelObjetivo)){
				variavel.setObjetivo(true);
				return;
			}
		}
	}

	public void processar() {
		
	}

	public Premissa obterPremissa() {
		return null;
	}

	public void setPremissa(Premissa premissa) {
		
	}

}
