package com.example.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "cdate", }, allowGetters = true)
@EntityListeners(AuditingEntityListener.class)

@Entity
public class MyCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mcid;

	@Column(name = "cdate", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate

	private Date cdate;

//	@Column
//	private  String cartvalue;

	@JsonBackReference
	@ManyToOne(targetEntity = Customer.class, optional = false)
	@JoinColumn(name = "cid")
	private Customer customer;

	@JsonManagedReference
	@OneToMany(mappedBy = "mycart", cascade = CascadeType.ALL, targetEntity = Orders.class, fetch = FetchType.EAGER)
	private List<Orders> orderList;

	@ManyToOne(targetEntity = Products.class, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "pid")
	private Products product;

	public MyCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyCart(int mcid, Date cdate, Customer customer, List<Orders> orderList, Products product) {
		super();
		this.mcid = mcid;
		this.cdate = cdate;
		this.customer = customer;
		this.orderList = orderList;
		this.product = product;

	}

	public MyCart(Date cdate, Customer customer, List<Orders> orderList) {
		super();
		this.cdate = cdate;
		this.customer = customer;
		this.orderList = orderList;
	}

	public int getMcid() {
		return mcid;
	}

	public void setMcid(int mcid) {
		this.mcid = mcid;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Orders> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}
}
