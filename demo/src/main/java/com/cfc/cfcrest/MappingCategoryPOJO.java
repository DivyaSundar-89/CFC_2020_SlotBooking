package com.cfc.cfcrest;

public class MappingCategoryPOJO {

	private String _id;
	private String donorName;
	private String donorAddress;
	private String donorEmail;
	private String donorPhone;
	private String donorNotes;
	private String donorTime;
	private String category;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public String getDonorAddress() {
		return donorAddress;
	}

	public void setDonorAddress(String donorAddress) {
		this.donorAddress = donorAddress;
	}

	public String getDonorEmail() {
		return donorEmail;
	}

	public void setDonorEmail(String donorEmail) {
		this.donorEmail = donorEmail;
	}

	public String getDonorPhone() {
		return donorPhone;
	}

	public void setDonorPhone(String donorPhone) {
		this.donorPhone = donorPhone;
	}

	public String getDonorNotes() {
		return donorNotes;
	}

	public void setDonorNotes(String donorNotes) {
		this.donorNotes = donorNotes;
	}

	public String getDonorTime() {
		return donorTime;
	}

	public void setDonorTime(String donorTime) {
		this.donorTime = donorTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	private String recipientName;
}
