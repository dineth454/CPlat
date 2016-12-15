package lsf.zuba.javacc.controle;

import lsf.zuba.javacc.SyntaxChecker;
import lsf.zuba.vizobj.ChartParameter;

public class brain {
	
	public ChartParameter chart_Obj_Produce(String zuba_query) {
		
		ChartParameter chart_obj = new ChartParameter();
		
		 try {
	            chart_obj = new SyntaxChecker(new java.io.StringReader(zuba_query)).Visualization();
	            
	            String Sql = zuba_query.substring(zuba_query.indexOf("{")+1,zuba_query.indexOf("}"));
	            chart_obj.setSql_query(Sql);
	            
	            System.out.println("Syntax is okay");
	            //System.out.println("result : "+chart_obj.getBindTo());
	            
	            
	           // System.out.println("SQL: "+chart_obj.getSql_query());
	        } catch (Throwable e) {
	            // Catching Throwable is ugly but JavaCC throws Error objects!
	            System.out.println("Syntax check failed: " + e.getMessage());
	        }
		
		return chart_obj;
	}
}
