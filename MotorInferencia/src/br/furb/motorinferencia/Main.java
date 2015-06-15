package br.furb.motorinferencia;

import java.util.Scanner;

import br.furb.motorinferencia.main.MotorInferencia;
import br.furb.motorinferencia.main.Premissa;
import br.furb.motorinferencia.objetos.Resposta;

public class Main {

	public static void main(String[] args) throws Exception {
		MotorInferencia motorInferencia = new MotorInferencia();
		motorInferencia.novaRegra().se("tipocomida","fritura").e("fritura","peixe").entao("cerveja","kaiser");
		motorInferencia.novaRegra().se("tipocomida","fritura").e("fritura","batata").entao("cerveja","heinekken");
		motorInferencia.novaRegra().se("tipocomida","carne").e("carne","frango").entao("cerveja","kaiser");
		motorInferencia.novaRegra().se("tipocomida","carne").e("carne","boi").entao("cerveja","heinekken");
		motorInferencia.setObjetivo("cerveja");
		motorInferencia.processar();
		Premissa premissa = null;
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			while((premissa = motorInferencia.obterPremissa()) != null){
				System.out.println("Pergunta: " + premissa.getPergunta());
				System.out.println("Opções");
				for (String opcoes : premissa.getOpcoes()){
					System.out.println(opcoes);
				}
				System.out.println("Informe a resposta");	
					
				boolean respostaValida = false;
				while (!respostaValida){
					try {
						premissa.setResposta(scanner.next());
						respostaValida = true;
					} catch (Exception e){
						System.out.println(e.getMessage());
					}
				}
	
				motorInferencia.setPremissa(premissa);
			}
		} finally{
			if (scanner != null)
				scanner.close();
		}
		Resposta resposta = motorInferencia.getResposta();
		System.out.println(resposta.getNome());
		System.out.println(resposta.getResposta());
	}
}
