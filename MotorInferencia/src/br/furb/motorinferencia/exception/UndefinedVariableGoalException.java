package br.furb.motorinferencia.exception;

public class UndefinedVariableGoalException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UndefinedVariableGoalException(String variableName) {
		super("A variavel objetivo "+ variableName +" n�o teve o seu valor definido");
	}

}
