package br.furb.motorinferencia.busca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.furb.motorinferencia.main.MotorInferencia;
import br.furb.motorinferencia.objetos.Expressao;
import br.furb.motorinferencia.objetos.Regra;
import br.furb.motorinferencia.variavel.Variavel;
import busca.Estado;

public class EstadoRegra implements Estado {
	
	private List<Estado> sucessores;
	
	private Variavel<?> variavel;

	private MotorInferencia motorInferencia;

	private Regra regra;
	
	public static Scanner getScanner(){
		return new Scanner(System.in);
	}
	
	public EstadoRegra(MotorInferencia motorInferencia, Variavel<?> variavel){
		this.motorInferencia = motorInferencia;
		this.variavel = variavel;
		this.sucessores = new ArrayList<Estado>();
	}

	public EstadoRegra(MotorInferencia motorInferencia, Regra regra){
		this.motorInferencia = motorInferencia;
		this.regra = regra;
		this.sucessores = new ArrayList<Estado>();
	}

	
	public boolean ehMeta() {
		return this.motorInferencia.reachGoal();
	}

	public int custo() {
		return 0;
	}

	public List<Estado> sucessores() {
		if (variavel != null){
			geraSucessores();
		} else {
			geraSucessoresRegra();
			regra.tryExecutaEntao();
		}
		
		return this.sucessores;
	}

	private void geraSucessores() {
		for (Regra regra : motorInferencia.getRegras()){
			if (regra.getCondicao().getOperacao().getVariavel().equals(this.variavel)){
				this.sucessores.add(new EstadoRegra(this.motorInferencia, regra));
			}
		}
	}
	
	private void geraSucessoresRegra() {
		for (Regra regra : motorInferencia.getRegras()){
			laco: for(Expressao expressao : this.regra.getCondicao().getExpressoes()){
				Variavel<?> variavel = expressao.getVariavel();
				boolean variavelGerouSucessor = false;
				if (variavel.getResposta() == null){
					if (regra.getCondicao().getOperacao().getVariavel().equals(variavel)){
						this.sucessores.add(new EstadoRegra(this.motorInferencia, regra));
						variavelGerouSucessor = true;
					}
				} else {
					if (!expressao.testar()){
						break laco;
					}
					continue;
				}
				if (!variavelGerouSucessor){
					inputVariavel(variavel);
					if (!expressao.testar()){
						break laco;
					}
				}
			}
		}
	}
	
	private void inputVariavel(Variavel<?>  variavel){
		System.out.println("Pergunta: " + variavel.getNome());
		System.out.println("Opções");
		for (String opcoes : variavel.getValores()){
			System.out.println(opcoes);
		}
		System.out.println("Informe a resposta");	
			
		boolean respostaValida = false;
		while (!respostaValida){
			try {
				variavel.setResposta(getScanner().next());
				respostaValida = true;
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
	}


}
