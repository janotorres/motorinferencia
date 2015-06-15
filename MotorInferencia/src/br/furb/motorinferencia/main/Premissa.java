package br.furb.motorinferencia.main;

import java.util.List;

import br.furb.motorinferencia.variavel.Variavel;

public class Premissa {

	private String resposta;
	
	private List<String> opcoes;

	private String pergunta;

	public Premissa(Variavel<?> variavel) {
		this.pergunta = variavel.getNome();
		this.opcoes = variavel.getValores();
	}

	public String getPergunta() {
		return this.pergunta;
	}

	public List<String> getOpcoes() {
		return this.opcoes;
	}

	public void setResposta(String resposta) throws Exception {
		if (!this.opcoes.contains(resposta))
			throw new Exception("Resposta inexistente");
		this.resposta = resposta;		
	}
	
	public String getResposta() {
		return this.resposta;		
	}

	@Override
	public boolean equals(Object obj) {
		return this.pergunta.equals(((Premissa) obj).getPergunta());
	}
}
