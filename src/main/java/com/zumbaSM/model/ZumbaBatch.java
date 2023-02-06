/**
 * 
 */
package com.zumbaSM.model;

/**
 * @author rubydev
 *
 */
public class ZumbaBatch {

	private int batchId;
	private String batchName;
	private String batchHours;
	private int batchMaxParticipant;
	private int noOfParticipant;
	private int roomNo;
	private String zumbaTeacher;
	
	
	//default constructor
	public ZumbaBatch() {
		super();
		// TODO Auto-generated constructor stub
	}

	//parametrized constructor
	public ZumbaBatch(int batchId, String batchName, String batchHours, int batchMaxParticipant, int noOfParticipant, int roomNo, String zumbaTeacher) {
		super();
		this.batchId = batchId;
		this.batchName = batchName;
		this.batchHours = batchHours;
		this.batchMaxParticipant = batchMaxParticipant;
		this.noOfParticipant = noOfParticipant;
		this.roomNo = roomNo;
		this.zumbaTeacher = zumbaTeacher;
		
	}

	//Getter and Setter methods
	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getBatchHours() {
		return batchHours;
	}

	public void setBatchHours(String batchHours) {
		this.batchHours = batchHours;
	}

	public int getBatchMaxParticipant() {
		return batchMaxParticipant;
	}

	public void setBatchMaxParticipant(int batchMaxParticipant) {
		this.batchMaxParticipant = batchMaxParticipant;
	}

	public int getNoOfParticipant() {
		return noOfParticipant;
	}

	public void setNoOfParticipant(int noOfParticipant) {
		this.noOfParticipant = noOfParticipant;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getZumbaTeacher() {
		return zumbaTeacher;
	}

	public void setZumbaTeacher(String zumbaTeacher) {
		this.zumbaTeacher = zumbaTeacher;
	}

	@Override
	public String toString() {
		return "ZumbaBatch [batchId=" + batchId + ", batchName=" + batchName + ", batchHours=" + batchHours
				+ ", batchMaxParticipant=" + batchMaxParticipant + ", noOfParticipant=" + noOfParticipant + ", roomNo="
				+ roomNo + ", zumbaTeacher=" + zumbaTeacher + "]";
	}
	
	
	
	
	
	
}
