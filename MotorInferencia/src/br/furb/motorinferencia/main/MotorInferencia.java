package br.furb.motorinferencia.main;

import java.util.ArrayList;
import java.util.List;

import br.furb.motorinferencia.objetos.Expressao;
import br.furb.motorinferencia.objetos.Regra;
import br.furb.motorinferencia.objetos.Resposta;
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
		List<Regra> regrasObjetivos = findRegraObjetivo();
		for (Regra regra : regrasObjetivos){
			for (Expressao  expressao : regra.getCondicao().getExpressoes()){
				Variavel<?> variavel = expressao.getVariavel();
				if (isPremissa(variavel)){
					Premissa premissa = new Premissa(variavel, regra);
					int index = -1;
					if ((index = premissas.indexOf(premissa)) == -1){
						this.premissas.add(0, premissa);
					} else {
						this.premissas.get(index).getRegras().add(regra);
					}
				}
			}
		}
	}
	
	private List<Regra> findRegraObjetivo(){
		List<Regra> regrasObjetivos = new ArrayList<Regra>();
		for (Regra regra : regras){
			if (regra.getCondicao().getOperacao().getVariavel().equals(variavelObjetivo)){
				regrasObjetivos.add(regra);
			}
		}
		return regrasObjetivos;
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
		int size = this.premissas.size();
		if (size > 0){
			return this.premissas.remove(size -1);
		} else {
			return null;
		}
	}

	public void setPremissa(Premissa premissa) {
		for (Regra regra : premissa.getRegras()){
			if (regra.testar()){
				regra.executaEntao();
			}
		}
		
		if (variavelObjetivo.getResposta() != null){
			this.premissas.clear();
		}
		
	}

	public Resposta getResposta() {
		return new Resposta(variavelObjetivo);
		
	}

}
