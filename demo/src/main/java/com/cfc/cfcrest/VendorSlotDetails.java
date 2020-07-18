package com.cfc.cfcrest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class VendorSlotDetails {

	private static VendorSlotDetails stdregd = null;

	private VendorSlotDetails() {

	}

	public static VendorSlotDetails getInstance() {
		if (stdregd == null) {
			stdregd = new VendorSlotDetails();
			return stdregd;
		} else {
			return stdregd;
		}
	}

	public Vendor getSlot(String customerName) {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

		JdbcCustomerDAO customerDAO = (JdbcCustomerDAO) context.getBean("customerDAO");
		Vendor vendor = customerDAO.getSlot(customerName);
		vendor.set_id("vendor_details_" + vendor.getCust_id());

		return vendor;
	}

}