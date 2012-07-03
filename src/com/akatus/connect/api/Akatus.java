package com.akatus.connect.api;

import com.akatus.connect.api.v1.cart.CartOperation;
import com.akatus.connect.api.v1.paymentmethod.PaymentMethodsOperation;

/**
 * Wrapper de integração com a API da Akatus.
 * 
 * @author João Batista Neto
 */
public class Akatus {
	/**
	 * Ambientes de execução das operações da API da Akatus
	 */
	public enum Environment {
		/**
		 * Ambiente de testes da Akatus
		 */
		DEVELOPMENT("https://dev.akatus.com"),

		/**
		 * Ambiente de produção da Akatus
		 */
		PRODUCTION("https://www.akatus.com");

		private final String host;

		Environment(String host) {
			this.host = host;
		}

		public String getHost() {
			return host;
		}
	};

	private final String apiKey;
	private final String email;
	private final Environment environment;

	/**
	 * Constroi a instância do wrapper da Api da Akatus utilizando o ambiente de
	 * produção.
	 * 
	 * @param {@link String} email O email da conta do vendedor.
	 * @param {@link String} apiKey A chave da Api definida no painel da conta
	 *        do vendedor.
	 */
	public Akatus(String email, String apiKey) {
		this(Environment.PRODUCTION, email, apiKey);
	}

	/**
	 * Constroi a instância do wrapper da Api da Akatus definindo o ambiente de
	 * execução.
	 * 
	 * @param {@link Akatus.Environment} environment O ambiente de execução das
	 *        operações da Api.
	 * @param {@link String} email O email da conta do vendedor.
	 * @param {@link String} apiKey A chave da Api definida no painel da conta
	 *        do vendedor.
	 */
	public Akatus(Environment environment, String email, String apiKey) {
		this.environment = environment;
		this.email = email;
		this.apiKey = apiKey;
	}

	/**
	 * @return Cria uma instância de {@link CartOperation} para ser utilizada na
	 *         integração com a API do carrinho da Akatus.
	 */
	public CartOperation cart() {
		return new CartOperation(this);
	}

	/**
	 * @return {@link String} A chave da Api.
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @return {@link String} O email do vendedor.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return {@link Akatus.Environment} O ambiente onde as operações serão
	 *         executadas.
	 */
	public Environment getEnvironment() {
		return environment;
	}

	/**
	 * @return {@link String} URL para onde as requisições serão enviadas.
	 */
	public String getHost() {
		return environment.getHost();
	}

	/**
	 * @return Cria uma instância de {@link PaymentMethodsOperation} para ser
	 *         utilizada na integração com a API de meios de pagamentos.
	 */
	public PaymentMethodsOperation paymentMethods() {
		return new PaymentMethodsOperation(this);
	}
}