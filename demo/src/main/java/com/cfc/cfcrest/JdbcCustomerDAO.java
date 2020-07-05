package com.cfc.cfcrest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class JdbcCustomerDAO {
	private DataSource dataSource;

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

}