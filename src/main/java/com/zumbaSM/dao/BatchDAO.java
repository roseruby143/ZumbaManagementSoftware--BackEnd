package com.zumbaSM.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zumbaSM.model.ZumbaBatch;
import com.zumbaSM.utility.DBConnection;

public class BatchDAO implements DAO<ZumbaBatch> {

	DBConnection dbConnection = new DBConnection();

	@Override
	public List<ZumbaBatch> getAll() {
		List<ZumbaBatch> batchList = new ArrayList<ZumbaBatch>();
		dbConnection.init();
		try {
			String sql = "select * from batch";
			ResultSet res = dbConnection.executeQuery(sql);
			while (res.next()) {

				ZumbaBatch batch = new ZumbaBatch();
				batch.setBatchHours(res.getString("batchHours"));
				batch.setBatchId(Integer.parseInt(res.getString("batchId")));
				batch.setBatchMaxParticipant(Integer.parseInt(res.getString("batchMaxParticipant")));
				batch.setBatchName(res.getString("batchName"));
				batch.setNoOfParticipant(Integer.parseInt(res.getString("noOfParticipant")));
				batch.setRoomNo(Integer.parseInt(res.getString("roomNo")));
				batch.setZumbaTeacher(res.getString("zumbaTeacher"));
				
				batchList.add(batch);
				System.out.println("--- Returning batchList is :: " + batchList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Inside BatchDAO method getAll() --- " + e.getMessage());
		} finally {
			dbConnection.destroy();
		}
		return batchList;
	}

	@Override
	public ZumbaBatch getOne(int id) {
		ZumbaBatch batch = new ZumbaBatch();
		dbConnection.init();
		try {
			String sql = "select * from batch where batchId = "+id;
			ResultSet res = dbConnection.executeQuery(sql);
			while (res.next()) {
				batch.setBatchHours(res.getString("batchHours"));
				batch.setBatchId(Integer.parseInt(res.getString("batchId")));
				batch.setBatchMaxParticipant(Integer.parseInt(res.getString("batchMaxParticipant")));
				batch.setBatchName(res.getString("batchName"));
				batch.setNoOfParticipant(Integer.parseInt(res.getString("noOfParticipant")));
				batch.setRoomNo(Integer.parseInt(res.getString("roomNo")));
				batch.setZumbaTeacher(res.getString("zumbaTeacher"));
				
				System.out.println("--- Returning batch is :: " + batch);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Inside BatchDAO method getOne() for id "+id+" --- " + e.getMessage());
		} finally {
			dbConnection.destroy();
		}
		return batch;
	}

	@Override
	public int save(ZumbaBatch obj) {
		dbConnection.init();
		try {
			String sql = "Insert into batch (batchName ,batchHours, batchMaxParticipant, noOfParticipant, roomNo, zumbaTeacher) values"
					+ " ('" + obj.getBatchName() + "', '" + obj.getBatchHours() + "','" + obj.getBatchMaxParticipant()
					+ "','" + obj.getNoOfParticipant() + "','" + obj.getRoomNo() + "','" + obj.getZumbaTeacher() + "')";
			System.out.println("Participants Inserting SQL statement is :: " + sql);
			int rowAffected = dbConnection.executeUpdate(sql);
			String message = (rowAffected > 0) ? "Batch record saved successfully"
					: "Unable to save Batch data.";
			System.out.println(message);
			return rowAffected;
		} catch (Exception e) {
			throw new RuntimeException("Inside BatchDAO method save() --- " + e.getMessage());
		} finally {
			dbConnection.destroy();
		}
	}

	@Override
	public int update(ZumbaBatch obj) {
		dbConnection.init();
		try {
			String sql = "update batch set batchName = '" + obj.getBatchName() + "', batchHours = '" + obj.getBatchHours()
			+ "', batchMaxParticipant = " + obj.getBatchMaxParticipant() + ", noOfParticipant = " + obj.getNoOfParticipant() + 
			", roomNo = " + obj.getRoomNo() + ", zumbaTeacher = '"
			+ obj.getZumbaTeacher() + "' where batchId = "+obj.getBatchId();
			System.out.println("Batch Updating SQL statement is :: " + sql);
			int rowAffected = dbConnection.executeUpdate(sql);
			String message = (rowAffected > 0) ? "Batch record updated successfully"
					: "Unable to update Batch data.";
			System.out.println(message);
			return rowAffected;
		} catch (Exception e) {
			throw new RuntimeException("Inside BatchDAO method update() --- " + e.getMessage());
		} finally {
			dbConnection.destroy();
		}
			
	}

	@Override
	public int delete(long id) {
		dbConnection.init();
		try {
			String sql = "delete from batch where batchId  = " + id;
			int rowAffected = dbConnection.executeUpdate(sql);
			String message = (rowAffected > 0) ? "batch record deleted successfully"
					: "Unable to delete batch data.";
			System.out.println(message);
			return rowAffected;
		} catch (Exception e) {
			throw new RuntimeException("Inside method delete() of ParticipantsDAO --- couldn't delete participants :: " + e.getMessage());
		} finally {
			dbConnection.destroy();
		}
	}
}
