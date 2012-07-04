package com.akatus.connect.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.akatus.connect.api.v1.cart.CartOperation;
import com.akatus.connect.api.v1.cart.CartResponse;
import com.akatus.connect.api.v1.entity.Address;
import com.akatus.connect.api.v1.entity.Holder;
import com.akatus.connect.api.v1.entity.Payer;
import com.akatus.connect.api.v1.entity.Phone;
import com.akatus.connect.api.v1.entity.Transaction;

public class AkatusTest {
	/**
	 * Email que será utilizado nos testes
	 */
	private String testEmail;

	/**
	 * Chave de Api que será utilizada nos testes
	 */
	private String testApiKey;

	/**
	 * Referência da transação que será utilizada nos testes
	 */
	private String testReference;

	@Before
	public void setUp() throws Exception {
		testEmail = "aa.borba@yahoo.com.br";
		testApiKey = "29D4EB49-735E-429D-A5C3-B19DF50ADC47";
		testReference = "test123";

		/**
		 * configuring the trust manager to accept the Akatus SSL certificate.
		 */
		final SSLContext sc = SSLContext.getInstance("SSL");

		sc.init(null, new TrustManager[] { new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(X509Certificate[] certs,
					String authType) {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] certs,
					String authType) {
			}
		} }, new java.security.SecureRandom());

		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}

	private void configurePayerWithoutAddress(Payer payer) {
		final Phone phone = payer.newPhone();

		phone.setNumber("1433019799");
		phone.setType(Phone.Type.RESIDENTIAL);

		payer.setName("Jose Antonio");
		payer.setEmail("ze@antonio.com.br");
	}

	private void configurePayerWithAddress(Payer payer) {
		configurePayerWithoutAddress(payer);

		final Address address = payer.newAddress();

		address.setCity("Franca");
		address.setNeighbourhood("Bairro");
		address.setComplement("uma casa muito engraçada");
		address.setNumber(0);
		address.setState(Address.State.SP);
		address.setStreet("Rua dos bobos");
		address.setType(Address.Type.SHIPPING);
		address.setZip("14400-000");
		address.setCountry(Address.Country.BRA);
	}

	private void configureTransaction(Transaction transaction) {
		transaction.setReference(testReference);
	}

	private void configureTransactionWithCreditCard(Transaction transaction) {
		final Holder holder = transaction.holder();

		configureTransaction(transaction);
		transaction.setPaymentMethod(Transaction.PaymentMethod.CARTAO_AMEX);
		transaction.setNumber("378282246310005"); // Número de cartão de teste
		transaction.setExpiration("12/2016");
		transaction.setSecurityNumber("1234");
		transaction.setInstallments(2);

		holder.setDocument("33719525929"); // CPF de teste
		holder.setPhone("9999999999");
		holder.setName("Fulano de Tal");
	}

	/**
	 * Cria uma instância de {@link Akatus} sem definir o ambiente de execução e
	 * testa se o ambiente de execução padrão é definido como
	 * {@link Akatus.Environment}.PRODUCTION
	 */
	@Test
	public void testConstructorWithoutEnvironment() {
		final Akatus akatus = new Akatus(testEmail, testApiKey);

		Assert.assertEquals(Akatus.Environment.PRODUCTION,
				akatus.getEnvironment());

		Assert.assertEquals(Akatus.Environment.PRODUCTION.getHost(),
				akatus.getHost());
	}

	/**
	 * Cria uma instância de {@link Akatus} definindo os ambientes de execução
	 * possíveis e testa se o ambiente foi realmente definido.
	 */
	@Test
	public void testConstructorWithEnvironment() {
		Akatus akatus;

		akatus = new Akatus(Akatus.Environment.PRODUCTION, testEmail,
				testApiKey);

		Assert.assertEquals(Akatus.Environment.PRODUCTION,
				akatus.getEnvironment());

		Assert.assertEquals(Akatus.Environment.PRODUCTION.getHost(),
				akatus.getHost());

		akatus = new Akatus(Akatus.Environment.DEVELOPMENT, testEmail,
				testApiKey);

		Assert.assertEquals(Akatus.Environment.DEVELOPMENT,
				akatus.getEnvironment());

		Assert.assertEquals(Akatus.Environment.DEVELOPMENT.getHost(),
				akatus.getHost());
	}

	/**
	 * Testa o envio de um carrinho utilizando boleto como forma de pagamento.
	 * 
	 * @throws MalformedURLException
	 */
	@Test
	public void testCart() throws MalformedURLException {
		final CartOperation cart = new Akatus(Akatus.Environment.DEVELOPMENT,
				testEmail, testApiKey).cart();

		configurePayerWithAddress(cart.payer());
		configureTransaction(cart.getTransaction());

		cart.addProduct("UFC1403", "Luva de box com ferradura dentro", 148.99,
				8.5, 1, 16.45, 10.00);

		cart.addProduct("UFC1403", "Luva de box com ferradura dentro", 148.99,
				8.5, 1, 16.45, 10.00);

		final CartResponse cartResponse = (CartResponse) cart.execute();

		Assert.assertFalse(cartResponse.getStatus().equals("erro"));
		Assert.assertNull(cartResponse.getDescription());

		new URL(cartResponse.getReturnURL());
	}

	/**
	 * Testa o envio de um carrinho utilizando boleto como forma de pagamento,
	 * sem enviar o endereço do pagador.
	 * 
	 * @throws MalformedURLException
	 */
	@Test
	public void testCartWithoutPayerAddress() throws MalformedURLException {
		final CartOperation cart = new Akatus(Akatus.Environment.DEVELOPMENT,
				testEmail, testApiKey).cart();

		configurePayerWithoutAddress(cart.payer());
		configureTransaction(cart.getTransaction());

		cart.addProduct("UFC1403", "Luva de box com ferradura dentro", 148.99,
				8.5, 1, 16.45, 10.00);

		cart.addProduct("UFC1403", "Luva de box com ferradura dentro", 148.99,
				8.5, 1, 16.45, 10.00);

		final CartResponse cartResponse = (CartResponse) cart.execute();

		Assert.assertFalse(cartResponse.getStatus().equals("erro"));
		Assert.assertNull(cartResponse.getDescription());

		new URL(cartResponse.getReturnURL());
	}

	/**
	 * Testa o envio de um carrinho utilizando cartão de crédito como forma de
	 * pagamento.
	 */
	@Test
	public void testCartWithCreditCard() {
		final CartOperation cart = new Akatus(Akatus.Environment.DEVELOPMENT,
				testEmail, testApiKey).cart();

		configurePayerWithAddress(cart.payer());
		configureTransactionWithCreditCard(cart.getTransaction());

		cart.addProduct("UFC1403", "Luva de box com ferradura dentro", 148.99,
				8.5, 1, 16.45, 10.00);

		cart.addProduct("UFC1403", "Luva de box com ferradura dentro", 148.99,
				8.5, 1, 16.45, 10.00);

		final CartResponse cartResponse = (CartResponse) cart.execute();

		Assert.assertFalse(cartResponse.getStatus().equals("erro"));
		Assert.assertNull(cartResponse.getDescription());
		Assert.assertEquals(testReference, cartResponse.getReference());
	}

	/**
	 * Testa o envio de um carrinho utilizando cartão de crédito como forma de
	 * pagamento, sem enviar o endereço do pagador.
	 */
	@Test
	public void testCartWithCreditCardWithoutPayerAddress() {
		final CartOperation cart = new Akatus(Akatus.Environment.DEVELOPMENT,
				testEmail, testApiKey).cart();

		configurePayerWithoutAddress(cart.payer());
		configureTransactionWithCreditCard(cart.getTransaction());

		cart.addProduct("UFC1403", "Luva de box com ferradura dentro", 148.99,
				8.5, 1, 16.45, 10.00);

		cart.addProduct("UFC1403", "Luva de box com ferradura dentro", 148.99,
				8.5, 1, 16.45, 10.00);

		final CartResponse cartResponse = (CartResponse) cart.execute();

		Assert.assertFalse(cartResponse.getStatus().equals("erro"));
		Assert.assertNull(cartResponse.getDescription());
		Assert.assertEquals(testReference, cartResponse.getReference());
	}
}