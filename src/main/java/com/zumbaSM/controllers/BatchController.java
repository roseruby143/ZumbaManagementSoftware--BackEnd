package com.zumbaSM.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zumbaSM.dao.BatchDAO;
import com.zumbaSM.model.Response;
import com.zumbaSM.model.ZumbaBatch;

/**
 * Servlet implementation class BatchController
 */
@WebServlet("/batches")
public class BatchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BatchDAO batchDaoObject = new BatchDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BatchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response responseObj = new Response();
		String id = request.getParameter("batchId"); 
		  //List<Participants> participantsList = participantDao.getAll(); 
		  String jsonResponse = null; 
		  try { 
			  if (id != null && Integer.parseInt(id)!=0) { 
				  ZumbaBatch batch = batchDaoObject.getOne(Integer.parseInt(id)); 
				  jsonResponse = new Gson().toJson(batch); 
			  } else { 
				  List<ZumbaBatch> batchList = batchDaoObject.getAll(); 
				  jsonResponse = new Gson().toJson(batchList);
			  } 
			  PrintWriter out = response.getWriter();
			  response.setContentType("application/json");
			  response.setCharacterEncoding("UTF-8"); 
			  out.print(jsonResponse); 
			  out.flush();
			  responseObj.setData(jsonResponse);
			  responseObj.setMessage("Received batch data from DB");
			  responseObj.setStatus("Success");
		  } catch (Exception e) { 
			  System.out.println("Inside Batch Controller - method doGet() -- " + e.getMessage()); 
			  responseObj.setMessage("Error while fetching batch data.");
			  responseObj.setStatus("Failed");
		  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response responseDto = new Response();
		try {
			// map Batch object fields with request parameters
			ZumbaBatch batch = new ZumbaBatch();
			batch.setBatchHours(request.getParameter("batchHours"));
			batch.setBatchId(0);
			batch.setBatchMaxParticipant(Integer.parseInt(request.getParameter("batchMaxParticipant")));
			batch.setBatchName(request.getParameter("batchName"));
			batch.setNoOfParticipant(Integer.parseInt(request.getParameter("noOfParticipant")));
			batch.setRoomNo(Integer.parseInt(request.getParameter("roomNo")));
			batch.setZumbaTeacher(request.getParameter("zumbaTeacher"));

			int noOfRowsSaved = batchDaoObject.save(batch);
			responseDto.setStatus((noOfRowsSaved > 0) ? "Success" : "Failed");
			responseDto.setMessage((noOfRowsSaved > 0) ? "Batch object saved successfully" : "Error while saving Batch data");
			responseDto.setData(batch);
		} catch (Exception e) {
			responseDto.setStatus("Failed");
			responseDto.setMessage("Error while creating Batch data");
			System.out.println("Inside Batch Controller - method doePost() -- "+e.getMessage());
		}
		String jsonResponse = new Gson().toJson(responseDto);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonResponse);
		out.flush();
	}
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response responseDto = new Response();
		try {
			// map Batch object fields with request parameters
			ZumbaBatch batch = new ZumbaBatch();
			batch.setBatchHours(request.getParameter("batchHours"));
			batch.setBatchId(Integer.parseInt(request.getParameter("batchId")));
			batch.setBatchMaxParticipant(Integer.parseInt(request.getParameter("batchMaxParticipant")));
			batch.setBatchName(request.getParameter("batchName"));
			batch.setNoOfParticipant(Integer.parseInt(request.getParameter("noOfParticipant")));
			batch.setRoomNo(Integer.parseInt(request.getParameter("roomNo")));
			batch.setZumbaTeacher(request.getParameter("zumbaTeacher"));

			int noOfRowsSaved = batchDaoObject.update(batch);
			responseDto.setStatus((noOfRowsSaved > 0) ? "Success" : "Failed");
			responseDto.setMessage((noOfRowsSaved > 0) ? "Batch object updated successfully" : "Error while updating Batch data");
			responseDto.setData(batch);
		} catch (Exception e) {
			responseDto.setStatus("Failed");
			responseDto.setMessage("Failed to update batch data");
			System.out.println("Inside Batch Controller - method doPut() -- "+e.getMessage());
		}
		String jsonResponse = new Gson().toJson(responseDto);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonResponse);
		out.flush();
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response responseDto = new Response();
		try {
			String id = request.getParameter("batchId");
			System.out.println("Batch delete operation - batchId = "+id);
			int rowAffected = batchDaoObject.delete(Long.parseLong(id));
			if(rowAffected > 0) {
				responseDto.setStatus("Success");
				responseDto.setMessage("Batch object deleted successfully");
				responseDto.setData(id);
			} else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			responseDto.setStatus("Failed");
			responseDto.setMessage("Failed to delete batch data");
			System.out.println("Inside Batch Controller - method doDelete() -- "+e.getMessage());
		}
		
		String jsonResponse = new Gson().toJson(responseDto);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonResponse);
		out.flush();
	}

}
