package com.zumbaSM.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zumbaSM.model.Participants;
import com.zumbaSM.utility.DBConnection;

public class ParticipantsDAO implements DAO<Participants> {

	DBConnection dbConnection = new DBConnection();

	@Override
	public List<Participants> getAll() {
		List<Participants> participantsList = new ArrayList<Participants>();
		dbConnection.init();
		try {
			String sql = "select * from participants";
			ResultSet res = dbConnection.executeQuery(sql);
			while (res.next()) {
				Participants participants = new Participants();
				participants.setBatchId(res.getInt("batchId"));
				participants.setCity(res.getString("city"));
				participants.setCountry(res.getString("country"));
				participants.setEmail(res.getString("email"));
				participants.setParticipanId(res.getInt("participanId"));
				participants.setParticipantName(res.getString("participantName"));
				participants.setPhone(res.getLong("phone"));
				participants.setPincode(res.getInt("pincode"));
				participants.setState(res.getString("state"));
				participants.setStreet(res.getString("street"));
				participantsList.add(participants);
				System.out.println("Returning participantsList is :: " + participantsList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Inside ParticipantsDAO method getAll() ---- Couldn't fetch participants --- " + e.getMessage());
		} finally {
			dbConnection.destroy();
		}
		return participantsList;
	}

	@Override
	public Participants getOne(int id) {
		Participants participants = new Participants();
		dbConnection.init();
		try {
			String sql = "select * from participants where participanId = " + id;
			ResultSet res = dbConnection.executeQuery(sql);
			while (res.next()) {

				participants.setBatchId(res.getInt("batchId"));
				participants.setCity(res.getString("city"));
				participants.setCountry(res.getString("country"));
				participants.setEmail(res.getString("email"));
				participants.setParticipanId(id);
				participants.setParticipantName(res.getString("participantName"));
				participants.setPhone(res.getLong("phone"));
				participants.setPincode(res.getInt("pincode"));
				participants.setState(res.getString("state"));
				participants.setStreet(res.getString("street"));
				System.out.println("Returning participants is :: " + participants);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Inside ParticipantsDAO method getOne() ---- Couldn't fetch participants with id "+ id +" --- " + e.getMessage());
		} finally {
			dbConnection.destroy();
		}
		return participants;
	}

	@Override
	public int save(Participants obj) {
		dbConnection.init();
		try {
			String sql = "Insert into participants (participantName, email, street, city, state, country, pincode, phone, batchId) "
					+ "values ('" + obj.getParticipantName() + "','" + obj.getEmail()
					+ "','" + obj.getStreet() + "','" + obj.getCity() + "','" + obj.getState() + "','"
					+ obj.getCountry() + "'," + obj.getPincode() + ",'" + obj.getPhone() + "', " + obj.getBatchId()
					+ ")";
			System.out.println("Participants Inserting SQL statement is :: " + sql);
			int rowAffected = dbConnection.executeUpdate(sql);
			String message = (rowAffected > 0) ? "Participant record saved successfully"
					: "Unable to save Participant data.";
			System.out.println(message);
			return rowAffected;
		} catch (Exception e) {
			throw new RuntimeException("Inside ParticipantsDAO method save() ---- Couldn't save participants data --- " + e.getMessage());
		} finally {
			dbConnection.destroy();
		}
	}

	@Override
	public int update(Participants obj) {
		dbConnection.init();
		try {
			String sql = "update participants set participantName = '" + obj.getParticipantName() + "', email = '" + obj.getEmail()
					+ "', street = '" + obj.getStreet() + "', city = '" + obj.getCity() + "', state = '" + obj.getState() + "', country = '"
					+ obj.getCountry() + "', pincode = " + obj.getPincode() + ", phone = '" + obj.getPhone() + "', batchId = " + obj.getBatchId()
					+ " where participanId = "+obj.getParticipanId();
			System.out.println("Participants Update SQL statement is :: " + sql);
			int rowAffected = dbConnection.executeUpdate(sql);
			String message = (rowAffected > 0) ? "Participant record saved successfully"
					: "Unable to save Participant data.";
			System.out.println(message);
			return rowAffected;
		} catch (Exception e) {
			throw new RuntimeException("Inside ParticipantsDAO method update() ---- Couldn't update participants --- " + e.getMessage());
		} finally {
			dbConnection.destroy();
		}
	}

	@Override
	public int delete(long id) {
		dbConnection.init();
		try {
			String sql = "delete from participants where participanId  = " + id;
			int rowAffected = dbConnection.executeUpdate(sql);
			String message = (rowAffected > 0) ? "participant record deleted successfully"
					: "Unable to delete participant data.";
			System.out.println(message);
			return rowAffected;
		} catch (Exception e) {
			throw new RuntimeException("Inside method delete() of ParticipantsDAO --- couldn't delete participants :: " + e.getMessage());
		} finally {
			dbConnection.destroy();
		}
	}
	
	public List<Participants> getParticipantsInBatch(long id) {
		List<Participants> participantsList = new ArrayList<Participants>();
		dbConnection.init();
		try {
			String sql = "select * from participants where batchId = "+id;
			ResultSet res = dbConnection.executeQuery(sql);
			while (res.next()) {
				Participants participants = new Participants();
				participants.setBatchId(res.getInt("batchId"));
				participants.setCity(res.getString("city"));
				participants.setCountry(res.getString("country"));
				participants.setEmail(res.getString("email"));
				participants.setParticipanId(res.getInt("participanId"));
				participants.setParticipantName(res.getString("participantName"));
				participants.setPhone(res.getLong("phone"));
				participants.setPincode(res.getInt("pincode"));
				participants.setState(res.getString("state"));
				participants.setStreet(res.getString("street"));
				participantsList.add(participants);
				System.out.println("Returning participantsList is :: " + participantsList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Inside method getParticipantsInBatch() ---- Couldn't fetch participants in batch "+id+" :: " + e.getMessage());
		} finally {
			dbConnection.destroy();
		}
		return participantsList;
	}

}
