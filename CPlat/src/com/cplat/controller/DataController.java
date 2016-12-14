package com.cplat.controller;

import java.util.List;
import java.util.ArrayList;

import lsf.zuba.javacc.SyntaxChecker;
import lsf.zuba.javacc.controle.brain;
import lsf.zuba.vizobj.ChartParameter;
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
	@Path("{query}")
	@GET
	@Produces("application/json")
	public Response generateJson(@PathParam("query") String zuba_query){
		//String bindto = "matrix";
		//String query = "SELECT Date, PRC, Pseudo_PRC, Turnover FROM cep_simulator";
		String[] yParameters = {"PRC", "Pseudo_PRC", "Turnover"};
		String xParameter = "Date";
		//String type = "line";
		String xtype = "timeseries";
		
		//String zuba_query = "DRAW CHART bar TO matrix FOR {SELECT Date, PRC, Pseudo_PRC, Turnover FROM cep_simulator;} WHICH X:a, Y:b";
		
		System.out.println(zuba_query);
		
		brain brain = new brain();
		ChartParameter chart_config = brain.chart_Obj_Produce(zuba_query);
		
		String query = chart_config.getSql_query();
            
          //get related value to a jsonArray
    		TableData data = new TableData();
    		List<?>  list =  data.getData(query);
    		JSONArray resultArray = new JSONArray(list);
    		
    		//creat json strcture
    		JSONArray jsonArray1 = new JSONArray();
    		
    		JSONObject jsonObject1 = new JSONObject();
    		jsonObject1.put("bindto",chart_config.getBindTo());
    		
    		//create json object for xs
    		JSONObject jsonObject2 = new JSONObject();
    		for(int i=0; i< yParameters.length; i++){
    			jsonObject2.put(yParameters[i],"array"+i);
    		}
    		
    		//create json arrays for data columns
    			//value of x parameters
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
    		
    			//value of y parameters
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
    		jsonObject3.put("type",chart_config.getChartType());
    		
    		JSONObject jsonObject4 = new JSONObject();
    		jsonObject4.put("xtype",xtype);
    		
    		//put objects into main json array
    		jsonArray1.put(jsonObject1);
    		jsonArray1.put(jsonObject2);
    		jsonArray1.put(jsonArray2);
    		jsonArray1.put(jsonObject3);
    		jsonArray1.put(jsonObject4);
    		
    		return Response.status(200).entity(jsonArray1.toString()).build();
    		
    		//return Response.status(200).entity("fuck").build();
		
		//return Response.status(200).entity("").build();
	}
}
