package br.furb.motorinferencia.variavel;

import java.util.ArrayList;
import java.util.List;

public class VariavelString extends Variavel<String> {

	public VariavelString(String nome, boolean objetivo, List<String> valores) {
		super(nome, objetivo, valores);
	}

	public VariavelString(String nome) {
		this(nome, false, new ArrayList<String>());
	}

}
