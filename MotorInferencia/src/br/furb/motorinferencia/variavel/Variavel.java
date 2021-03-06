package br.furb.motorinferencia.variavel;

import java.util.ArrayList;
import java.util.List;

public abstract class Variavel<T> {

	private String nome;
	
	private boolean objetivo;
	
	private List<T> valores;

	private String resposta;

	private String pergunta;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isObjetivo() {
		return objetivo;
	}

	public void setObjetivo(boolean objetivo) {
		this.objetivo = objetivo;
	}

	public Variavel(String nome, boolean objetivo, List<T> valores) {
		super();
		this.nome = nome;
		this.objetivo = objetivo;
		this.valores = valores;
	}

	public Boolean addValor(T valor){
		if (!this.valores.contains(valor)){
			return this.valores.add(valor);
		}
		return false;
	}
	
	@Override
	public boolean equals(Object object) {
		return this.getNome().equals(((Variavel<?>) object).getNome());
	}

	public List<String> getValores() {	
		List<String> valores = new ArrayList<String>();
		for (T valor : this.valores){
			valores.add(valor != null ? valor.toString() : null);
		}
		return valores;
	}

	public void setResposta(String resposta) throws Exception {
		if (!this.getValores().contains(resposta))
			throw new Exception("Resposta inexistente");
		this.resposta = resposta;
		
	}

	public String getResposta() {
		return this.resposta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	
	public String getPergunta() {
		return this.pergunta != null ? this.pergunta : this.nome;
	}
	
	
}
