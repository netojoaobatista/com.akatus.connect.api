package com.akatus.connect.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import com.akatus.connect.api.v1.entity.AkatusAccount;

/**
 * Base para implementação de uma operação da API da Akatus. Essa classe fornece
 * uma interface básica para execução de operações da API, facilitando a
 * implementação das operações da API.
 * 
 * @author João Batista Neto
 */
public abstract class AkatusOperation {
	private String apiVersion = "v1";
	protected final Akatus akatus;
	protected final AkatusAccount account;

	/**
	 * Constroi o objeto que representa a operação passando o wrapper da API.
	 * 
	 * @param Instância
	 *            do wrapper da API da {@link Akatus}
	 */
	public AkatusOperation(Akatus akatus) {
		this.akatus = akatus;

		account = new AkatusAccount(akatus.getApiKey(), akatus.getEmail());
	}

	/**
	 * @return Uma instância de {@link AkatusRequestBuilder} para criação do
	 *         corpo da requisição.
	 */
	protected abstract AkatusRequestBuilder createDefaultRequestBuilder();

	/**
	 * @return Uma instância de {@link AkatusResponseBuilder} para criação do
	 *         objeto de resposta baseado na resposta da operação.
	 */
	protected abstract AkatusResponseBuilder createDefaultResponseBuilder();

	/**
	 * Executa a operação da API informando um Builder para a requisição e
	 * resposta.
	 * 
	 * @param Instância
	 *            de {@link AkatusRequestBuilder} responsável pela criação do
	 *            corpo da requisição.
	 * @param Instância
	 *            de {@link AkatusResponseBuilder} responsável pela criação do
	 *            objeto que representa a resposta.
	 * @return Uma instância de {@link AkatusResponse} com a resposta da
	 *         operação.
	 */
	public AkatusResponse execute(AkatusRequestBuilder requestBuilder,
			AkatusResponseBuilder responseBuilder) {

		AkatusResponse response = new AkatusResponse();

		try {
			final URL url = new URL(akatus.getHost() + getPath());
			final HttpsURLConnection connection = (HttpsURLConnection) url
					.openConnection();

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					requestBuilder.getContentType());

			final OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());

			writer.write(requestBuilder.build(this));
			writer.flush();

			try {
				response = responseBuilder.build(readResponse(connection
						.getInputStream()));
			} catch (final IOException e) {
				response = responseBuilder.build(readResponse(connection
						.getErrorStream()));
			}
		} catch (final Exception e) {
			Logger.getLogger(AkatusOperation.class.getName()).throwing(
					this.getClass().getName(), "execute", e);
		}

		return response;
	}

	/**
	 * Executa a operação utilizando o Builder padrão da operação para a
	 * requisição e resposta.
	 * 
	 * @return Uma instância de {@link AkatusResponse} com a resposta da
	 *         operação.
	 */
	public AkatusResponse execute() {
		return execute(createDefaultRequestBuilder(),
				createDefaultResponseBuilder());
	}

	/**
	 * @return A instância de {@link AkatusAccount} que será utilizada na
	 *         integração.
	 */
	public AkatusAccount getAccount() {
		return account;
	}

	/**
	 * @return {@link String} que representa o caminho da requisição da
	 *         operação.
	 */
	public abstract String getPath();

	/**
	 * @return {@link String} que representa a versão da operação.
	 */
	public String getVersion() {
		return apiVersion;
	}

	private String readResponse(InputStream inputStream) throws IOException {
		final BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));

		final StringBuffer stringBuffer = new StringBuffer();
		String line;

		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line);
		}

		return stringBuffer.toString();
	}

	/**
	 * @param Uma
	 *            {@link String} que representa a versão que será utilizada.
	 */
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
}