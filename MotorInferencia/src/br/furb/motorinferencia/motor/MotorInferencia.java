package br.furb.motorinferencia.motor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.furb.motorinferencia.busca.EstadoRegra;
import br.furb.motorinferencia.objetos.Condicao;
import br.furb.motorinferencia.objetos.Regra;
import br.furb.motorinferencia.objetos.Resposta;
import br.furb.motorinferencia.variavel.Variavel;
import busca.BuscaProfundidade;

/**
 * 
 * @author TECBMJGT
 *
 */
public class MotorInferencia  implements MotorInferenciaIntf{

	private List<Regra> regras;
	
	private List<Variavel<?>> variaveis;

	private List<Variavel<?>> variaveisObjetivo;
	
	public MotorInferencia() {
		this.regras = new ArrayList<Regra>();
		this.variaveis = new ArrayList<Variavel<?>>();
		this.variaveisObjetivo = new ArrayList<Variavel<?>>();
	}
	
	/**
	 * 
	 * @return
	 */
	public Regra novaRegra() {
		Regra regra = new Regra(this.variaveis);
		this.regras.add(regra);
		return regra;
	}

	/**
	 * 
	 * @param variaveisObjetivo
	 * @throws Exception
	 */
	public void setObjetivo(String... variaveisObjetivo) throws Exception {
		laco : for (String variavelObjetivo : variaveisObjetivo){
			
			for (Variavel<?> variavel : variaveis){
				if (variavel.getNome().equals(variavelObjetivo)){
					variavel.setObjetivo(true);
					this.variaveisObjetivo.add(variavel);
					continue laco;
				}
			}
			throw new Exception("Não existe a váriavel"+variavelObjetivo);
		}
	}

	/**
	 * 
	 */
	@Override
	public void processar() {
		for(Variavel<?> variavelObjetivo : variaveisObjetivo){
			EstadoRegra estadoRegra = new EstadoRegra(this, variavelObjetivo);
			BuscaProfundidade busca = new BuscaProfundidade();
			busca.busca(estadoRegra);			
		}

	}
	
	/**
	 * 
	 */
	public List<Resposta> getResposta() throws Exception {
		List<Resposta> respostas = new ArrayList<Resposta>();
		for (Variavel<?> variavelObjetivo : variaveisObjetivo){
			if (variavelObjetivo.getResposta() != null){
				respostas.add(new Resposta(variavelObjetivo));	
			} else {
				throw new Exception("A variavel objetivo "+ variavelObjetivo.getNome() +" não teve o seu valor definido");
			}
		}
		return respostas;
	}

	/**
	 * 
	 * @return
	 */
	public List<Regra> getRegras() {
		return this.regras;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean reachGoal(){
		for (Variavel<?> variavelObjetivo : variaveisObjetivo){
			if (variavelObjetivo.getResposta() == null){
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param variavelNome
	 * @param descricao
	 */
	public void setDescricaoPergunta(String variavelNome, String descricao) {
		for (Variavel<?> variavel : variaveis){
			if (variavel.getNome().equals(variavelNome)){
				variavel.setPergunta(descricao);
			}
		}
	}
	/**
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static MotorInferenciaIntf load(InputStream inputStream) throws Exception {
		MotorInferencia motorInferencia = new MotorInferencia();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(inputStream);
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		List<String> variaveisObjetivo = new ArrayList<String>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node regraNode = nodeList.item(i);
			if (regraNode instanceof Element && "regra".equals(regraNode.getNodeName())) {
				NodeList childNodes = regraNode.getChildNodes();
				Map<String, String> seMap = new HashMap<String, String>();
				List<Map<String, String>> eMap = new ArrayList<Map<String,String>>();
				Map<String, String> entaoMap = new HashMap<String, String>();
 				for (int j = 0; j < childNodes.getLength(); j++) {
					Node node = childNodes.item(j);
					if (node instanceof Element) {
						switch (node.getNodeName()) {
						case "se":
							seMap = processar(node);
							break;
						case "e":
							eMap.add(processar(node));
							break;
						case "entao":
							entaoMap = processar(node);
							break;
						}
					}
				}
 				Regra regra = motorInferencia.novaRegra();
 				Condicao condicao = regra.se(seMap.get("variavel"), seMap.get("valor"));
 				for (Map<String, String> map : eMap){
 					condicao.e(map.get("variavel"), map.get("valor"));
 				}
 				condicao.entao(entaoMap.get("variavel"), entaoMap.get("valor"));
 				System.out.println(regra);
			} else if (regraNode instanceof Element && "variaveisObjetivo".equals(regraNode.getNodeName())) {
				NodeList childNodes = regraNode.getChildNodes();
 				for (int j = 0; j < childNodes.getLength(); j++) {
					Node node = childNodes.item(j);
					if (node instanceof Element) {
						variaveisObjetivo.add(node.getLastChild().getTextContent().trim());
					}
 				}
			}
		}
		for (String variavelObjetivo : variaveisObjetivo) {
			motorInferencia.setObjetivo(variavelObjetivo);	
		}
		
		return motorInferencia;
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	private static Map<String, String> processar(Node node) {
		Map<String, String> map = new HashMap<String, String>();
		for (int l = 0; l < node.getChildNodes().getLength(); l++) {
			Node nodeChild = node.getChildNodes().item(l);
			if (nodeChild.getNodeName().equals("variavel")){
				map.put("variavel", nodeChild.getLastChild().getTextContent().trim());
			} else if (nodeChild.getNodeName().equals("valor")){
				map.put("valor", nodeChild.getLastChild().getTextContent().trim());
			}
		}
		return map;
	}

}
