package br.furb.motorinferencia;

import java.util.Scanner;

import br.furb.motorinferencia.main.MotorInferencia;
import br.furb.motorinferencia.main.Premissa;

public class Main {

	public static void main(String[] args) throws Exception {
		MotorInferencia motorInferencia = new MotorInferencia();
		motorInferencia.novaRegra().se("comida","fritura").entao("cerveja","kaiser");
		motorInferencia.novaRegra().se("comida","peixe").entao("cerveja","heinekken");
		motorInferencia.setObjetivo("cerveja");
		motorInferencia.processar();
		Premissa premissa = null;
		while((premissa = motorInferencia.obterPremissa()) != null){
			System.out.println("Pergunta: " + premissa.getPergunta());
			System.out.println("Opções");
			for (String opcoes : premissa.getOpcoes()){
				System.out.println(opcoes);
			}
			System.out.println("Informe a resposta");	
			
			Scanner scanner = null;
			try {
				scanner = new Scanner(System.in);
				boolean respostaValida = false;
				while (!respostaValida){
					try {
						premissa.setResposta(scanner.next());
						respostaValida = true;
					} catch (Exception e){
						System.out.println(e.getMessage());
					}
				}
			} finally{
				if (scanner != null)
					scanner.close();
			}
			motorInferencia.setPremissa(premissa);
		}
		
		
	}
}
