package br.furb.motorinferencia;

import br.furb.motorinferencia.main.MotorInferencia;

public class Main {

	public static void main(String[] args) throws Exception {
		MotorInferencia motorInferencia = new MotorInferencia();
		motorInferencia.novaRegra().se("tipocomida","fritura").e("fritura","peixe").entao("cerveja","kaiser");
		motorInferencia.novaRegra().se("tipocomida","fritura").e("fritura","batata").entao("cerveja","heinekken");
		motorInferencia.novaRegra().se("tipocomida","carne").e("carne","frango").entao("cerveja","kaiser");
		motorInferencia.novaRegra().se("tipocomida","carne").e("carne","boi").entao("cerveja","heinekken");
		motorInferencia.setObjetivo("cerveja");
		motorInferencia.processar();
		System.out.println(motorInferencia.getResposta());
	}
}
