package com.akatus.connect.api;

/**
 * Interface para definição de um construtor do corpo da requisição.
 * 
 * @author João Batista Neto
 */
public interface AkatusRequestBuilder {
	/**
	 * @return Uma {@link String} que representa o Content-Type do corpo da
	 *         requisição.
	 */
	public String getContentType();

	/**
	 * Constroi o corpo da requisição.
	 * 
	 * @param A
	 *            {@link AkatusOperation} que terá o corpo da requisição
	 *            construído.
	 * @return A {@link String} que representa o corpo que será enviado na
	 *         requisição.
	 */
	public String build(AkatusOperation operation);
}