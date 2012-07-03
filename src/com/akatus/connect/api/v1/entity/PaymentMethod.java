package com.akatus.connect.api.v1.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PaymentMethod implements Entity {
	public class Flag {
		private String code;
		private String description;
		private Integer installments;

		public String getCode() {
			return code;
		}

		public String getDescription() {
			return description;
		}

		public Integer getInstallments() {
			return installments;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public void setInstallments(Integer installments) {
			this.installments = installments;
		}
	}

	private String code;
	private String description;
	private List<PaymentMethod.Flag> flags = new ArrayList<PaymentMethod.Flag>();
	private Integer installments;

	public void addPaymentMethodFlag(String code, String description,
			Integer installments) {
		final PaymentMethod.Flag flag = new Flag();
		flag.setCode(code);
		flag.setDescription(description);
		flag.setInstallments(installments);

		flags.add(flag);
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public Iterator<PaymentMethod.Flag> getFlagIterator() {
		return flags.iterator();
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFlags(List<PaymentMethod.Flag> flags) {
		this.flags = flags;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}
}