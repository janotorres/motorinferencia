package br.furb.motorinferencia.variavel;

import java.util.ArrayList;
import java.util.List;

public class VariavelDouble extends Variavel<Double> {

	public VariavelDouble(String nome, boolean objetivo, List<Double> valores) {
		super(nome, objetivo, valores);
	}

	public VariavelDouble(String nome) {
		this(nome, false, new ArrayList<Double>());
	}

}
