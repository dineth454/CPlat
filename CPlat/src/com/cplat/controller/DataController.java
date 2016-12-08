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
		String bindto = "matrix";
		String query = "SELECT Date, PRC, Pseudo_PRC, Turnover FROM cep_simulator";
		String[] yParameters = {"PRC", "Pseudo_PRC", "Turnover"};
		String xParameter = "Date";
		String type = "bar";
		String xtype = "timeseries";
		
		
		TableData data = new TableData();
		List<?>  list =  data.getData(query);
		//company.genJson(list, request, response);
		JSONArray resultArray = new JSONArray(list);
		
		JSONArray jsonArray1 = new JSONArray();
		
		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("bindto",bindto);
		
		JSONObject jsonObject2 = new JSONObject();
		for(int i=0; i< yParameters.length; i++){
			jsonObject2.put(yParameters[i],"array"+i);
		}
		
		JSONArray jsonArray2 = new JSONArray();
		
		for(int i=0; i< yParameters.length; i++){
			JSONArray xValArray = new JSONArray();
			xValArray.put("array"+i);
			
			for(int j=0; j<list.size(); j++){
				String xValue = resultArray.getJSONObject(j).getString(xParameter);
				xValArray.put(xValue);
			}
			jsonArray2.put(xValArray);
		}
		
		for(int i=0; i< yParameters.length; i++){
			JSONArray yValArray = new JSONArray();
			yValArray.put(yParameters[i]);
			
			for(int j=0; j<list.size(); j++){
				double yValue = resultArray.getJSONObject(j).getDouble(yParameters[i]);
				yValArray.put(yValue);
			}
			jsonArray2.put(yValArray);
		}
		
		JSONObject jsonObject3 = new JSONObject();
		jsonObject3.put("type",type);
		
		JSONObject jsonObject4 = new JSONObject();
		jsonObject4.put("xtype",xtype);
		
		jsonArray1.put(jsonObject1);
		jsonArray1.put(jsonObject2);
		jsonArray1.put(jsonArray2);
		jsonArray1.put(jsonObject3);
		jsonArray1.put(jsonObject4);

		
		return Response.status(200).entity(jsonArray1.toString()).build();
	}
}
