package cn.arp.trend.data.model;

import java.math.BigDecimal;



public class DataModel {
	private String name;
	private Object value;
	private String date;
	private String orgid;

	

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public DataModel(String name, Object value, String date, String orgid) {
		BigDecimal bd=new BigDecimal(value.toString());
		this.name = name;
		this.value = bd.stripTrailingZeros().toPlainString();
		this.date = date.replace(".0","");
		this.orgid = orgid;
	}

	public DataModel(String name, Object value) {
		BigDecimal bd=new BigDecimal(value.toString());
		this.name = name;
		//stripTrailingZeros()不光会去掉小数点后的没用的0，如果你的整数位的末尾数字也是0的话，它会将这个整数变成科学计数法.解决办法是后面加toPlainString
		this.value = bd.stripTrailingZeros().toPlainString();
	}

	public DataModel() {

	}

}
