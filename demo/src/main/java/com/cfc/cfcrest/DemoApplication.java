package com.cfc.cfcrest;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;

@SpringBootApplication
public class DemoApplication {

	private static CloudantClient client;

	public static void main(String[] args) {
		try {
			client = ClientBuilder.url(new URL(
					"https://41853c84-7969-4a90-8330-a577d695bc23-bluemix:ea2666a301e790ec11f6386683cd9f3f3e3122811cbb3fa071b7e6cc51d7509b@41853c84-7969-4a90-8330-a577d695bc23-bluemix.cloudantnosqldb.appdomain.cloud"))
					.username("41853c84-7969-4a90-8330-a577d695bc23-bluemix")
					.password("ea2666a301e790ec11f6386683cd9f3f3e3122811cbb3fa071b7e6cc51d7509b").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> listDb = new ArrayList<String>();
		try {
			listDb = client.getAllDbs();
			System.out.println("list db's" + listDb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

		JdbcCustomerDAO customerDAO = (JdbcCustomerDAO) context.getBean("customerDAO");
		client.deleteDB("cus");

		List<Vendor> vendorList = customerDAO.getAllVendors();
		for (Vendor vendor : vendorList) {
			client.database("cus", true).post(vendor);
		}
		SpringApplication.run(DemoApplication.class, args);
	}

}
