package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products {
	
	

	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pid;
	
	@Column
	private String pname;
	
	@Column
	private float price;
	
	@Column
	private byte[] pic_byte;
	
	@Column
	private String desc;

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Products(int pid, String pname, float price, byte[] pic_byte, String desc) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.price = price;
		this.pic_byte = pic_byte;
		this.desc = desc;
	}
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public byte[] getPic_byte() {
		return pic_byte;
	}

	public void setPic_byte(byte[] pic_byte) {
		this.pic_byte = pic_byte;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
