package lsf.zuba.main;

import java.util.ArrayList;

import lsf.zuba.javacc.SyntaxChecker;
import lsf.zuba.javacc.controle.brain;
import lsf.zuba.vizobj.ChartParameter;

public class zuba_main {
	

	public static void main(String[] args) {			 
			 
		String zuba_query = "DRAW CHART bar TO mydiv FOR {Select a,b from c;} WHICH X:a, Y:b";
		
		brain brain = new brain();
		ChartParameter chart_config = brain.chart_Obj_Produce(zuba_query);
		System.out.println(chart_config.getSql_query());
		System.out.println(chart_config.getBindTo());
		

	}

}
