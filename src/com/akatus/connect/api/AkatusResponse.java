package com.akatus.connect.api;

/**
 * Base para criação de um objeto de resposta de uma operação. Essa classe
 * apenas define as propriedades status e descrição, comuns para requisições
 * onde algum erro ocorre.
 * 
 * @author João Batista Neto
 */
public class AkatusResponse {
	private String description = "operação não executada";
	private String status = "erro";

	/**
	 * @return Recupera uma {@link String} com a descrição da resposta (em caso
	 *         de erro), se houver.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return Recupera uma {@link String} o status da resposta.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Define a descrição da resposta.
	 * 
	 * @param description
	 *            é uma {@link String} com a descrição da resposta.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Define o status da resposta.
	 * 
	 * @param status
	 *            é uma {@link String} com o status da resposta.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}