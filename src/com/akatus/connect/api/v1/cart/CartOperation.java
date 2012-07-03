/**
 * 
 */
package com.akatus.connect.api.v1.cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.akatus.connect.api.Akatus;
import com.akatus.connect.api.AkatusOperation;
import com.akatus.connect.api.AkatusRequestBuilder;
import com.akatus.connect.api.AkatusResponseBuilder;
import com.akatus.connect.api.v1.entity.Payer;
import com.akatus.connect.api.v1.entity.Product;
import com.akatus.connect.api.v1.entity.Transaction;

/**
 * Implementação da operação carrinho da API da Akatus.
 * 
 * @author João Batista Neto
 */
public class CartOperation extends AkatusOperation {
	private Payer payer;
	private final List<Product> products = new ArrayList<Product>();
	private final Transaction transaction = new Transaction();

	public CartOperation(Akatus akatus) {
		super(akatus);
	}

	/**
	 * Adiciona um produto ao carrinho.
	 * 
	 * @param Uma
	 *            instância de {@link Product} que será adicionada ao carrinho.
	 */
	public void addProduct(Product product) {
		products.add(product);
	}

	/**
	 * Adiciona um novo produto ao carrinho.
	 * 
	 * @param code
	 *            é uma {@link String} que representa o código do produto.
	 * @param description
	 *            é uma {@link String} que representa a descrição do produto.
	 * @param price
	 *            é um {@link Double} o preço do produto.
	 * @param weight
	 *            é um {@link Double} o peso do produto.
	 * @return A instância de {@link Product} que foi adicionada ao carrinho.
	 */
	public Product addProduct(String code, String description, Double price,
			Double weight) {

		final Product product = new Product();

		product.setCode(code);
		product.setDescription(description);
		product.setPrice(price);
		product.setWeight(weight);

		addProduct(product);

		return product;
	}

	/**
	 * Adiciona um novo produto ao carrinho.
	 * 
	 * @param code
	 *            é uma {@link String} que representa o código do produto.
	 * @param description
	 *            é uma {@link String} que representa a descrição do produto.
	 * @param price
	 *            é um {@link Double} com o preço do produto.
	 * @param weight
	 *            é um {@link Double} com o peso do produto.
	 * @param quantity
	 *            é um {@link Integer} com a quantidade comprada pelo cliente.
	 * @return A instância de {@link Product} que foi adicionada ao carrinho.
	 */
	public Product addProduct(String code, String description, Double price,
			Double weight, Integer quantity) {

		final Product product = addProduct(code, description, price, weight);
		product.setQuantity(quantity);

		return product;
	}

	/**
	 * Adiciona um novo produto ao carrinho.
	 * 
	 * @param code
	 *            é uma {@link String} que representa o código do produto.
	 * @param description
	 *            é uma {@link String} que representa a descrição do produto.
	 * @param price
	 *            é um {@link Double} com o preço do produto.
	 * @param weight
	 *            é um {@link Double} com o peso do produto.
	 * @param quantity
	 *            é um {@link Integer} com a quantidade comprada pelo cliente.
	 * @param shipping
	 *            é um {@link Double} com o preço do frete do produto.
	 * @return A instância de {@link Product} que foi adicionada ao carrinho.
	 */
	public Product addProduct(String code, String description, Double price,
			Double weight, Integer quantity, Double shipping) {

		final Product product = addProduct(code, description, price, weight,
				quantity);

		product.setShipping(shipping);

		return product;
	}

	/**
	 * Adiciona um novo produto ao carrinho.
	 * 
	 * @param code
	 *            é uma {@link String} que representa o código do produto.
	 * @param description
	 *            é uma {@link String} que representa a descrição do produto.
	 * @param price
	 *            é um {@link Double} com o preço do produto.
	 * @param weight
	 *            é um {@link Double} com o peso do produto.
	 * @param quantity
	 *            é um {@link Integer} com a quantidade comprada pelo cliente.
	 * @param shipping
	 *            é um {@link Double} com o preço do frete do produto.
	 * @param discount
	 *            é um {@link Double} com um desconto para o produto.
	 * @return A instância de {@link Product} que foi adicionada ao carrinho.
	 */
	public Product addProduct(String code, String description, Double price,
			Double weight, Integer quantity, Double shipping, Double discount) {

		final Product product = addProduct(code, description, price, weight,
				quantity, shipping);

		product.setDiscount(discount);

		return product;
	}

	/**
	 * @return Uma instância de {@link AkatusRequestBuilder} para criação do
	 *         corpo da requisição.
	 */
	@Override
	protected AkatusRequestBuilder createDefaultRequestBuilder() {
		return new CartXMLRequestBuilder();
	}

	/**
	 * @return Uma instância de {@link AkatusResponseBuilder} para criação do
	 *         objeto de resposta baseado na resposta da operação.
	 */
	@Override
	protected AkatusResponseBuilder createDefaultResponseBuilder() {
		return new CartXMLResponseReader();
	}

	/**
	 * @return Recupera uma {@link String} com o caminho da operação.
	 */
	@Override
	public String getPath() {
		final StringBuilder pathBuilder = new StringBuilder();

		pathBuilder.append("/api/");
		pathBuilder.append(getVersion());
		pathBuilder.append("/carrinho.xml");

		return pathBuilder.toString();
	}

	/**
	 * @return Uma instância de {@link Payer}, que representa o pagador.
	 */
	public Payer getPayer() {
		return payer;
	}

	/**
	 * @return Um {@link Iterator} com os produtos do carrinho.
	 */
	public Iterator<Product> getProductIterator() {
		return products.iterator();
	}

	/**
	 * Esse método recupera o objeto {@link Transaction} e já calcula os valores
	 * do carrinho.
	 * 
	 * @return A instância de {@link Transaction}
	 */
	public Transaction getTransaction() {
		Double discountAmount = 0.0;
		Double weight = 0.0;
		Double shippingAmount = 0.0;

		for (final Iterator<Product> productIterator = getProductIterator(); productIterator
				.hasNext();) {
			final Product product = productIterator.next();

			discountAmount += product.getDiscount();
			weight += product.getWeight();
			shippingAmount += product.getShipping();
		}

		transaction.setDiscountAmount(discountAmount);
		transaction.setWeight(weight);
		transaction.setShippingAmount(shippingAmount);

		return transaction;
	}

	/**
	 * Recupera, criando se não existir, uma instância de {@link Payer}
	 * 
	 * @return A instância de {@link Payer}
	 */
	public Payer payer() {
		if (payer == null) {
			setPayer(new Payer());
		}

		return getPayer();
	}

	/**
	 * @param payer
	 *            é a instância de {@link Payer} com os dados do cliente.
	 */
	public void setPayer(Payer payer) {
		this.payer = payer;
	}
}