package br.furb.motorinferencia;

public class Regra {
	
	
	private Expressao expressao;
	
	private Operacao operacao;
	
	public Regra se(Expressao expressao){
		return this;
	}
	
	public Regra e(Expressao expressao){
		return this;
	}
	
	public Regra ou(Expressao expressao){
		return this;
	}
	
	public Regra entao(Variavel<String> variavel, String valor){
		this.operacao = new Operacao<String>(variavel, valor);
		return this;
	}

	public Regra entao(Variavel<Double> variavel, Double valor ){
		this.operacao = new Operacao<Double>(variavel, valor);
		return this;
	}
	
	public static void main(String[] args) {
		new Regra().se(new Expressao()).entao(new Variavel<String>(""), "");
	}
}
