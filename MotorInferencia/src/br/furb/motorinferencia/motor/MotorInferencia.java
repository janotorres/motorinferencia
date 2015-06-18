package br.furb.motorinferencia.motor;

import java.util.ArrayList;
import java.util.List;

import br.furb.motorinferencia.busca.EstadoRegra;
import br.furb.motorinferencia.exception.UndefinedVariableGoalException;
import br.furb.motorinferencia.objetos.Regra;
import br.furb.motorinferencia.objetos.Resposta;
import br.furb.motorinferencia.variavel.Variavel;
import busca.BuscaProfundidade;

public class MotorInferencia {


	private List<Regra> regras;
	
	private List<Variavel<?>> variaveis;

	private List<Variavel<?>> variaveisObjetivo;
	
	public MotorInferencia() {
		this.regras = new ArrayList<Regra>();
		this.variaveis = new ArrayList<Variavel<?>>();
		this.variaveisObjetivo = new ArrayList<Variavel<?>>();
	}
	
	public Regra novaRegra() {
		Regra regra = new Regra(this.variaveis);
		this.regras.add(regra);
		return regra;
	}

	public void setObjetivo(String... variaveisObjetivo) throws Exception {
		laco : for (String variavelObjetivo : variaveisObjetivo){
			
			for (Variavel<?> variavel : variaveis){
				if (variavel.getNome().equals(variavelObjetivo)){
					variavel.setObjetivo(true);
					this.variaveisObjetivo.add(variavel);
					continue laco;
				}
			}
			throw new Exception("Não existe a váriavel"+variavelObjetivo);
		}
	}

	public void processar() {
		for(Variavel<?> variavelObjetivo : variaveisObjetivo){
			EstadoRegra estadoRegra = new EstadoRegra(this, variavelObjetivo);
			BuscaProfundidade busca = new BuscaProfundidade();
			busca.busca(estadoRegra);			
		}

	}
	public List<Resposta> getResposta() throws UndefinedVariableGoalException {
		List<Resposta> respostas = new ArrayList<Resposta>();
		for (Variavel<?> variavelObjetivo : variaveisObjetivo){
			if (variavelObjetivo.getResposta() != null){
				respostas.add(new Resposta(variavelObjetivo));	
			} else {
				throw new UndefinedVariableGoalException(variavelObjetivo.getNome());
			}
			
			
		}
		return respostas;
	}

	public List<Regra> getRegras() {
		return this.regras;
	}
	
	public boolean reachGoal(){
		for (Variavel<?> variavelObjetivo : variaveisObjetivo){
			if (variavelObjetivo.getResposta() == null){
				return false;
			}
		}
		
		return true;
		
	}

	public void setDescricaoPergunta(String variavelNome, String descricao) {
			for (Variavel<?> variavel : variaveis){
				if (variavel.getNome().equals(variavelNome)){
					variavel.setPergunta(descricao);
				}
			}
		
	}

}
