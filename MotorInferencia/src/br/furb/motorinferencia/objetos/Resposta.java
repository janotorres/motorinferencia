package br.furb.motorinferencia.objetos;

import br.furb.motorinferencia.variavel.Variavel;

public class Resposta {

	private String nome;
	
	private String resposta;

	public Resposta(Variavel<?> variavelObjetivo) {
		this.nome = variavelObjetivo.getNome();
		this.resposta = variavelObjetivo.getResposta();
	}

	public String getNome() {
		return this.nome;
	}

	public String getResposta() {
		return this.resposta;
	}
	
	@Override
	public String toString() {
		return "Resposta:[nome:"+this.nome+",resposta:"+this.resposta+"]";
	}

}
