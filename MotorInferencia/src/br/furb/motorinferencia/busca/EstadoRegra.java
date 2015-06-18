package br.furb.motorinferencia.busca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.furb.motorinferencia.motor.MotorInferencia;
import br.furb.motorinferencia.objetos.Expressao;
import br.furb.motorinferencia.objetos.Regra;
import br.furb.motorinferencia.variavel.Variavel;
import busca.Estado;

public class EstadoRegra implements Estado {
	
	private List<Estado> sucessores;
	
	private Variavel<?> variavel;

	private MotorInferencia motorInferencia;

	private Regra regra;

	private EstadoRegra antecessor;
	
	public static Scanner getScanner(){
		return new Scanner(System.in);
	}
	
	public EstadoRegra(MotorInferencia motorInferencia, Variavel<?> variavel){
		this.motorInferencia = motorInferencia;
		this.variavel = variavel;
		this.sucessores = new ArrayList<Estado>();
	}

	public EstadoRegra(MotorInferencia motorInferencia, Regra regra, EstadoRegra antecessor){
		this.motorInferencia = motorInferencia;
		this.regra = regra;
		this.antecessor = antecessor;
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
				this.sucessores.add(new EstadoRegra(this.motorInferencia, regra, this));
			}
		}
	}
	
	private void geraSucessoresRegra() {
		boolean variavelGerouSucessor = false;
		for (Regra regra : motorInferencia.getRegras()){
			laco: for(Expressao expressao : this.regra.getCondicao().getExpressoes()){
				Variavel<?> variavel = expressao.getVariavel();
				if (variavel.getResposta() == null){
					if (regra.getCondicao().getOperacao().getVariavel().equals(variavel)){
						this.sucessores.add(new EstadoRegra(this.motorInferencia, regra, this));
						variavelGerouSucessor = true;
					}
				} else {
					if (!expressao.testar() && !regra.hasOr()){
						break laco;
					}
					continue;
				}
				if (!variavelGerouSucessor){
					inputVariavel(variavel);
					if (this.antecessor != null){
						for(Estado estado : this.antecessor.sucessores()){
							EstadoRegra estadoRegra = (EstadoRegra) estado;
							estadoRegra.executar();
						}
					}
					if (!expressao.testar() && !regra.hasOr()){
						break laco;
					}
				}
			}
		}
	}
	
	private void executar() {
		this.regra.tryExecutaEntao();
		
	}

	private void inputVariavel(Variavel<?>  variavel){
		System.out.println("Pergunta: " + variavel.getPergunta());
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
