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
import com.zumbaSM.dao.ParticipantsDAO;
import com.zumbaSM.model.Participants;
import com.zumbaSM.model.Response;

/**
 * Servlet implementation class ParticipantController
 */
@WebServlet("/participants")
public class ParticipantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ParticipantsDAO participantDao = new ParticipantsDAO();
	Response responseDto = new Response();
	   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParticipantController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  String id = request.getParameter("participantId"); 
		  String b_id = request.getParameter("batchId"); 
		  //List<Participants> participantsList = participantDao.getAll(); 
		  String jsonResponse = null; 
		  try { 
			  if (id != null && Integer.parseInt(id) > 0) { 
				  Participants participant = participantDao.getOne(Integer.parseInt(id));
				  jsonResponse = new Gson().toJson(participant);
			  } else if(b_id != null && Integer.parseInt(b_id) > 0){
				  List<Participants> participant = participantDao.getParticipantsInBatch(Integer.parseInt(b_id));
				  jsonResponse = new Gson().toJson(participant);
			  }else { 
				  List<Participants> participantsList = participantDao.getAll(); 
				  jsonResponse = new Gson().toJson(participantsList);
			  } 
			  PrintWriter out = response.getWriter();
			  response.setContentType("application/json");
			  response.setCharacterEncoding("UTF-8"); 
			  out.print(jsonResponse); 
			  out.flush();
			  responseDto.setData(jsonResponse);
			  responseDto.setMessage("Received participants data from DB");
			  responseDto.setStatus("Success");
		  } catch (Exception e) { 
			  System.out.println("Inside ParticipantController - method  doGet() -- Error while fetching participants data" + e.getMessage()); 
			  responseDto.setMessage("-- Error while fetching participants data --");
			  responseDto.setStatus("Failed");
		  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response responseDto = new Response();
		try {
			// map participants object fields with request parameters
			Participants participants = new Participants();
			participants.setBatchId(Integer.parseInt(request.getParameter("batchId")));
			participants.setCity(request.getParameter("city"));
			participants.setCountry(request.getParameter("country"));
			participants.setEmail(request.getParameter("email"));
			participants.setParticipanId(0);
			participants.setParticipantName(request.getParameter("participantName"));
			participants.setPhone(Long.parseLong(request.getParameter("phone")));
			participants.setPincode(Integer.parseInt(request.getParameter("pincode")));
			participants.setState(request.getParameter("state"));
			participants.setStreet(request.getParameter("street"));

			int noOfRowsSaved = participantDao.save(participants);
			responseDto.setStatus((noOfRowsSaved > 0) ? "Success" : "Failed");
			responseDto.setMessage((noOfRowsSaved > 0) ? "Participant object saved successfully" : "Error while saving participants data");
			responseDto.setData(participants);
		} catch (Exception e) {
			responseDto.setStatus("Failed");
			responseDto.setMessage("Something Went Wrong , Failed to create participant data");
			System.out.println("Inside ParticipantController - method  doPost() -- Error while saving participants data" + e.getMessage());
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
			// map participants object fields with request parameters
			Participants participants = new Participants();
			participants.setParticipanId(Integer.parseInt(request.getParameter("participanId")));
			participants.setBatchId(Integer.parseInt(request.getParameter("batchId")));
			participants.setCity(request.getParameter("city"));
			participants.setCountry(request.getParameter("country"));
			participants.setEmail(request.getParameter("email"));
			participants.setParticipantName(request.getParameter("participantName"));
			participants.setPhone(Long.parseLong(request.getParameter("phone")));
			participants.setPincode(Integer.parseInt(request.getParameter("pincode")));
			participants.setState(request.getParameter("state"));
			participants.setStreet(request.getParameter("street"));

			int noOfRowsSaved = participantDao.update(participants);
			responseDto.setStatus((noOfRowsSaved > 0) ? "Success" : "Failed");
			responseDto.setMessage((noOfRowsSaved > 0) ? "Participant object saved successfully" : "Error while saving participants data");
			responseDto.setData(participants);
		} catch (Exception e) {
			e.printStackTrace();
			responseDto.setStatus("Failed");
			responseDto.setMessage("Something Went Wrong , Failed to update participant data");
			System.out.println("Inside ParticipantController - method  doPut() -- Error while updating participants data" + e.getMessage());
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
		// TODO Auto-generated method stub
		try {
			String id = request.getParameter("participanId");
			System.out.println("Participants delete operation - participantId = "+id);
			int rowAffected = participantDao.delete(Long.parseLong(id));
			if(rowAffected > 0) {
				responseDto.setStatus("Success");
				responseDto.setMessage("Participant object deleted successfully");
				responseDto.setData(id);
			} else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			responseDto.setStatus("Failed");
			responseDto.setMessage("Something Went Wrong , Failed to delete participant data");
			System.out.println("Inside ParticipantController - method  doDelete() -- Error while deleting participants data" + e.getMessage());
		}
		
		String jsonResponse = new Gson().toJson(responseDto);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonResponse);
		out.flush();
	}

}
