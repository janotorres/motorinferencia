package br.furb.motorinferencia.main;

public class Main {

	public static void main(String[] args) {
		MotorInferencia motorInferencia = new MotorInferencia();
		motorInferencia.novaRegra().se(null).e(null).ou(null).entao(null);
		motorInferencia.novaRegra().se(null).e(null).ou(null).entao(null);
		motorInferencia.novaRegra().se(null).e(null).ou(null).entao(null);
		motorInferencia.novaRegra().se(null).e(null).ou(null).entao(null);
		motorInferencia.setObjetivo("objetivo");
		motorInferencia.processar();
		Premissa premissa = null;
		while((premissa = motorInferencia.obterPremissa()) != null){
			System.out.println("Pergunta");
			System.out.println(premissa.getPergunta());
			for (String opcoes : premissa.getOpcoes()){
				System.out.println(opcoes);
			}
			
			premissa.setResposta("");
			motorInferencia.setPremissa(premissa);
		}
		
		
	}
}
