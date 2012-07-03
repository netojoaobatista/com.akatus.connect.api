package com.akatus.connect.api;

/**
 * Interface para definição de um construtor do objeto da resposta.
 * 
 * @author João Batista Neto
 */
public interface AkatusResponseBuilder {
	/**
	 * Constroi o objeto que representa a resposta.
	 * 
	 * @param A
	 *            {@link String} que será utilizada para a construção do objeto
	 *            de resposta da operação.
	 * @return Uma instância de {@link AkatusResponse} que representa a resposta
	 *         da operação.
	 */
	public AkatusResponse build(String response);
}