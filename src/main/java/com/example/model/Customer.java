package com.example.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;

	@Column
	private String cname;

	@Column
	private String pass;

	@JsonManagedReference
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, targetEntity = MyCart.class, fetch = FetchType.EAGER)
	private List<MyCart> myCartList;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int cid, String cname, String pass, List<MyCart> myCartList) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.pass = pass;
		this.myCartList = myCartList;
	}

	public int getCid() {
		System.out.println(cid);
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<MyCart> getMyCartList() {
		return myCartList;
	}

	public void setMyCartList(List<MyCart> myCartList) {
		this.myCartList = myCartList;
	}

}
