package lsf.zuba.vizobj;


public class ChartParameter {
	private String chartType;
	private String bindTo;
	private Axis x;
	private Axis y;
	private String DB_type;
	private String Sql_query;
	
	public String getSql_query() {
		return Sql_query;
	}
	public void setSql_query(String sql_query) {
		Sql_query = sql_query;
	}
	public String getDB_type() {
		return DB_type;
	}
	public void setDB_type(String dB_type) {
		DB_type = dB_type;
	}
	public void setX(Axis x) {
		this.x = x;
	}
	public void setY(Axis y) {
		this.y = y;
	}
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
	public void setX(String type, String label, String data_para) {
		Axis x = new Axis();
		x.setType(type);
		x.setLabel(label);
		x.setData_parameter(data_para);
		this.x = x;
	}
	public Axis getY() {
		return y;
	}
	public void setY(String type, String label, String data_para) {
		Axis y = new Axis();
		y.setType(type);
		y.setLabel(label);
		y.setData_parameter(data_para);
		this.y = y;
	}
	
}
