package br.furb.motorinferencia;

import br.furb.motorinferencia.motor.MotorInferencia;

public class Main {

	public static void main(String[] args) throws Exception {
		MotorInferencia motorInferencia = new MotorInferencia();
		motorInferencia.novaRegra().se("azeite", 0d).entao("tipocomida","fritura");
		motorInferencia.novaRegra().se("azeite", 1d).entao("tipocomida", null);
		motorInferencia.novaRegra().se("tipocomida","fritura").e("fritura","peixe").entao("cerveja","kaiser");
		motorInferencia.novaRegra().se("tipocomida","fritura").e("fritura","batata").entao("cerveja","heinekken");
		motorInferencia.novaRegra().se("tipocomida","carne").e("carne","frango").entao("cerveja","heinekken");
		motorInferencia.novaRegra().se("tipocomida","carne").e("carne","boi").entao("cerveja","heinekken");
		motorInferencia.setDescricaoPergunta("azeite",  "Você gosta de azeite?");
		motorInferencia.setDescricaoPergunta("fritura", "Que alimento frito você mais gosta?");
		motorInferencia.setObjetivo("cerveja");
		motorInferencia.processar();
		System.out.println(motorInferencia.getResposta());
	}
}
