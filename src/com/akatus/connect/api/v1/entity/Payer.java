package com.akatus.connect.api.v1.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Payer implements Entity {
	private String name;
	private String email;
	private final List<Address> addresses = new ArrayList<Address>();
	private final List<Phone> phones = new ArrayList<Phone>();

	public void addAddress(Address address) {
		addresses.add(address);
	}

	public void addPhone(Phone phone) {
		phones.add(phone);
	}

	public Iterator<Address> getAddressIterator() {
		return addresses.iterator();
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public Iterator<Phone> getPhoneIterator() {
		return phones.iterator();
	}

	public Address newAddress() {
		final Address address = new Address();

		addAddress(address);

		return address;
	}

	public Phone newPhone() {
		final Phone phone = new Phone();

		addPhone(phone);

		return phone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}