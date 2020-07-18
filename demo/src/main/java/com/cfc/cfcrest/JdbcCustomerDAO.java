package com.cfc.cfcrest;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

import distancemapper.GetNearbyDistance;

public class JdbcCustomerDAO {
	private DataSource dataSource;
	private static CloudantClient client;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Vendor getSlot(String customerId) {
		Vendor vendor = new Vendor();
		String sql = "select * from orders where customer_name='" + customerId + "'";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String cust_name = rs.getString(1);
				String vendor_id = rs.getString(2);
				String slot = rs.getString(8);
				vendor.setCust_id(cust_name);
				vendor.setVendor_id(vendor_id);
				vendor.setSlot(slot);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return vendor;
	}

	public List<Vendor> getAllVendors() {
		List<Vendor> vendorList = new ArrayList<Vendor>();
		String sql = "select * from orders";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String cust_name = rs.getString(1);
				String vendor_id = rs.getString(2);
				String slot = rs.getString(8);
				Vendor vendor = new Vendor();
				vendor.set_id("vendor_details_" + cust_name);
				vendor.setCust_id(cust_name);
				vendor.setVendor_id(vendor_id);
				vendor.setSlot(slot);
				vendorList.add(vendor);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return vendorList;
	}

	public void storeDonorIntoCludant(DonorPOJO donorPOJO) {
		try {
			client = ClientBuilder.url(new URL(
					"https://41853c84-7969-4a90-8330-a577d695bc23-bluemix:ea2666a301e790ec11f6386683cd9f3f3e3122811cbb3fa071b7e6cc51d7509b@41853c84-7969-4a90-8330-a577d695bc23-bluemix.cloudantnosqldb.appdomain.cloud"))
					.username("41853c84-7969-4a90-8330-a577d695bc23-bluemix")
					.password("ea2666a301e790ec11f6386683cd9f3f3e3122811cbb3fa071b7e6cc51d7509b").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		client.database("donor", true).post(donorPOJO);
	}

	public void storeRecipientIntoCludant(RecipientPOJO recipientPOJO) {
		try {
			client = ClientBuilder.url(new URL(
					"https://41853c84-7969-4a90-8330-a577d695bc23-bluemix:ea2666a301e790ec11f6386683cd9f3f3e3122811cbb3fa071b7e6cc51d7509b@41853c84-7969-4a90-8330-a577d695bc23-bluemix.cloudantnosqldb.appdomain.cloud"))
					.username("41853c84-7969-4a90-8330-a577d695bc23-bluemix")
					.password("ea2666a301e790ec11f6386683cd9f3f3e3122811cbb3fa071b7e6cc51d7509b").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		client.database("recipient", true).post(recipientPOJO);
		calculateNearestDonor(recipientPOJO);
	}

	public void calculateNearestDonor(RecipientPOJO recipientPOJO) {
		try {
			client = ClientBuilder.url(new URL(
					"https://41853c84-7969-4a90-8330-a577d695bc23-bluemix:ea2666a301e790ec11f6386683cd9f3f3e3122811cbb3fa071b7e6cc51d7509b@41853c84-7969-4a90-8330-a577d695bc23-bluemix.cloudantnosqldb.appdomain.cloud"))
					.username("41853c84-7969-4a90-8330-a577d695bc23-bluemix")
					.password("ea2666a301e790ec11f6386683cd9f3f3e3122811cbb3fa071b7e6cc51d7509b").build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String recipientAddress = recipientPOJO.getAddress();
		List<String> addressList = new ArrayList<String>();
		Map<String, DonorPOJO> donorAddressMapping = new HashMap<String, DonorPOJO>();
		Database db = client.database("donor", false);
		try {
			List<DonorPOJO> donorPOJOList = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse()
					.getDocsAs(DonorPOJO.class);
			for (DonorPOJO donorPOJO : donorPOJOList) {
				addressList.add(donorPOJO.getAddress());
				donorAddressMapping.put(donorPOJO.getAddress(), donorPOJO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		addressList.add(recipientAddress);
		System.out.println("donor address mapping" + donorAddressMapping);
		GetNearbyDistance distance = new GetNearbyDistance();
		Map<String, Map<String, Double>> addressMap = distance.getLocationDetails(addressList);
		System.out.println("addressmap" + addressMap);
		Map<String, Double> innerMap = addressMap.get(recipientAddress);
		for (Map.Entry<String, Double> iMap : innerMap.entrySet()) {
			System.out.println(iMap.getKey() + " = " + iMap.getValue());
			String recipientCategory = recipientPOJO.getCategory();
			String donorCategory = donorAddressMapping.get(iMap.getKey()).getCategory();
			if (recipientCategory.equals(donorCategory)
					|| (recipientCategory.contains("Amount") && donorCategory.contains("amount"))) {

				DonorPOJO dPOJO = donorAddressMapping.get(iMap.getKey());
				System.out.println("matching category of closest donor and recipient");
				MappingCategoryPOJO pojo = new MappingCategoryPOJO();
				pojo.set_id("mapping_" + recipientPOJO.getName());
				pojo.setCategory(recipientPOJO.getCategory());
				pojo.setDonorAddress(dPOJO.getAddress());
				pojo.setRecipientName(recipientPOJO.getName());
				pojo.setDonorEmail(dPOJO.getEmail());
				pojo.setDonorName(dPOJO.getName());
				pojo.setDonorNotes(dPOJO.getDonornotes());
				pojo.setDonorPhone(dPOJO.getPhone());
				pojo.setDonorTime(dPOJO.getTime());
				System.out.println("donor details" + dPOJO.getName() + "donor address" + dPOJO.getAddress());
				client.database("mapping", true).post(pojo);
			}
		}
	}

}