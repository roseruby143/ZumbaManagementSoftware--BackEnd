/**
 * 
 */
package com.zumbaSM.model;


/**
 * @author rubydev
 *
 */
public class Participants {
	
	private int participanId;
	private String participantName;
	private String email;
	private String street;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private Long phone;
	private int batchId;
	
	// default constructor
	public Participants() {
		super();
	}

	// parametrized constructor
	public Participants(int participanId, String participantName, String email, String street, String city,
			String state, String country, int pincode, Long phone, int batchId) {
		super();
		this.participanId = participanId;
		this.participantName = participantName;
		this.email = email;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.phone = phone;
		this.batchId = batchId;
	}

	//getter and setter methods
	public int getParticipanId() {
		return participanId;
	}

	public void setParticipanId(int participanId) {
		this.participanId = participanId;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	//override to string
	@Override
	public String toString() {
		return "Participants [participanId=" + participanId + ", participantName=" + participantName + ", email="
				+ email + ", street=" + street + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", pincode=" + pincode + ", phone=" + phone + ", batch=" + batchId + "]";
	}
	
	
	
	
	

}
