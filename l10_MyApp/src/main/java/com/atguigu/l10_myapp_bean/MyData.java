package com.atguigu.l10_myapp_bean;

public class MyData {
	private String  qixian;
	private String baifenshu;
	private String day;
	private int image;
	public MyData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyData(String qixian, String baifenshu, String day, int image) {
		super();
		this.qixian = qixian;
		this.baifenshu = baifenshu;
		this.day = day;
		this.image = image;
	}
	public String getQixian() {
		return qixian;
	}
	public void setQixian(String qixian) {
		this.qixian = qixian;
	}
	public String getBaifenshu() {
		return baifenshu;
	}
	public void setBaifenshu(String baifenshu) {
		this.baifenshu = baifenshu;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "MyData [qixian=" + qixian + ", baifenshu=" + baifenshu
				+ ", day=" + day + ", image=" + image + "]";
	}
	
}
