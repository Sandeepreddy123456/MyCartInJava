package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "odate", }, allowGetters = true)
@EntityListeners(AuditingEntityListener.class)

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int oid;

//	@Column
//	private float price ;
	@Column(name = "odate", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date odate;

	@JsonBackReference
	@ManyToOne(targetEntity = MyCart.class, optional = true)
	@JoinColumn(name = "mcid")
	private MyCart mycart;

//	@JsonManagedReference
	@ManyToOne(targetEntity = Products.class, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "pid")
	private Products product;

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(int oid, Date odate, MyCart mycart, Products product) {
		super();
		this.oid = oid;
		this.odate = odate;
		this.mycart = mycart;
		this.product = product;
	}

	public Orders(int oid, Date odate) {
		super();
		this.oid = oid;
		this.odate = odate;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public Date getOdate() {
		return odate;
	}

	public void setOdate(Date odate) {
		this.odate = odate;
	}

	public MyCart getMycart() {
		return mycart;
	}

	public void setMycart(MyCart mycart) {
		this.mycart = mycart;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

}
