package br.furb.motorinferencia.main;

public class Main {

	public static void main(String[] args) throws Exception {
		MotorInferencia motorInferencia = new MotorInferencia();
		motorInferencia.novaRegra().se("comida","fritura").entao("cerveja","kaiser");
		motorInferencia.novaRegra().se("comida","peixe").entao("cerveja","heinekken");
		motorInferencia.setObjetivo("cerveja");
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
