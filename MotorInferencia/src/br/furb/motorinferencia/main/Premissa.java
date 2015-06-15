package br.furb.motorinferencia.main;

import java.util.ArrayList;
import java.util.List;

import br.furb.motorinferencia.objetos.Regra;
import br.furb.motorinferencia.variavel.Variavel;

public class Premissa {

	//private String resposta;
	
///	private List<String> opcoes;

	//private String pergunta;

	private Variavel<?> variavel;

	private List<Regra> regras = new ArrayList<Regra>();

	public Premissa(Variavel<?> variavel, Regra regra) {
		this.variavel = variavel;
		this.regras.add(regra);
	}

	public String getPergunta() {
		return this.variavel.getNome();
	}

	public List<String> getOpcoes() {
		return this.variavel.getValores();
	}

	public void setResposta(String resposta) throws Exception {
		if (!this.variavel.getValores().contains(resposta))
			throw new Exception("Resposta inexistente");
		this.variavel.setResposta(resposta);		
	}
	
	public String getResposta() {
		return this.variavel.getResposta();		
	}

	@Override
	public boolean equals(Object obj) {
		return this.getPergunta().equals(((Premissa) obj).getPergunta());
	}

	public List<Regra> getRegras() {
		return this.regras;
	}
}
