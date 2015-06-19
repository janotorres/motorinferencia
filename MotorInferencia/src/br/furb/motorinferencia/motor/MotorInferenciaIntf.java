package br.furb.motorinferencia.motor;

import java.util.List;

import br.furb.motorinferencia.objetos.Resposta;

public interface MotorInferenciaIntf {

	public void processar();

	public List<Resposta> getResposta() throws Exception;

}
