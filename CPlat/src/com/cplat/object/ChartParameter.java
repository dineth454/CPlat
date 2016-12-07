package com.cplat.object;

public class ChartParameter {
	private String chartType;
	private String bindTo;
	private Axis x;
	private Axis y;
	
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	public String getBindTo() {
		return bindTo;
	}
	public void setBindTo(String bindTo) {
		this.bindTo = bindTo;
	}
	public Axis getX() {
		return x;
	}
	public void setX(String type, String label) {
		Axis x = new Axis();
		x.setType(type);
		x.setLabel(label);
		this.x = x;
	}
	public Axis getY() {
		return y;
	}
	public void setY(String type, String label) {
		Axis y = new Axis();
		y.setType(type);
		y.setLabel(label);
		this.y = y;
	}
	
}
