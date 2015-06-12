package br.furb.motorinferencia.main;

import java.util.List;

import br.furb.motorinferencia.variavel.Variavel;

public class Premissa {

	private String resposta;
	
	private List<String> opcoes;

	public Premissa(Variavel<?> variavel) {
		this.opcoes = variavel.getValores();
	}

	public String getPergunta() {
		return null;
	}

	public List<String> getOpcoes() {
		return this.opcoes;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;		
	}
	
	public String getResposta() {
		return this.resposta;		
	}

}
