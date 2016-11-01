package com.prototype.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ProjectBean {

	private int p_num;
	private String p_name;
	private String p_content;
	private Timestamp p_start;
	private Timestamp p_end;
	private String m_id;
	private Timestamp p_reg_date;
	
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}
	public Timestamp getP_start() {
		return p_start;
	}
	public String getP_start_short() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String start = format.format(p_start);
		if (start.length() > 10) {
			start = start.substring(0, 10);
		}
		return start;
	}
	public void setP_start(Timestamp p_start) {
		this.p_start = p_start;
	}
	public String getP_end_short() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String end = format.format(p_end);
		if (end.length() > 10) {
			end = end.substring(0, 10);
		}
		return end;
	}
	public Timestamp getP_end() {
		return p_end;
	}
	public void setP_end(Timestamp p_end) {
		this.p_end = p_end;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public Timestamp getP_reg_date() {
		return p_reg_date;
	}
	public void setP_reg_date(Timestamp p_reg_date) {
		this.p_reg_date = p_reg_date;
	}
}
