package com.cplat.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cplat.dao.TableData;

@Path("/DataController")
public class DataController {
	//@Path("{graphData}")
	@GET
	@Produces("application/json")
	public Response generateJson(){
		TableData data = new TableData();
		List<?>  list =  data.getData();
		//company.genJson(list, request, response);
		JSONArray jsonArray = new JSONArray(list);
		JSONArray jsonArray2 = new JSONArray();
		
		for(int i=0; i<list.size(); i++){
			int customerID = jsonArray.getJSONObject(i).getInt("customerID");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("CustomerID",customerID);
			jsonArray2.put(jsonObject);
		}
		
		return Response.status(200).entity(jsonArray2.toString()).build();
	}
}
